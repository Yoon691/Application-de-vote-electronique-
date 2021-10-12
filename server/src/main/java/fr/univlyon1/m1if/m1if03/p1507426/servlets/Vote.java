package fr.univlyon1.m1if.m1if03.p1507426.servlets;


import fr.univlyon1.m1if.m1if03.p1507426.classes.Ballot;
import fr.univlyon1.m1if.m1if03.p1507426.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.p1507426.classes.Candidat;
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

@WebServlet(name = "Vote", value = "/vote")
public class Vote extends HttpServlet {



    
    /** 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();
        String candidatChoisi = request.getParameter("candidat-choisi");
        @SuppressWarnings("unchecked")
        Map<String, Candidat> listCandidat = (Map<String, Candidat>) request.getServletContext().getAttribute("candidats");
            System.out.println("candidatChoisi : " + candidatChoisi);
            if (candidatChoisi != null && !candidatChoisi.equals("")) {
                HttpSession session = request.getSession(true);
                Candidat candidat = listCandidat.get(candidatChoisi);
                Bulletin bulletin = new Bulletin(candidat);
                @SuppressWarnings("unchecked")
                List<Bulletin> bulletins = (List<Bulletin>) context.getAttribute("bulletins");
                bulletins.add(bulletin);
                Ballot ballot =new Ballot(bulletin);
                @SuppressWarnings("unchecked")
                Map<String,Ballot> ballots = (Map<String, Ballot>) context.getAttribute("ballots");
                User user = (User) session.getAttribute("user");
                ballots.put(user.getLogin(), ballot);
                session.setAttribute("candidatVoter", candidat );
                request.getRequestDispatcher("ballot.jsp").forward(request, response);
            } else {
                response.sendRedirect("ballot.jsp");
            }
    }


}