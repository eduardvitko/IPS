package com.servlets.subscriptions;


import com.dao.SubscriptionDao;
import com.dto.SubscriptionDto;

import com.exceptions.SubscriptionException;

import com.mapper.BusinessMapper;

import com.model.Subscription;
import com.service.SubscriptionServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "addSubscription", urlPatterns = "/add/subscription")
public class AddSubscriptionServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(AddSubscriptionServlet.class);
    private BusinessMapper businessMapper;
    SubscriptionServiceImpl subscriptionService = new SubscriptionServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("adding subscription");
        HttpSession session = req.getSession(true);
        String name = req.getParameter("name");
        SubscriptionDto subscriptionDto = new SubscriptionDto(name);
        try {
          subscriptionService.create(subscriptionDto);
                session.setAttribute("subscriptions", subscriptionService.findAll());
                resp.sendRedirect("/subscriptions");

            } catch (SubscriptionException e) {
                session.setAttribute("errorMessage", e.getMessage());
        }
    }
}



