package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by yerlan on 25/01/17.
 */
@Controller
public class MealController {

    private static final Logger LOG = LoggerFactory.getLogger(MealController.class);

    @Autowired
    private MealService service;

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String getAction(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("filter".equals(action)) {
            LocalDate startDate = DateTimeUtil.parseLocalDate(request.getParameter("startDate"));
            LocalDate endDate = DateTimeUtil.parseLocalDate(request.getParameter("endDate"));
            LocalTime startTime = DateTimeUtil.parseLocalTime(request.getParameter("startTime"));
            LocalTime endTime = DateTimeUtil.parseLocalTime(request.getParameter("endTime"));
            Collection<Meal> meals = service.getBetweenDates(startDate, endDate, AuthorizedUser.id());
            request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(meals, startTime, endTime, AuthorizedUser.getCaloriesPerDay()));
            return "meals";
        }
        final Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        if (request.getParameter("id").isEmpty()) {
            LOG.info("Create {}", meal);
            service.save(meal, AuthorizedUser.id());
        } else {
            LOG.info("Update {}", meal);
            service.save(meal, AuthorizedUser.id());
        }
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request) {
        int id = getId(request);
        LOG.info("Delete {}", id);
        service.delete(id, AuthorizedUser.id());
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals/create", method = RequestMethod.GET)
    public String create(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "meal";
    }

    @RequestMapping(value = "/meals/update", method = RequestMethod.GET)
    public String update(HttpServletRequest request) {
        int id = getId(request);
        Meal meal = service.get(id, AuthorizedUser.id());
        request.setAttribute("meal", meal);
        return "meal";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String meals(Model model) {
        model.addAttribute("meals", MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

}
