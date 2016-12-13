package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.data.Data;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by yerlan on 12/13/16.
 */

public class AddMealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(AddMealServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String param = request.getParameter("description");

        if (param != null)
            LOG.debug("Extractiong parameter values sucess!!" + param);
        else
            LOG.debug("Extraction failed");

        LocalDateTime dt = null;
        int cal = -1;

        try {
            dt = LocalDateTime.parse(request.getParameter("dateTime"));
            cal = Integer.parseInt(request.getParameter("calories"));
        }  catch (Exception e) {
            LOG.debug(e.getCause() + ":::" + e.getMessage());
        }

        if (dt == null)
            LOG.debug("Unsuccesfull operation LocalDateTime.parse");
        else
            LOG.debug("Extracted LocalDateTime parameter!");

        if (cal < 0)
            LOG.debug("Unsuccessull operation Integer.parseInt ");
        else
            LOG.debug("Extracted calories parameter");

        Meal meal = Data.getNewMeal(dt, param, cal);
        Data.put(meal);

        LOG.debug("Kinda Added new meal : " + meal);

        LOG.debug("Redirecting back to list of meals...");

        request.getRequestDispatcher("/meals").forward(request, response);
    }

}
