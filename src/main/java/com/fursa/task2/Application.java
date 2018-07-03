package com.fursa.task2;

import com.fursa.task2.authservice.AuthService;
import com.fursa.task2.authservice.servlets.SignInServlet;
import com.fursa.task2.authservice.servlets.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Application {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(contextHandler);
        contextHandler.addServlet(new ServletHolder(new SignInServlet(AuthService.getInstance())), "/signin");
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(AuthService.getInstance())), "/signup");

        server.start();
        server.join();
    }
}
