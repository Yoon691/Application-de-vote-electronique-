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

        
        /** 
         * @param request
         * @param response
         * @throws ServletException
         * @throws IOException
         */
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String nouveauNom = request.getParameter("new-nom");
            System.out.println("new-nom : " + nouveauNom);
            if (nouveauNom != null && !nouveauNom.equals("")) {
                HttpSession session = request.getSession(true);
                User user = (User) session.getAttribute("user");
                user.setNom(nouveauNom);
                request.setAttribute("nomN", nouveauNom);
                request.getRequestDispatcher("profil.jsp").forward(request, response);
            } else {
                response.sendRedirect("profil.jsp");
            }
        }


    }
