package fr.univlyon1.m1if.m1if03.p1507426.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//"/election/resultats" , "/election/user", "/election/vote","/election/listBallots"
@WebServlet(name = "ServletController", urlPatterns = {"/election", "/election/*"  })
public class ServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURL().toString().split("/election")[1];
        String urL = req.getRequestURL().toString();
        System.out.println("uri doGet : " + uri );
        System.out.println("urL doGet : " + urL );
        switch (uri){
            case "/resultats":
                System.out.println("resultat doGet ");
//                resp.sendRedirect("/resultats");
                req.getRequestDispatcher("/resultats").forward(req, resp);

                break;
            case "/user":
                System.out.println("user doGet");
//                resp.sendRedirect("/resultats");
                req.getRequestDispatcher("/profil").forward(req, resp);
                break;
            case "/vote":
                System.out.println("vote doGet");
//                resp.sendRedirect("/resultats");
                req.getRequestDispatcher("/vote").forward(req, resp);
                break;
            case "/listBallots":
                System.out.println("listBallot doGet");
//                resp.sendRedirect("/resultats");
                req.getRequestDispatcher("/listBallots").forward(req, resp);
                break;
            case "/ballot":
                System.out.println("listBallot doGet");
//                resp.sendRedirect("/resultats");
                req.getRequestDispatcher("/ballot").forward(req, resp);
                break;
            case "/deco":
                System.out.println("deco doGet");
//                resp.sendRedirect("/resultats");
                req.getRequestDispatcher("/deco").forward(req, resp);
                break;


            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, " La ressource req.getRequestURL().toString()demandé existe pas");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURL().toString().split("/election")[1];
        String urL = req.getRequestURL().toString();
        System.out.println("uri : " + uri );
        System.out.println("urL doPost : " + urL );
        switch (uri){

            case "/user":
                System.out.println("user doPost");
//                resp.sendRedirect("/resultats");
                req.getRequestDispatcher("/profil").forward(req, resp);
                break;
            case "/vote":
                System.out.println("vote doPost");
//                resp.sendRedirect("/resultats");
                req.getRequestDispatcher("/vote").forward(req, resp);
                break;
            case "/listBallots":
                System.out.println("listBallot doPost");
//                resp.sendRedirect("/resultats");
                req.getRequestDispatcher("/listBallots").forward(req, resp);
                break;
            case "/deleteVote":
                System.out.println("deleteVote doPost");
//                resp.sendRedirect("/resultats");
                req.getRequestDispatcher("/deleteVote").forward(req, resp);
                break;
            case "/ballot":
                System.out.println("Ballot doPost");
                String supprime = (String) req.getAttribute("action");
                System.out.println("supprime : " + supprime);
//                resp.sendRedirect("/resultats");
                req.getRequestDispatcher("/ballot").forward(req, resp);
                break;


            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, " La ressource demandé existe pas");
                break;
        }
    }
}
