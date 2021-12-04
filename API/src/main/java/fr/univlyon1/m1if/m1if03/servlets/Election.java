package fr.univlyon1.m1if.m1if03.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/election/*")
public class Election extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String action = req.getRequestURI().replace(req.getContextPath() + "/election/", "");
        System.out.println("urlElection: " + url);
        System.out.println("actionDoget: " + action);
        processRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        processRequest(req, resp);
        String action = req.getRequestURL().toString().split("/election/")[1];
        switch(action) {
            case "resultats":
                this.getServletContext().getNamedDispatcher("ResultatsController").include(req, resp);
                break;
            case "ballots":
                this.getServletContext().getNamedDispatcher("BallotsController").forward(req, resp);
                break;
            default:
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getRequestURL().toString().split("/election/")[1];
//        String urlplus = action.split("/")[0];
        this.getServletContext().getNamedDispatcher("BallotsController").forward(req, resp);


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("processRequest Election");
        String id = null;
        String url = req.getRequestURL().toString().split("/election/")[1];
        System.out.println("url: " + url);
        System.out.println("lenght: " + url.length());
//        if (!(url.split("candidats")[1].isEmpty())) {
//            System.out.println("isEmpty");
//            id = url.split("candidats/")[1];
//            System.out.println("id: " + id);
//        }
//        String action = req.getRequestURI().replace(req.getContextPath() + "/election/", "");
        String action = req.getRequestURL().toString().split("/election/")[1];
        System.out.println("action: "+ action);
        req.setAttribute("action", action); // Utilisé dans electionHome.jsp

        switch(action) {
            case "resultats":
                this.getServletContext().getNamedDispatcher("ResultatsController").include(req, resp);
                break;
            case "ballots":
                this.getServletContext().getNamedDispatcher("BallotsController").include(req, resp);
                break;
            case "vote":
                this.getServletContext().getNamedDispatcher("Vote").forward(req, resp);
                break;
            case "deleteVote":
                this.getServletContext().getNamedDispatcher("DeleteVote").forward(req, resp);
                break;
            case "listBallots":
                this.getServletContext().getNamedDispatcher("Ballots").forward(req, resp);
                break;
            case "candidats":
            case "candidats/noms":
                System.out.println("/candidats");
                this.getServletContext().getNamedDispatcher("CandidatsController").include(req, resp);
                break;
            default:
//                if (url.length() > 9){
                    String urlplus = url.split("/")[0];
                    System.out.println("urlplus: " + urlplus);
                    System.out.println("if (url.length() > 9)");
                switch (urlplus) {
                    case "candidats":
                        System.out.println("(urlplus.equals(\"candidats\"))");
                        this.getServletContext().getNamedDispatcher("CandidatsController").include(req, resp);
                        return;
                    case "ballots":
                        this.getServletContext().getNamedDispatcher("BallotsController").include(req, resp);
                        return;
                    case "votes":
                        this.getServletContext().getNamedDispatcher("VotesController").include(req, resp);
                        return;
                }

//                }
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
