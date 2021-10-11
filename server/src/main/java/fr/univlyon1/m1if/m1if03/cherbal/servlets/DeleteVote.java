package fr.univlyon1.m1if.m1if03.cherbal.servlets;

import fr.univlyon1.m1if.m1if03.cherbal.classes.Ballot;
import fr.univlyon1.m1if.m1if03.cherbal.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.cherbal.classes.User;

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

@WebServlet(name = "DeleteVote", value = "/DeleteVote")
public class DeleteVote extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();
        @SuppressWarnings("unchecked")
        Map<String, Ballot> ballots = (Map<String, Ballot>) context.getAttribute("ballots");
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (ballots.get(user.getLogin()) != null) {

            Ballot ballot = ballots.get(user.getLogin());
            Bulletin bulletin = ballot.getBulletin();
            @SuppressWarnings("unchecked")
            List<Bulletin> bulletins = (List<Bulletin>) context.getAttribute("bulletins");
            bulletins.remove(bulletin);
            ballot.setBulletin(null);
            ballots.remove(user.getLogin());
            request.getRequestDispatcher("ballot.jsp").forward(request, response);

        }
        response.sendRedirect("ballot.jsp");
    }
}
