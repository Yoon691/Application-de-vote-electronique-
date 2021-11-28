package fr.univlyon1.m1if.m1if03.utils;

import fr.univlyon1.m1if.m1if03.classes.Candidat;
import fr.univlyon1.m1if.m1if03.classes.User;
import fr.univlyon1.m1if.m1if03.dto.CandidatsDTO;
import fr.univlyon1.m1if.m1if03.dto.UserDTO;
import fr.univlyon1.m1if.m1if03.dto.UsersDTO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class HTMLContent {
    private HTMLContent() {
    }

    @SuppressWarnings("unchecked")
    public static void setContentAttributes(HttpServletRequest request, Class<?> dtoClass) {
        Map<String, User> users = (Map<String, User>) request.getAttribute("users");
        Map<String, Candidat> candidats = (Map<String, Candidat>) request.getServletContext().getAttribute("candidats");
//        Map<String, User> users = (Map<String, User>) request.getServletContext().getAttribute("users");
//        Map<String, Salle> salles = (Map<String, Salle>) request.getServletContext().getAttribute("salles");
//        GestionPassages passages = (GestionPassages) request.getServletContext().getAttribute("passages");

        String[] splitedFieldTypeStr = dtoClass.toString()
                .replace("class ", "").split("\\.");
        String dtoClassStr = splitedFieldTypeStr[splitedFieldTypeStr.length - 1];

        switch (dtoClassStr) {
            case "UserDTO":
                UserDTO userDTO = (UserDTO) request.getAttribute("DTO");
                User userContent = users.get(userDTO.getLogin());
                request.setAttribute("user", userContent);
                break;
            case "UsersDTO":
                UsersDTO usersDTO = (UsersDTO) request.getAttribute("DTO");
                List<User> usersContent = new ArrayList<>();
                for (String userUrl : usersDTO) {
                    String userId = request.getRequestURL().toString().split("/users")[1];
                    usersContent.add(users.get(userId));
                }

                request.setAttribute("users", usersContent);
                break;

            case "CandidatsDTO":
                System.out.println("CandidatsDTO");
                CandidatsDTO candidatsDTO = (CandidatsDTO) request.getAttribute("DTO");
                List<Candidat> candidatsContent = new ArrayList<>();
                for (String passageUrl : candidatsDTO) {
//                    String passageId = getIdFromUrl(passageUrl);
                    candidatsContent.add(candidats.get(passageUrl));
                }
                System.out.println("CandidatsDTO 2 ");
                request.setAttribute("candidatContent", candidatsContent);

                break;
        }
    }
}
