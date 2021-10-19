package fr.univlyon1.m1if.m1if03.p1507426.servlets;

import fr.univlyon1.m1if.m1if03.p1507426.classes.Ballot;
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

@WebServlet(name = "ListBallots" , value = "/listBallots")
public class ListBallots extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/components/listBallots.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ServletConfig config = getServletConfig();
            ServletContext context = config.getServletContext();
            @SuppressWarnings("unchecked")
            Map<String, Ballot> ballots = (Map<String, Ballot>) context.getAttribute("ballots");
            String votant = req.getParameter("user");
            if (votant != null) {
                Ballot ballot = ballots.get(votant);
                Bulletin bulletin = ballot.getBulletin();
                @SuppressWarnings("unchecked")
                List<Bulletin> bulletins = (List<Bulletin>) context.getAttribute("bulletins");
                bulletins.remove(bulletin);
                ballot.setBulletin(null);
                ballots.remove(votant);
                req.getRequestDispatcher("WEB-INF/components/listBallots.jsp").include(req, resp);
            } else {
//                resp.sendRedirect("WEB-INF/components/listBallots.jsp");
                resp.sendRedirect("listBallots");
            }


    }
}
