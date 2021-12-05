package fr.univlyon1.m1if.m1if03.filters;

import com.auth0.jwt.exceptions.JWTVerificationException;
import fr.univlyon1.m1if.m1if03.utils.ElectionM1if03JwtHelper;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter extends HttpFilter {
    private final String[] authorizedURIs = {"/index.html", "/static", "/election/resultats" ,"/users/login"};
    private final String[] authorizedURIsCandidat = {"/election/candidats", "/election/candidats/noms"};
    // Manque "/", traité plus bas...
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("Hello 1 ");
        String currentUri = req.getRequestURI().replace(req.getContextPath(), "");

        // Traitement de l'URL racine
        if(currentUri.equals("/")) {
            System.out.println("IF index");
            res.sendRedirect("index.html");
            return;
        }
        for(String authorizedUri: authorizedURIsCandidat) {
            System.out.println("authorizedUri: " + authorizedUri);
            System.out.println("currentUri : " + currentUri);
            String rootUrl =  currentUri.replace( "/election/candidats", "");
            if(rootUrl.equals("") || rootUrl.equals("/noms")) {
                    System.out.println("dofiltre candidat 1: " + req.getRequestURL().toString());
                    super.doFilter(req, res, chain);
                    System.out.println("dofiltre 2: " + req.getRequestURL().toString());
                    return;
                }
            }



        // Traitement des autres URLs autorisées sans authentification
        for(String authorizedUri: authorizedURIs) {
            System.out.println("authorizedUri: " + authorizedUri);
            if(currentUri.startsWith(authorizedUri)) {
                System.out.println("authorizedUri If: " + authorizedUri);
                System.out.println("Hello 2");
                System.out.println("dofiltre 1: " + req.getRequestURL().toString());
                super.doFilter(req, res, chain);
                System.out.println("dofiltre 2: " + req.getRequestURL().toString());
                return;
            }
        }

        try {
            System.out.println("TRY Athentification");
            System.out.println("URL FILTER Authentification: " + req.getRequestURL());
            // Vérification si l'utilisateur est authentifié
            String authorizationToken = req.getHeader("Authorization").replace("Bearer ", "");
            System.out.println("Authorization: " + authorizationToken);
            req.setAttribute("loggedUserUrl", ElectionM1if03JwtHelper.verifyToken(authorizationToken, req));
        } catch (NullPointerException | JWTVerificationException e) {
            res.sendError(401, "Utilisateur non authentifié");
            return;
        }
        chain.doFilter(req, res);

    }
}