package fr.univlyon1.m1if.m1if03.p1507426.servlets;


import fr.univlyon1.m1if.m1if03.p1507426.classes.Ballot;
import fr.univlyon1.m1if.m1if03.p1507426.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.p1507426.classes.Candidat;
import fr.univlyon1.m1if.m1if03.p1507426.classes.User;
import fr.univlyon1.m1if.m1if03.p1507426.utils.CandidatListGenerator;

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

@WebServlet(name = "Init", value = "/init", loadOnStartup = 1)
public class Init extends HttpServlet {
    Map<String, Candidat> candidats = null;
    final Map<String, Ballot> ballots = new HashMap<>();
    final List<Bulletin> bulletins = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        // Cette instruction doit toujours être au début de la méthode init() pour pouvoir accéder à l'objet config.
        super.init(config);

        ServletContext context = config.getServletContext();

            if (candidats == null) {
                try {
                    candidats = CandidatListGenerator.getCandidatList();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                context.setAttribute("candidats", candidats);
            }
            context.setAttribute("ballots", ballots);
        context.setAttribute("bulletins", bulletins);
    }

}