package fr.univlyon1.m1if.m1if03.servlets;

import fr.univlyon1.m1if.m1if03.classes.Ballot;
import fr.univlyon1.m1if.m1if03.classes.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/users/*")
public class Users extends HttpServlet {
    Map<String, User> users;

    @SuppressWarnings("unchecked")
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        users = (Map<String, User>) config.getServletContext().getAttribute("users");


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getNamedDispatcher("UsersResources").forward(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getRequestURL().toString().split("/users/")[1];
        System.out.println("Action : " + action);
        if (action.equals("login") || action.equals("logout")){
            System.out.println("IF users");
            this.getServletContext().getNamedDispatcher("UsersOperation").forward(req, resp);
        }
        else {
            System.out.println("Else users");
            this.getServletContext().getNamedDispatcher("UsersResources").include(req, resp);
        }

    }
}
