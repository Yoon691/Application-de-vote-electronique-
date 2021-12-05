package fr.univlyon1.m1if.m1if03.servlets;

import fr.univlyon1.m1if.m1if03.classes.Candidat;
import fr.univlyon1.m1if.m1if03.dto.CandidatDTO;
import fr.univlyon1.m1if.m1if03.dto.CandidatsDTO;
import fr.univlyon1.m1if.m1if03.dto.CandidatsNomDTO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "CandidatsController", urlPatterns = {"/candidats", "/candidats/*"})
public class CandidatsController extends HttpServlet {
    Map<String, Candidat> candidats;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        candidats = (Map<String, Candidat>) config.getServletContext().getAttribute("candidats");
        System.out.println("Candidats : " + candidats.size());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rootUrl =  req.getRequestURL().toString().replace(req.getRequestURL().toString().split("/election/")[0] + "/election/", "");

                System.out.println("rootUrl : " + rootUrl);
        switch (rootUrl){
            case "candidats/noms" :
                System.out.println("case nom");
                getNomsCandidats(req, resp);
                break;
            case "candidats" :
                System.out.println("case vide");
                getCandidats(req,resp);
                break;
            default:
                System.out.println("default");
                String id = rootUrl.split("candidats/")[1];
                getCandidat(req, resp, id);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


    private void getCandidats( HttpServletRequest request,  HttpServletResponse response) {
        String rootUrl = request.getRequestURL().toString().split("/candidats")[0];
        CandidatsDTO candidatsDTO = new CandidatsDTO( candidats, rootUrl);
        request.setAttribute("DTO", candidatsDTO);
        request.setAttribute("Vu","candidats/candidats");
        response.setStatus(200);
    }
    private void getNomsCandidats( HttpServletRequest request,  HttpServletResponse response) {
        CandidatsNomDTO candidatsNomDTO = new CandidatsNomDTO( candidats);
        request.setAttribute("DTO", candidatsNomDTO);
        request.setAttribute("Vu","candidats/candidatsNoms");
        response.setStatus(200);
    }
    private void getCandidat(HttpServletRequest request, HttpServletResponse response,final String candidatId) throws IOException {
        Candidat candidat = candidats.get(candidatId);
        if (candidat == null) {
            System.out.println("if (user == null)");
            request.setAttribute("CodeErreur","CandidatNonTrouvé");
            return;
        }
        System.out.println("aprés if (user == null)");

        CandidatDTO candidatDTO = new CandidatDTO(candidat);
        request.setAttribute("DTO", candidatDTO);
        request.setAttribute("Vu","/candidats/candidat");
        response.setStatus(200);
    }





}
