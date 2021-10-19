package fr.univlyon1.m1if.m1if03.p1507426.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletController", urlPatterns = {"/election", "/election/*"  })
public class ServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURL().toString().split("/election")[1];
        System.out.println("uri doGet : " + uri );
        switch (uri){
            case "/resultats":
                req.getRequestDispatcher("/resultats").forward(req, resp);
                break;
            case "/user":
                req.getRequestDispatcher("/profil").forward(req, resp);
                break;
            case "/vote":
                req.getRequestDispatcher("/vote").forward(req, resp);
                break;
            case "/listBallots":
                req.getRequestDispatcher("/listBallots").forward(req, resp);
                break;
            case "/ballot":
                req.getRequestDispatcher("/ballot").forward(req, resp);
                break;
            case "/deco":
                req.getRequestDispatcher("/deco").forward(req, resp);
                break;

            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, " La ressource demandée n'existe pas");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURL().toString().split("/election")[1];
        System.out.println("uri : " + uri );
        switch (uri){
            case "/user":
                req.getRequestDispatcher("/profil").forward(req, resp);
                break;
            case "/vote":
                req.getRequestDispatcher("/vote").forward(req, resp);
                break;
            case "/listBallots":
                req.getRequestDispatcher("/listBallots").forward(req, resp);
                break;
            case "/ballot":
                req.getRequestDispatcher("/ballot").forward(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, " La ressource demandée n'existe pas");
                break;
        }
    }
}
