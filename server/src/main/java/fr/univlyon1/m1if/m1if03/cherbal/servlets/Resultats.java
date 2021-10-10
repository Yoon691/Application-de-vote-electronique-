package fr.univlyon1.m1if.m1if03.cherbal.servlets;

import fr.univlyon1.m1if.m1if03.cherbal.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.cherbal.classes.Candidat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "Resultats", value = "/resultats")
public class Resultats extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        @SuppressWarnings("unchecked")
        Map<String, Candidat> listCandidat = (Map<String, Candidat>) request.getServletContext().getAttribute("candidats");
        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();
        Map<String, Integer> votes = new HashMap<>();
        Set<String> listCandidatCle = listCandidat.keySet();
        for (String nomCandidat : listCandidatCle) {
            votes.put(nomCandidat, 0);
        }

        for (Bulletin bulletin : (List<Bulletin>)  context.getAttribute("bulletins")) {
            int score = ((Map<String, Integer>) votes).get(bulletin.getCandidat().getNom());
            votes.put(bulletin.getCandidat().getNom(), ++score);
        }
        request.setAttribute("votes", votes);

        request.getRequestDispatcher("resultats.jsp").include(request,response);
    }
}
