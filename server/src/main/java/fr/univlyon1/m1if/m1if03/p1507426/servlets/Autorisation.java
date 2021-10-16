package fr.univlyon1.m1if.m1if03.p1507426.servlets;

import fr.univlyon1.m1if.m1if03.p1507426.classes.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

///listBallots.jsp
@WebFilter(filterName = "Autorisation", value = "/election/listBallots")

public class Autorisation extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException
    {
        //super.doFilter(req, res, chain);
        HttpSession session = req.getSession(true);

        if(((User) session.getAttribute("user")).isAdmin()) {
            System.out.println("Filtre Autorisation Admin");
            chain.doFilter(req, res);
        } else {
            System.out.println("Filtre Autorisation non Admin");

//            res.sendRedirect("ballot.jsp");
            req.getRequestDispatcher("listBallots").include(req, res);
        }

    }

}
