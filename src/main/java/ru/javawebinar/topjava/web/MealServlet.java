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
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by yerlan on 12/9/16.
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals");


        List<MealWithExceed> mealsWExceed = MealsUtil.getFilteredWithExceeded(Data.getAllMeals(),
                LocalDateTime.of(1900, Month.MAY, 31, 0, 0).toLocalTime(),
                LocalDateTime.of(2100, Month.MAY, 31, 23, 0).toLocalTime(),
                00);

        request.setAttribute("list", mealsWExceed);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }
}
