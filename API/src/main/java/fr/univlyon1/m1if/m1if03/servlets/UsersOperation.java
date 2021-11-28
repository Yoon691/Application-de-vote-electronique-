package fr.univlyon1.m1if.m1if03.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univlyon1.m1if.m1if03.classes.User;
import fr.univlyon1.m1if.m1if03.dto.UserDTO;
import fr.univlyon1.m1if.m1if03.utils.ElectionM1if03JwtHelper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UsersOperation", urlPatterns = {})
public class UsersOperation extends HttpServlet {
    Map<String, User> users;

    @SuppressWarnings("unchecked")
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        users = (Map<String, User>) config.getServletContext().getAttribute("users");

    }
    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getRequestURI().replace(req.getContextPath() + "/users/", "");
        String uri = req.getRequestURL().toString().split("/users")[1];
        System.out.println("uri: " + uri);
//        Map<String, User> users = (Map<String, User>) req.getAttribute("users");
        switch (uri){
            case "/login":
                try {
                    UserDTO userdto = new ObjectMapper().readValue(req.getReader(), UserDTO.class);
                    System.out.println("case \"/login\":");
                    if(userdto!=null) {
                        System.out.println("If 1");
                        User user;
                        if(users.containsKey(userdto.getLogin())){
                            System.out.println("If 2");
                            user = users.get(userdto.getLogin());
                        }
                        else{
                            System.out.println("else ");
                            user = new User(userdto.getLogin(), userdto.getNom(), userdto.getAdmin());
                            users.put(userdto.getLogin(), user);
                        }

                        String url = req.getRequestURL().toString().split("/login")[0].concat("/" + user.getLogin());
                        String token = ElectionM1if03JwtHelper.generateToken(userdto.getLogin(), userdto.getAdmin(), req);
                        token = ("Bearer ").concat(token);
                        System.out.println(token);
                        resp.setHeader("Authorization", token);
                        resp.setHeader("Location", url);
                        resp.setStatus(204);
                    }
                }
                catch (Exception e){
                    resp.sendError(400, "Paramètres de la requête non acceptables ");
                }
                break;
            case "/logout":
                resp.setHeader("Authorization",null);
                //response.setHeader("Location",getRootUrl(request));
                resp.setStatus(204);
                break;

            default:
                resp.sendError(404);
                break;
        }

    }

}

