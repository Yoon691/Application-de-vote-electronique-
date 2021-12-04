package fr.univlyon1.m1if.m1if03.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
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
//        users = (Map<String, User>) config. request.getAttribute("users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURL().toString().split("/users/")[1];
        System.out.println("uri : " + uri);
        if (uri.equals("list")){
            getUsers(req, resp);
        } else {
            getUser(req,resp,uri);
        }
        //super.doGet(req, resp);

    }
    @SuppressWarnings("unchecked")
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
                String uri = req.getRequestURL().toString().split("/users/")[1];
                System.out.println("uri Put user: " + uri );
                String nom = uri.split("/")[0];
                System.out.println("nom: " + nom);
                User user = users.get(nom);
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
                    NomUserDTO usernomdto = new ObjectMapper().readValue(req.getReader(), NomUserDTO.class);
                    System.out.println("usernomdto: " + usernomdto.getNom());
                    System.out.println("usernomdto != null");
                    user.setNom(usernomdto.getNom());
                    System.out.println("user.getnom: " + user.getNom());
                    users.put(nom,user);
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
            response.sendError(404,  "Utilisateur non trouvé");
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

