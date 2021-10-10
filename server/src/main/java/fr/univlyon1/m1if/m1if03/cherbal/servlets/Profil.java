package fr.univlyon1.m1if.m1if03.cherbal.servlets;

import fr.univlyon1.m1if.m1if03.cherbal.classes.Ballot;
import fr.univlyon1.m1if.m1if03.cherbal.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.cherbal.classes.Candidat;
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

@WebServlet(name = "Profil", value = "/profil")
public class Profil extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ServletConfig config = getServletConfig();
            ServletContext context = config.getServletContext();
            String nouveauNom = request.getParameter("new-nom");
            @SuppressWarnings("unchecked")
            Map<String, Candidat> listCandidat = (Map<String, Candidat>) request.getServletContext().getAttribute("candidats");
            System.out.println("new-nom : " + nouveauNom);
            if (nouveauNom != null && !nouveauNom.equals("")) {
                HttpSession session = request.getSession(true);
                User user = (User) session.getAttribute("user");
                user.setNom(nouveauNom);
                request.getRequestDispatcher("profil.jsp").forward(request, response);
            } else {
                response.sendRedirect("profil.jsp");
            }
        }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user != null ) {
            response.sendRedirect("profil.jsp");
        } else {

            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Vous avez pas le droite d'accéder a cette page il faut se connecter");
            //            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur dans la récupération de la liste des candidats.");

        }
    }

    }
