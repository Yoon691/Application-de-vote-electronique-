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

@WebServlet(name = "DeleteVote", value = "/deleteVote")
public class DeleteVote extends HttpServlet {
    
    /** 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("deleteVote Servlet");
        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();
        @SuppressWarnings("unchecked")
        Map<String, Ballot> ballots = (Map<String, Ballot>) context.getAttribute("ballots");
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (!user.isAdmin()) {
            System.out.println("deleteVote Servlet !user.isAdmin()");
            if (ballots.get(user.getLogin()) != null) {
                System.out.println("deleteVote Servlet ballots.get(user.getLogin()) != null");

                Ballot ballot = ballots.get(user.getLogin());
                Bulletin bulletin = ballot.getBulletin();
                @SuppressWarnings("unchecked")
                List<Bulletin> bulletins = (List<Bulletin>) context.getAttribute("bulletins");
                bulletins.remove(bulletin);
                ballot.setBulletin(null);
                ballots.remove(user.getLogin());
                request.getRequestDispatcher("ballot").forward(request, response);

            } else{
            response.sendRedirect("ballot");}
        } else {
            System.out.println("deleteVote Servlet admin");

            String votant = request.getParameter("user");
            if (votant != null) {
                System.out.println("deleteVote Servlet admin votant != null");

                Ballot ballot = ballots.get(votant);
                Bulletin bulletin = ballot.getBulletin();
                @SuppressWarnings("unchecked")
                List<Bulletin> bulletins = (List<Bulletin>) context.getAttribute("bulletins");
                bulletins.remove(bulletin);
                ballot.setBulletin(null);
                ballots.remove(votant);
                System.out.println("deleteVote Servlet admin votant != null FIN");

                request.getRequestDispatcher("listBallots").forward(request, response);
            } else {
                response.sendRedirect("listBallots");
            }
        }
    }
}
