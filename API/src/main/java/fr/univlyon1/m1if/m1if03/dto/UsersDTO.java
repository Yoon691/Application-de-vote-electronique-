package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.User;

import java.util.ArrayList;
import java.util.Collection;

public class UsersDTO extends ArrayList<String> {

    public UsersDTO(final Collection<User> users, final String rootUrl) {
        for (User user : users) {
            add(rootUrl.concat("/users/").concat(user.getLogin()));
        }
    }

}
