package fr.univlyon1.m1if.m1if03.p1507426.servlets;

import fr.univlyon1.m1if.m1if03.p1507426.classes.User;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import javax.servlet.FilterChain;

import java.io.IOException;

@WebFilter(filterName = "Authentification", urlPatterns = "/election/*")
public class Authentification extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filtre Autorisation");
        HttpSession session = req.getSession(true);

                if (session.getAttribute("user") != null ) {
                    System.out.println("session.getAttribute(user) != null");
                        chain.doFilter(req, res);
                } else if (req.getParameter("login") != null && !req.getParameter("login").equals("")) {
                    System.out.println("Filtre Authentification connection");
                    User nouveauUser = new User(req.getParameter("login"),
                            req.getParameter("nom") != null ? req.getParameter("nom") : "",
                            req.getParameter("admin") != null && req.getParameter("admin").equals("on"));
                    session.setAttribute("user", nouveauUser);
                    chain.doFilter(req, res);
                } else {
                    System.out.println("Filtre Autorisation non connecter");
                    res.sendRedirect("../");
                }


    }

}
