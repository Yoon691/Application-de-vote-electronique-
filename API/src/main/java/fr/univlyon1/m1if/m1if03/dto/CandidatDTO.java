package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.Candidat;
import fr.univlyon1.m1if.m1if03.classes.User;

public class CandidatDTO {
    private String prenom;
    private String nom;

    /**
     *
     */
    public CandidatDTO() {
    }

    /**
     * @param candidat
     */
    public CandidatDTO(final Candidat candidat) {
        this.prenom = candidat.getPrenom();
        this.nom = candidat.getNom();

    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidatDTO candidatDTO = (CandidatDTO) o;
        return prenom.equals(candidatDTO.prenom) &&
                prenom.equals(candidatDTO.nom);
    }

}
