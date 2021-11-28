package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.Ballot;
import fr.univlyon1.m1if.m1if03.classes.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class BallotsDTO extends ArrayList<String> {
    public BallotsDTO(final Map<String, Ballot> ballots, final String rootUrl) {
        for (int i = 0; i < ballots.size(); i++) {
            add(rootUrl.concat("/ballots/").concat(Integer.toString(i)));
        }
    }
}
