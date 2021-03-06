package com.fursa.task2.authservice.servlets;

import com.fursa.task2.authservice.AuthService;
import com.fursa.task2.authservice.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.fursa.task2.authservice.util.PageVariables.FIELD_LOGIN;
import static com.fursa.task2.authservice.util.PageVariables.FIELD_PASSWORD;

public class SignInServlet extends HttpServlet {
    private AuthService authService;

    public SignInServlet(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(FIELD_LOGIN);
        String password = req.getParameter(FIELD_PASSWORD);
        User user = authService.authorize(req.getSession().getId(), login, password);
        if(user != null) {
            resp.getWriter().println("Authorized!");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(user.toString());
        } else {
            resp.getWriter().println("Unauthorized");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
