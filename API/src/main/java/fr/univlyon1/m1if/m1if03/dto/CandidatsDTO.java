package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.Candidat;
import fr.univlyon1.m1if.m1if03.classes.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CandidatsDTO extends ArrayList<String> {

    public CandidatsDTO(final Map<String, Candidat> candidats, final String rootUrl) {
        for (int i  = 0; i < candidats.size(); i++) {
                add(rootUrl.concat("/candidats/").concat(Integer.toString(i)));
        }
    }
}
