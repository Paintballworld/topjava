package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.data.Data;
import ru.javawebinar.topjava.model.Meal;

import javax.ejb.Local;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by yerlan on 12/13/16.
 */
public class UpdateMealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(UpdateMealServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int updatingMealId = Integer.parseInt(request.getParameter("id"));

        LOG.debug("Update query from html page for Meal id#" + updatingMealId + " is received.");

        String description = request.getParameter("description");

        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));

        int cal = Integer.parseInt(request.getParameter("calories"));

        Meal temp = Data.getNewMeal(dateTime, description, cal);

        Data.update(updatingMealId, temp);

        LOG.debug("Meal " + temp + " updated successfully!");

        request.getRequestDispatcher("/meals").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int updatingMealId = Integer.parseInt(request.getParameter("id"));

        LOG.debug("Update query for Meal id#" + updatingMealId + " is received. Forwarding to relevant html page");

        Meal mealItem = Data.get(updatingMealId);

        request.setAttribute("mealItem", mealItem);

        request.getRequestDispatcher("/update.jsp").forward(request, response);

    }
}
