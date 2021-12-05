package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.Candidat;

import java.util.ArrayList;
import java.util.Map;

public class CandidatsNomDTO extends ArrayList<String> {
    public CandidatsNomDTO(final Map<String, Candidat> candidats) {
        for (String nomCndidat : candidats.keySet()) {
            add(nomCndidat);
        }
    }
}
