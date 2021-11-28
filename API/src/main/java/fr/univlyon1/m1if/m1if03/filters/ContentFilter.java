package fr.univlyon1.m1if.m1if03.filters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import fr.univlyon1.m1if.m1if03.classes.Candidat;
import fr.univlyon1.m1if.m1if03.dto.CandidatsDTO;
import fr.univlyon1.m1if.m1if03.dto.UserDTO;
import fr.univlyon1.m1if.m1if03.utils.CandidatListGenerator;
import org.graalvm.compiler.serviceprovider.IsolateUtil;

import javax.json.JsonObject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.univlyon1.m1if.m1if03.utils.HTMLContent.setContentAttributes;

@WebFilter(filterName = "ContentFilter")
public class ContentFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Je suis dans le ContentFilter 1");
        super.doFilter(request, response, chain);
        System.out.println("Je suis dans le ContentFilter 2");
        Object DTO = request.getAttribute("DTO");
        String codeErreur = (String) request.getAttribute("CodeErreur");
//        if(request.getMethod().equals("POST")) {
//            System.out.println("IF de POST FILTER");
//            response.setStatus(201);
////            response.setContentType("application/json");
//        }
        if(DTO == null){
            if (codeErreur != null) {
                System.out.println("if code erreur");
                switch (codeErreur) {
                    case "CandidatNonTrouvé":
                        response.sendError(404, "Candidat non trouvé");
                        break;
                    case "UserNonAdmin":
                        response.sendError(403, "Utilisateur non administrateur");
                        break;
                    default:
                }
            }
            return;
        }


        for(String acceptedFormat : request.getHeader("Accept").split(", ")) {
            System.out.println("acceptedFormat : " + acceptedFormat);
            switch (acceptedFormat) {
                case "application/json":
                    response.setContentType("application/json");
                    try {
                        System.out.println("try");

                        ObjectMapper objectMapper = new ObjectMapper();
//                        String responseAsString = objectMapper.writeValueAsString(request.getAttribute("DTO"));
                        objectMapper.writeValue(response.getWriter(), request.getAttribute("DTO"));
//                        objectMapper.writeValue(System.out, request.getAttribute("DTO"));

//                        JsonGenerator g = objectMapper.getFactory().createJsonGenerator(response.getWriter());
////                        String gAsString = objectMapper.writeValueAsString(g);
////                        System.out.println("gAsString"+ gAsString);
//                        System.out.println("responseAsString"+ responseAsString);
//                        String json = new Gson().toJson(responseAsString);
//                        response.setContentType("application/json");
//                        response.setCharacterEncoding("UTF-8");
//                        response.getWriter().write(json);
                    } catch (IOException ignored) {System.out.println("catch");}
//                    chain.doFilter(request, response);

                    return;
                case "text/html":
                    System.out.println("text/html");
                    setContentAttributes(request, DTO.getClass());
                    String vu = (String) request.getAttribute("Vu");
                    System.out.println("vue: " + vu);
                    request.getRequestDispatcher("/WEB-INF/components/".concat(vu).concat(".jsp")).include(request,response);
//                    response.sendRedirect("WEB-INF/components/candidats/candidats.jsp");
                    System.out.println("Fin text/html");
                    return;
            }
        }
        System.out.println("sendError");
        response.sendError(406, "Le serveur ne peux renvoyer aucun des formats accepter.");
    }
}

