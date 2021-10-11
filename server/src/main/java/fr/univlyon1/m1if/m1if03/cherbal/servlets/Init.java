package fr.univlyon1.m1if.m1if03.cherbal.servlets;

import fr.univlyon1.m1if.m1if03.cherbal.classes.Ballot;
import fr.univlyon1.m1if.m1if03.cherbal.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.cherbal.classes.Candidat;
import fr.univlyon1.m1if.m1if03.cherbal.classes.User;
import fr.univlyon1.m1if.m1if03.cherbal.utils.CandidatListGenerator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Init", value = "/init")
public class Init extends HttpServlet {
    Map<String, Candidat> candidats = null;
    final Map<String, Ballot> ballots = new HashMap<>();
    final List<Bulletin> bulletins = new ArrayList<>();

    
    /** 
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        // Cette instruction doit toujours être au début de la méthode init() pour pouvoir accéder à l'objet config.
        super.init(config);
        ServletContext context = config.getServletContext();
        context.setAttribute("ballots", ballots);
        context.setAttribute("bulletins", bulletins);
    }

    
    /** 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // On intercepte le premier appel à Init pour mettre en place la liste des candidats,
        // car en cas d'erreur de chargement, il faut pouvoir renvoyer une erreur HTTP.
        // Fait dans un bloc try/catch pour le cas où la liste des candidats ne s'est pas construite correctement.
        try {
            if (candidats == null) {
                candidats = CandidatListGenerator.getCandidatList();
                Candidat candidatBlanc = new Candidat("**Vous avez votez BLANC !**", "VOTE Blanc");
                candidats.put("VOTE Blanc", candidatBlanc);
                request.getServletContext().setAttribute("candidats", candidats);
            }

            // Gestion de la session utilisateur
            String login = request.getParameter("login");
            if (login != null && !login.equals("")) {
                HttpSession session = request.getSession(true);
                request.getServletContext().getContext("ballots");
                session.setAttribute("user", new User(login, request.getParameter("nom") != null ? request.getParameter("nom") : ""));
                request.getRequestDispatcher("vote.jsp").forward(request, response);
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur dans la récupération de la liste des candidats.");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    
    /** 
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");

    }
}