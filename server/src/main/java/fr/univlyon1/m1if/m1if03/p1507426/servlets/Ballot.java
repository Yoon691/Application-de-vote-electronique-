package fr.univlyon1.m1if.m1if03.p1507426.servlets;

import fr.univlyon1.m1if.m1if03.p1507426.classes.Bulletin;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Ballot" , value = "/ballot")
public class Ballot extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();
        @SuppressWarnings("unchecked")
        Map<String, Ballot> ballots = (Map<String, Ballot>) context.getAttribute("ballots");
        System.out.println("nombre de vote" + ballots.size());
        int nombreVote = ballots.size();
        req.setAttribute("nombreVote", nombreVote);
        req.getRequestDispatcher("WEB-INF/components/ballot.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();
        @SuppressWarnings("unchecked")
        Map<String, Ballot> ballots = (Map<String, Ballot>) context.getAttribute("ballots");
//        String eTagFromBrowser = req.getHeader("If-None-Match");
//        System.out.println("If-None-Match : " + eTagFromBrowser);
        System.out.println("Ballots : " + ballots.size());
        List<Bulletin> bulletins = (List<Bulletin>) context.getAttribute("bulletins");
        System.out.println("Bultins  : " + bulletins.size());

//        Integer nombreDeVote = bulletins.size();
        int nombreVote = ballots.size();
        req.setAttribute("nombreVote", nombreVote);
        req.getRequestDispatcher("WEB-INF/components/ballot.jsp").include(req, resp);
    }
}
