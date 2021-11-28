package fr.univlyon1.m1if.m1if03.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univlyon1.m1if.m1if03.classes.Ballot;
import fr.univlyon1.m1if.m1if03.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.classes.Candidat;
import fr.univlyon1.m1if.m1if03.classes.User;
import fr.univlyon1.m1if.m1if03.dto.*;
import fr.univlyon1.m1if.m1if03.utils.ElectionM1if03JwtHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BallotsController", urlPatterns = {"/ballots", "/ballots/*"})
public class BallotsController extends HttpServlet {
    Map<String, Ballot> ballots;
    Map<String, Candidat> candidats;
    List<Bulletin> bulletins;
    Map<String, User> users;

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
        if ("ballots".equals(rootUrl)) {
            System.out.println("case vide");
            getBallots(req, resp);
        } else {
            System.out.println("default");
            String id = rootUrl.split("ballots/")[1];
            System.out.println("id: " + id);
            if (id.length() == 1) {
                getBallot(req, resp, Integer.parseInt(id));
            } else {
                String userId = id.split("/")[1];
                System.out.println("userId: " + userId);
                getBallotByUser(req, resp, userId);

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURL().toString().split("/election/")[1];
        System.out.println("uriBallot: " + uri);
        try {
            BallotDTO ballotDTO = new ObjectMapper().readValue(req.getReader(), BallotDTO.class);
            System.out.println("ballotDTO : " + ballotDTO.getNomCandidat());
            Ballot ballot;
            System.out.println("creation de ballots ");
            Candidat candidat = candidats.get(ballotDTO.getNomCandidat());
            Bulletin bulletin = new Bulletin(candidat);
            bulletins.add(bulletin);
            ballot = new Ballot(bulletin);
            String nameUser = (String) req.getAttribute("loggedUserUrl");
            System.out.println("nameUser: " + nameUser);
            ballots.put(nameUser, ballot);
            resp.setStatus(201);
        } catch (Exception e) {
            resp.sendError(400, "Paramatre de la requette non accepté ");
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rootUrl = req.getRequestURL().toString().replace(req.getRequestURL().toString().split("/election/")[0] + "/election/", "");
        String id = rootUrl.split("ballots/")[1];
        String nameUser = (String) req.getAttribute("loggedUserUrl");
        String key = (String) ballots.keySet().toArray()[Integer.parseInt(id)];
        System.out.println("nameUser: " + nameUser);
//        System.out.println("key: " + key);
//        Map<String, User> users = (Map<String, User>) req.getAttribute("users");
        User user = users.get(nameUser);
        if (key != null) {
            if (key.equals(nameUser) || user.isAdmin()) { //|| user.isAdmin()
                ballots.remove(key);
                resp.setStatus(204);
            } else {
                resp.sendError(403, "Utilisateur non administrateur ou non propriÃ©taire du ballot");
            }
        } else {
            resp.sendError(404, "Ballot non trouvÃ©");
        }
    }

    private void getBallots(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nameUser = (String) request.getAttribute("loggedUserUrl");
        System.out.println("nameUser: " + nameUser);
        User user = users.get(nameUser);
        if (!user.isAdmin()){
            System.out.println("!user.isAdmin()");
            request.setAttribute("CodeErreur","UserNonAdmin");
            return;
        }
        String rootUrl = request.getRequestURL().toString().split("/ballots")[0];
        BallotsDTO ballotsDTO = new BallotsDTO(ballots, rootUrl);
        request.setAttribute("DTO", ballotsDTO);
        request.setAttribute("Vu", "candidats/candidats");
        response.setStatus(200);
    }

    @SuppressWarnings("unchecked")
    private void getBallot(HttpServletRequest request, HttpServletResponse response, final Integer ballotId) throws IOException {
        String rootUrl = request.getRequestURL().toString().split("/ballots")[0];
        String nameUser = (String) request.getAttribute("loggedUserUrl");
        System.out.println("nameUser: " + nameUser);
        System.out.println("ballotId" + ballotId);
        String key = (String) ballots.keySet().toArray()[ballotId];
        System.out.println("key: " + key);

//        if (ballots.keySet().toArray()[ballotId] != null) {
//            key = (String) ballots.keySet().toArray()[ballotId];
//            System.out.println("key: " + key);
//        }

//        Map<String, User> users = (Map<String, User>) request.getAttribute("users");
        User user = users.get(nameUser);
        System.out.println("user.login" + user.getLogin());
        if (key == null) {
            System.out.println("if (user == null)");
            response.sendError(404, "Ballot non trouvÃ©");
        } else if (key.equals(nameUser)|| user.isAdmin()) { //
            System.out.println("aprés if (user == null)");
            BallotIdDTO ballotIdDTO = new BallotIdDTO(ballotId, rootUrl);
            System.out.println("ballotDot: " + ballotIdDTO);
            request.setAttribute("DTO", ballotIdDTO);
            request.setAttribute("Vu", "/ballots/ballots");
            response.setStatus(200);
        } else {
            response.sendError(403, "Utilisateur non administrateur ou non propriÃ©taire du ballot");
        }

    }

    @SuppressWarnings("unchecked")
    private void getBallotByUser(HttpServletRequest request, HttpServletResponse response, final String userId) throws IOException {
        String rootUrl = request.getRequestURL().toString().split("/ballots")[0];
        String nameUser = (String) request.getAttribute("loggedUserUrl");
        System.out.println("nameUser: " + nameUser);
//        Map<String, User> users = (Map<String, User>) request.getAttribute("users");
        User user = users.get(nameUser);
        System.out.println("user.login" + user.getLogin());
        if (ballots.get(userId) == null || users.get(userId) == null) {
            System.out.println("if (user == null)");
            response.sendError(404, "Utilisateur ou ballot non trouvé");
        } else if (ballots.get(userId).equals(ballots.get(nameUser))|| user.isAdmin()) { //
            System.out.println("aprés if (user == null)");
            BallotByUserDTO ballotByUserDTO = new BallotByUserDTO(ballots, userId, rootUrl, true);
            request.setAttribute("DTO", ballotByUserDTO);
            request.setAttribute("Vu", "/ballots/ballotsByUser");
            response.setStatus(200);
        } else {
            response.sendError(403, "Utilisateur non administrateur ou non propriÃ©taire du ballot");
        }


    }
}
