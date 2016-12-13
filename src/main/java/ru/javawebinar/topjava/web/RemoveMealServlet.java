package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.data.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by yerlan on 12/13/16.
 */
public class RemoveMealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(RemoveMealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int requestId = Integer.parseInt(request.getParameter("id"));

        LOG.debug("Deleting meal with id#" + requestId + "was received");

        Data.remove(requestId);

        LOG.debug("Deleting success!");

        request.getRequestDispatcher("/meals").forward(request, response);

    }
}
