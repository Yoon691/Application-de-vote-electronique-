package fr.univlyon1.m1if.m1if03.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univlyon1.m1if.m1if03.classes.Ballot;
import fr.univlyon1.m1if.m1if03.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.classes.Candidat;
import fr.univlyon1.m1if.m1if03.classes.User;
import fr.univlyon1.m1if.m1if03.dto.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "VotesController", urlPatterns = {"/votes/*"})
public class VotesController extends HttpServlet {
    Map<String, Ballot> ballots;
    Map<String, Candidat> candidats;
    List<Bulletin> bulletins;
    Map<String, User> users ;

    @SuppressWarnings("unchecked")
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ballots = (Map<String, Ballot>) config.getServletContext().getAttribute("ballots");
        System.out.println("ballots : " + ballots.size());
        candidats = (Map<String, Candidat>) config.getServletContext().getAttribute("candidats");
        bulletins = (List<Bulletin>) config.getServletContext().getAttribute("bulletins");
        users = (Map<String, User>) config.getServletContext().getAttribute("users");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rootUrl = req.getRequestURL().toString().replace(req.getRequestURL().toString().split("/election/")[0] + "/election/", "");
        System.out.println("rootUrl : " + rootUrl);
        String id = rootUrl.split("votes/")[1];
        System.out.println("id: " + id);
        if (id.length() == 1) {
            getVote(req, resp, Integer.parseInt(id));
        } else {
            String userId = id.split("/")[1];
            System.out.println("userId: " + userId);
            getVoteByUser(req, resp, userId);

        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rootUrl = req.getRequestURL().toString().replace(req.getRequestURL().toString().split("/election/")[0] + "/election/", "");
        System.out.println("rootUrl : " + rootUrl);
        String id = rootUrl.split("votes/")[1];
        String userId = id.split("/")[1];
        System.out.println("userId: " + userId);
        String nameUser = (String) req.getAttribute("loggedUserUrl");
        User user = users.get(nameUser);
        if (ballots.get(userId) == null || users.get(userId) == null){
            resp.sendError(404, "Utilisateur ou vote non trouvé");
        }

        try {
            if (userId.equals(nameUser)|| user.isAdmin()) {
                VoteUpdateByUserDTO voteUpdateByUserDTO = new ObjectMapper().readValue(req.getReader(), VoteUpdateByUserDTO.class);
                System.out.println("newNom : " + voteUpdateByUserDTO.getNom());
                Bulletin ancienBulletin = ballots.get(userId).getBulletin();
                System.out.println("AncienVote: " + ancienBulletin.getCandidat().getNom());
                bulletins.remove(ancienBulletin);
                Candidat candidat = candidats.get(voteUpdateByUserDTO.getNom());
                Bulletin newbulletin = new Bulletin(candidat);
                bulletins.add(newbulletin);
                ballots.get(userId).setBulletin(newbulletin);
                System.out.println("newVote: " + ballots.get(userId).getBulletin().getCandidat().getNom());
                resp.setStatus(204, "Vote correctement modifié");
            }else {
                resp.sendError(403, "Utilisateur non administrateur ou non propriétaire du ballot");

            }
        } catch (Exception e) {
            resp.sendError(400, "Paramètres de la requête non acceptables");
        }

    }


    @SuppressWarnings("unchecked")
    private void getVote(HttpServletRequest request, HttpServletResponse response, final Integer ballotId) throws IOException {
        String nameUser = (String) request.getAttribute("loggedUserUrl");
        System.out.println("nameUser: " + nameUser);
        String key = null;
        if ((String) ballots.keySet().toArray()[ballotId] != null) {
            key = (String) ballots.keySet().toArray()[ballotId];
            System.out.println("key: " + key);
        }

//        Map<String, User> users = (Map<String, User>) request.getAttribute("users");
        User user = users.get(nameUser);
        System.out.println("user.login" + user.getLogin());
        if (key == null) {
            System.out.println("if (user == null)");
            response.sendError(404, "Vote non trouvé");
        } else if (key.equals(nameUser)|| user.isAdmin()) { //
            System.out.println("aprés if (user == null)");
//            BallotIdDTO ballotIdDTO = new BallotIdDTO(ballotId, rootUrl);
//            System.out.println("ballotDot: " + ballotIdDTO);
            VoteDTO voteDTO = new VoteDTO();
            request.setAttribute("DTO", voteDTO);
            request.setAttribute("Vu", "/votes/vote");
            response.setStatus(200);
        } else {
            response.sendError(403, "Utilisateur non administrateur ou non propriétaire du ballot");
        }

    }

    @SuppressWarnings("unchecked")
    private void getVoteByUser(HttpServletRequest request, HttpServletResponse response, final String userId) throws IOException {
        String rootUrl = request.getRequestURL().toString().split("/votes")[0];
        String nameUser = (String) request.getAttribute("loggedUserUrl");
        System.out.println("nameUser: " + nameUser);
//        Map<String, User> users = (Map<String, User>) request.getAttribute("users");
        User user = users.get(nameUser);
         System.out.println("user.login" + user.getLogin());
        if (ballots.get(userId) == null || users.get(userId) == null) {
            System.out.println("if (user == null)");
            response.sendError(404, "Utilisateur ou vote non trouvé");
        } else if (ballots.get(userId).equals(ballots.get(nameUser)) || user.isAdmin()) { //
            System.out.println("aprés if (user == null)");
            BallotByUserDTO voteByUserDTO = new BallotByUserDTO(ballots, userId, rootUrl, false);
            request.setAttribute("DTO", voteByUserDTO);
            request.setAttribute("Vu", "/votes/votesByUser");
            response.setStatus(200);
        } else {
            response.sendError(403, "Utilisateur non administrateur ou non propriétaire du ballot");
        }


    }


}
