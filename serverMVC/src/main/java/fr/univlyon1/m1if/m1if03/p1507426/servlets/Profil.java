package fr.univlyon1.m1if.m1if03.p1507426.servlets;



import fr.univlyon1.m1if.m1if03.p1507426.classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "Profil", value = "/profil")
public class Profil extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String nouveauNom = request.getParameter("new-nom");
            System.out.println("new-nom : " + nouveauNom);
            if (nouveauNom != null && !nouveauNom.equals("")) {
                HttpSession session = request.getSession(true);
                User user = (User) session.getAttribute("user");
                user.setNom(nouveauNom);
                request.setAttribute("nomN", nouveauNom);
                request.getRequestDispatcher("WEB-INF/components/profil.jsp").include(request, response);
            } else {
                response.sendRedirect("user");
            }
        }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/components/profil.jsp").include(request, response);
    }


    }
