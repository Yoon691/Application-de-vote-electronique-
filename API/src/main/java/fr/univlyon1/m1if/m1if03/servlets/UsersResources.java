package fr.univlyon1.m1if.m1if03.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fr.univlyon1.m1if.m1if03.classes.User;
import fr.univlyon1.m1if.m1if03.dto.NomUserDTO;
import fr.univlyon1.m1if.m1if03.dto.UserDTO;
import fr.univlyon1.m1if.m1if03.dto.UsersDTO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "UsersResources", urlPatterns = {})
public class UsersResources extends HttpServlet {
    Map<String, User> users ;

    @SuppressWarnings("unchecked")
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        users = (Map<String, User>) config.getServletContext().getAttribute("users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURL().toString().split("/users/")[1];
        System.out.println("uri : " + uri);
        String uriPlus = null;
        if (uri.endsWith("ballot")){
            uriPlus = uri.split("/")[1];
        }

        if (uri.equals("list")){
            getUsers(req, resp);
        } else if (uriPlus != null){
            String id = uri.split("/")[0];
            getUserBallot(req,resp,id);
        }else {
            getUser(req,resp,uri);
        }

    }
    @SuppressWarnings("unchecked")
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            NomUserDTO usernomdto = null;
            for(String contentType : req.getHeader("Content-Type").split(", ")) {
                switch (contentType) {
                    case "application/x-www-form-urlencoded":
                        System.out.println("application/x-www-form-urlencoded");
                        String nom = req.getParameter("nom");
                        System.out.println("nomPut: " + nom);
                        User user = new User(null, nom, false);
                        usernomdto = new NomUserDTO(user);
                        System.out.println("usernomdtoUrlEncoded : " + usernomdto.getNom());
                        break;
                    case "application/json":
                        System.out.println("application/json");
                         usernomdto = new ObjectMapper().readValue(req.getReader(), NomUserDTO.class);
                        System.out.println("usernomdtoJson : " + usernomdto.getNom());
                        break;
                    case "application/xml":
                        System.out.println("application/xml");
                        XmlMapper xmlMapper = new XmlMapper();
                        usernomdto = xmlMapper.readValue(req.getReader(), NomUserDTO.class);
                        System.out.println("nomxML: " + usernomdto.getNom());
                        break;
                    default:
                        System.out.println("Default Login");
                        resp.sendError(400, "Paramètres de la requête non acceptables ");
                        return;
                }


            }
                String uri = req.getRequestURL().toString().split("/users/")[1];
                System.out.println("uri Put user: " + uri );
                String login = uri.split("/")[0];
                System.out.println("login: " + login);
                User user = users.get(login);
                String nameUserLoguer = (String) req.getAttribute("loggedUserUrl");
            if (users.get(nameUserLoguer) == null){
                System.out.println("key == null");
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Utilisateur n'existes pas encore.");
                return;
            }
                User userLoguer = users.get(nameUserLoguer);
                if(user==null) {
                    System.out.println("if user == null");
                    resp.sendError(404, "Utilisateur non trouvé");
                    return;
                }
                if (user.getLogin().equals(userLoguer.getLogin()) || userLoguer.isAdmin()){
                    System.out.println("else if user : " +  user.getLogin());
                    assert usernomdto != null;
                    System.out.println("usernomdto: " + usernomdto.getNom());
                    System.out.println("usernomdto != null");
                    user.setNom(usernomdto.getNom());
                    System.out.println("user.getnom: " + user.getNom());
                    users.put(login,user);
                    resp.setStatus(204);
                } else {
                    resp.sendError(403, "Utilisateur non administrateur ou  ou pas celui qui est logué");
                }

            } catch (Exception e){
                System.out.println("Catch");
                resp.sendError(400, "Paramètres de la requête non acceptables ");
            }

        }


    @SuppressWarnings("unchecked")
    private void getUser(final HttpServletRequest request, final HttpServletResponse response,
                         final String userId) throws IOException {

        String nameUserLoguer = (String) request.getAttribute("loggedUserUrl");
        if (users.get(nameUserLoguer) == null){
            System.out.println("key == null");
            request.setAttribute("CodeErreur","UserNoexist");
            return;
        }
        User userLoguer = users.get(nameUserLoguer);
        User user = users.get(userId);
        if (user == null) {
            System.out.println("if (user == null)");
            request.setAttribute("CodeErreur","UserNoTrouver");
            return;
        }
        System.out.println("aprés if (user == null)");
        if (user.getLogin().equals(userLoguer.getLogin()) || userLoguer.isAdmin()){
            UserDTO userDTO = new UserDTO(user);
            request.setAttribute("DTO", userDTO);
            request.setAttribute("Vu","/users/user");
            response.setStatus(200);
        } else {
            request.setAttribute("CodeErreur","UserNonAdminOuNonProp");
        }

    }
    @SuppressWarnings("unchecked")
    private void getUserBallot(final HttpServletRequest request, final HttpServletResponse response,
                         final String userId) throws IOException {
        String nameUserLoguer = (String) request.getAttribute("loggedUserUrl");
        if (users.get(nameUserLoguer) == null){
            System.out.println("key == null");
            request.setAttribute("CodeErreur","UserNoexist");
            return;
        }
        User userLoguer = users.get(nameUserLoguer);
        User user = users.get(userId);
        if (user == null) {
            System.out.println("if (user == null)");
            request.setAttribute("CodeErreur","UserNoTrouver");
            return;
        }
        if (user.getLogin().equals(userLoguer.getLogin()) || userLoguer.isAdmin()) {
            System.out.println("getUserBallot");
            String url = request.getRequestURL().toString().split("/user")[0];
            System.out.println("url" + url);
            String urlRedirect = url.concat("/election/ballots/byUser/" + userId);
            System.out.println("urlRedirect" + urlRedirect);
            request.setAttribute("CodeErreur", "redirect");
            request.setAttribute("urlRedirect", urlRedirect);
            request.setAttribute("userId", userId);

        }else {
            request.setAttribute("CodeErreur","UserNonAdminOuNonProp");
        }
    }

    @SuppressWarnings("unchecked")
    private void getUsers(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
//        users = (Map<String, User>) request.getAttribute("users");
        String nameUser = (String) request.getAttribute("loggedUserUrl");
        System.out.println("nameUser: " + nameUser);
        User user = users.get(nameUser);
        if (!user.isAdmin()){
            response.sendError(403, "Utilisateur non administrateur");
            return;
        }
        String rootUrl = request.getRequestURL().toString().split("/users")[0];
        UsersDTO usersDTO = new UsersDTO(users.values(), rootUrl);
        request.setAttribute("DTO", usersDTO);
        request.setAttribute("Vu","/users/users");
        response.setStatus(200);

    }

}

