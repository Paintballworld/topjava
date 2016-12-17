package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private MealRestController mealRestController;

    private AdminRestController adminRestController;

    ConfigurableApplicationContext appCtx;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//        repository = new InMemoryMealRepositoryImpl();

        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        System.out.println("\n\n\n");
        mealRestController = appCtx.getBean(MealRestController.class);
        mealRestController.getAll().forEach(System.out::println);
        adminRestController = appCtx.getBean(AdminRestController.class);
        System.out.println("\n\n\n");

    }

    @Override
    public void destroy() {
        appCtx.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        LOG.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        if (meal.isNew())
            mealRestController.create(meal);
        else {
            int mealId = getId(request);
            mealRestController.update(meal, mealId);
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");

            setBasicDataToRequest(request);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);

        } else if("changeUser".equals(action)) {
            String userEmail = request.getParameter("newUserId");
            LOG.info("Switching user to " + userEmail);
            User newUser = adminRestController.getByMail(userEmail);
            AuthorizedUser.setId(newUser.getId());
            setBasicDataToRequest(request);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);

        } else if ("filter".equals(action)) {
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String startTimeStr = request.getParameter("startTime");
            String endTimeStr = request.getParameter("endTime");
            LocalDate startDate = startDateStr != null ? LocalDate.parse(startDateStr) : null;
            LocalDate endDate = endDateStr != null ? LocalDate.parse(endDateStr) : null;
            LocalTime startTime = startTimeStr != null ? LocalTime.parse(startTimeStr) : null;
            LocalTime endTime = endTimeStr != null ? LocalTime.parse(endTimeStr) : null;

            LOG.info("getFiltered between " + startDate + " " + startTime
                    + " - " + endDate + " " + endTime);
            request.setAttribute( "meals", mealRestController.getFilteredWithExceeded(startDate, endDate, startTime, endTime));
            request.getRequestDispatcher("/meal.jsp").forward(request, response);

        } else if ("delete".equals(action)) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            mealRestController.delete(id);
            response.sendRedirect("meals");

        } else if ("create".equals(action) || "update".equals(action)) {
            final Meal meal = action.equals("create") ?
                    new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                    mealRestController.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("meal.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    private void setBasicDataToRequest(HttpServletRequest request) {
        User authorizedUser = adminRestController.get(AuthorizedUser.id());
        request.setAttribute("currentUser", authorizedUser);
        request.setAttribute("meals", mealRestController.getAllWithExceeded());
        request.setAttribute("users", adminRestController.getAll());
    }
}