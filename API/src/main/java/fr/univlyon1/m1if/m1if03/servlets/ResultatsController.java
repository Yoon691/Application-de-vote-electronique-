package fr.univlyon1.m1if.m1if03.servlets;

import fr.univlyon1.m1if.m1if03.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.classes.Candidat;
import fr.univlyon1.m1if.m1if03.classes.User;
import fr.univlyon1.m1if.m1if03.dto.ResultatDTO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ResultatsController", urlPatterns = "/resultats")
public class ResultatsController extends HttpServlet {
    Map<String, Candidat> candidats;
    List<Bulletin> bulletins;
    Map<String, User> users;

    @SuppressWarnings("unchecked")
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bulletins = (List<Bulletin>) config.getServletContext().getAttribute("bulletins");
        users = (Map<String, User>) config.getServletContext().getAttribute("users");
    }
    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("je suis dans Resultat");
        candidats = (Map<String, Candidat>) req.getServletContext().getAttribute("candidats");
        List<ResultatDTO> resultatsDTO = new ArrayList<>();
        for (String nomCandidat : candidats.keySet()) {
            ResultatDTO resultatDTO = new ResultatDTO(nomCandidat, bulletins);
            resultatsDTO.add(resultatDTO);
        }
        req.setAttribute("DTO", resultatsDTO);
        req.setAttribute("Vu","resultats/resultats");
        resp.setStatus(200);



    }
//
//    Map<String, Integer> votes = new HashMap<>();
//        for (String nomCandidat : candidats.keySet()) {
//        votes.put(nomCandidat, 0);
//    }
//
//        for (Bulletin bulletin : bulletins) {
//        int score = votes.get(bulletin.getCandidat().getNom());
//        votes.put(bulletin.getCandidat().getNom(), ++score);
//    }
//        req.setAttribute("votes", votes);
//        req.getRequestDispatcher("/WEB-INF/components/resultats.jsp").forward(req, resp);
}



