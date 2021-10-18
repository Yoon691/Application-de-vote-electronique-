package fr.univlyon1.m1if.m1if03.p1507426.servlets;

import fr.univlyon1.m1if.m1if03.p1507426.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.p1507426.classes.User;
import fr.univlyon1.m1if.m1if03.p1507426.classes.Ballot;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ServletBallot" , value = "/ballot")
public class ServletBallot extends HttpServlet {
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
        System.out.println("Ballot Servlet");
        String supprime = (String) req.getAttribute("action");
        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();
        @SuppressWarnings("unchecked")
        Map<String, Ballot> ballots = (Map<String, Ballot>) context.getAttribute("ballots");
        @SuppressWarnings("unchecked")
        List<Bulletin> bulletins = (List<Bulletin>) context.getAttribute("bulletins");
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");
        if (supprime != null &&  supprime.equals("supprimer")) {
            if (ballots.get(user.getLogin()) != null) {
                System.out.println("Ballot Servlet ballots.get(user.getLogin()) != null");
                Ballot ballot = ballots.get(user.getLogin());
                Bulletin bulletin = ballot.getBulletin();
                bulletins.remove(bulletin);
                ballot.setBulletin(null);
                ballots.remove(user.getLogin());
//                    req.getRequestDispatcher("ballot").forward(req, resp);
//
//                } else{
//                    resp.sendRedirect("ballot");}
            }
        }

        System.out.println("Ballots : " + ballots.size());

        System.out.println("Bultins  : " + bulletins.size());

//        Integer nombreDeVote = bulletins.size();
        int nombreVote = ballots.size();
        req.setAttribute("nombreVote", nombreVote);
        req.getRequestDispatcher("WEB-INF/components/ballot.jsp").include(req, resp);
    }
}
