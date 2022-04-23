package com.servlets;

import com.dto.UserCreateRequestDto;
import com.model.User;
import com.service.UserService;
import com.service.UserServiceImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registration", urlPatterns = {"/user/registration"})
public class UserRegistrationServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String conf_password = req.getParameter("conf_password");

        UserCreateRequestDto user = new UserCreateRequestDto(phone, password, conf_password);
        userService.registration(user);

        //String response = userService.registration(user);

//        if ((response.equals("error"))) {
//            req.setAttribute("validation_message", response);
//            req.getRequestDispatcher("/registration.jsp").forward(req,resp);
//
//        }
//        else {
            req.getRequestDispatcher("/success.html").forward(req,resp);
            req.setAttribute("validation_message", "you are successfully registration");

        //}
    }
}
