package fr.univlyon1.m1if.m1if03.p1507426.servlets;

import fr.univlyon1.m1if.m1if03.p1507426.classes.Ballot;
import fr.univlyon1.m1if.m1if03.p1507426.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.p1507426.classes.User;

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

@WebServlet(name = "ListBallots" , value = "/listBallots")
public class ListBallots extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletConfig config = getServletConfig();
//        ServletContext context = config.getServletContext();
//        @SuppressWarnings("unchecked")
//        Map<String, Ballot> ballots = (Map<String, Ballot>) context.getAttribute("ballots");
//        HttpSession session = request.getSession(true);
//        User user = (User) session.getAttribute("user");
//        if (ballots.get(user.getLogin()) != null) {
//
//            Ballot ballot = ballots.get(user.getLogin());
//            Bulletin bulletin = ballot.getBulletin();
//            @SuppressWarnings("unchecked")
//            List<Bulletin> bulletins = (List<Bulletin>) context.getAttribute("bulletins");
//            bulletins.remove(bulletin);
//            ballot.setBulletin(null);
//            ballots.remove(user.getLogin());
//            request.getRequestDispatcher("ballot.jsp").forward(request, response);
//
//        }
//        response.sendRedirect("ballot.jsp");
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute("user");
        if(user == null) {

            // Remarque : on ne peut pas avoir 2 blocs c:if qui font chacun un sendError (erreur 500).--%>
            // D'où la double condition et les 2 types d'erreurs dans la même instruction sendError()--%>
            response.sendError(session.getAttribute("user") == null ? HttpServletResponse.SC_FORBIDDEN : HttpServletResponse.SC_UNAUTHORIZED, "Admin : " + ((User) session.getAttribute("User")).isAdmin());

        } else if(user.isAdmin()){

        request.getRequestDispatcher("listBallots.jsp").include(request, response);
        } else {
            request.getRequestDispatcher("ballot.jsp").include(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("ballot.jsp").include(req, resp);

    }
}
//
//    ServletConfig config = getServletConfig();
//    ServletContext context = config.getServletContext();
//    @SuppressWarnings("unchecked")
//    Map<String, Ballot> ballots = (Map<String, Ballot>) context.getAttribute("ballots");
//    HttpSession session = req.getSession(true);
//    User user = (User) session.getAttribute("user");
//        if (ballots.get(user.getLogin()) != null) {
//                req.getRequestDispatcher("ballot.jsp").include(req, resp);
//                }
//                resp.sendRedirect("ballot.jsp");
