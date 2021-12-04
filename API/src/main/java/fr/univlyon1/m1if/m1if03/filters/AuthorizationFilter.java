package fr.univlyon1.m1if.m1if03.filters;

import fr.univlyon1.m1if.m1if03.classes.Candidat;
import fr.univlyon1.m1if.m1if03.classes.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/election/ballots")
public class AuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        ServletContext context = req.getServletContext();

        Map<String, User> users = (Map<String, User>) context.getAttribute("users");
        String nameUser = (String) req.getAttribute("loggedUserUrl");
        System.out.println("nameUser: " + nameUser);
        User user = users.get(nameUser);
        if (user == null){
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "Utilisateur n'existes pas encore.");

        } else if (req.getMethod().equals("GET")){
            if(user.isAdmin()) {
            super.doFilter(req, res, chain);
        } else {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Vous devez être administrateur pour accéder à cette page.");
        }
    } else {
            super.doFilter(req, res, chain);
        }
    }
}