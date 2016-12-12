package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.data.Data;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

/**
 * Created by me on 12/11/16.
 */
public class MealServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("list", MealsUtil.getFilteredWithExceeded(Data.getAllMeals(), LocalTime.MIN,
                LocalTime.MAX, 0));
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
