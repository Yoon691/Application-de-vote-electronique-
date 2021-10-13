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

@WebServlet(name = "FiltreAuthentification", value = "/FiltreAuthentification")
public class FiltreAuthentification extends HttpFilter {

    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        getFilterConfig().getServletContext().log("FiltreAuthentification de Connexion lancé");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        getFilterConfig().getServletContext().log("FiltreAuthentif en marche");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        if(session != null){
            User user = (User)session.getAttribute("user");

            if (user != null && user.isAdmin() ){
                response.sendRedirect("index.html");
            }
            if (user != null && !user.isAdmin() ){
                chain.doFilter(req,res);
            }
        }

    }

    public void destroy() {
        getFilterConfig().getServletContext().log("FiltreAuthentification de connexion détruit");
    }

}
