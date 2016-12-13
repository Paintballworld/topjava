package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.data.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by yerlan on 12/13/16.
 */
public class AddMealServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDateTime ldt = (LocalDateTime) request.getAttribute("dateTime");
        String des = (String) request.getAttribute("description");
        int cal = (int) request.getAttribute("calories");
        if (ldt != null && des != null) {
            Data.put(ldt, des, cal);
        }
    }
}
