//package fr.univlyon1.m1if.m1if03.p1507426.servlets;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name = "ServletController", urlPatterns = {"/election/resultat", "/election/user", "/election/vote","/election/listBallots"})
//public class ServletController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String uri = req.getRequestURL().toString().split("/election")[1];;
//        System.out.println("uri : " + uri );
//        switch (uri){
//            case "/resultat":
//                System.out.println("resultat");
////                resp.sendRedirect("/resultats");
//                req.getRequestDispatcher("/resultats").include(req, resp);;
//
//                break;
//            case "/election/user":
//
//                break;
//
//            default:
//                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Voila je suis dans la servlet ");
//                break;
//        }
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
//}
