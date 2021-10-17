package fr.univlyon1.m1if.m1if03.p1507426.servlets;

import fr.univlyon1.m1if.m1if03.p1507426.classes.Ballot;
import fr.univlyon1.m1if.m1if03.p1507426.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.p1507426.classes.Candidat;
import fr.univlyon1.m1if.m1if03.p1507426.classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletConfig;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import java.io.IOException;

@WebFilter(filterName = "Authentification", urlPatterns = "/election")
public class Authentification extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filtre Autorisation");
        HttpSession session = req.getSession(true);

        if(session.getAttribute("user") != null) {
            System.out.println("Filtre Autorisation user != null");

            chain.doFilter(req, res);
        } else if (req.getParameter("login") != null && !req.getParameter("login").equals("")){
            System.out.println("Filtre Autorisation connection");
            session.setAttribute("user", new User(req.getParameter("login"),
                    req.getParameter("nom") != null ? req.getParameter("nom") : "",
                    req.getParameter("admin") != null && req.getParameter("admin").equals("on")));
//            request.getRequestDispatcher("vote.jsp").forward(request, response);
           // res.sendRedirect("vote.jsp");
            req.getRequestDispatcher("WEB-INF/components/vote.jsp").include(req, res);
        } else {
            System.out.println("Filtre Autorisation non connecter");
            res.sendRedirect("index.html");
        }


    }

//    public void destroy() {
//        getFilterConfig().getServletContext().log("FiltreAuthentification de connexion détruit");
//    }

}
