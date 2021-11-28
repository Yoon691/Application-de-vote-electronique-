package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.Ballot;

public class VoteUpdateByUserDTO {
    private String nom;


    /**
     *
     */
    public VoteUpdateByUserDTO() {
    }

    /**
     * @param ballot
     */
    public VoteUpdateByUserDTO(final Ballot ballot) {
        this.nom = ballot.getBulletin().getCandidat().getNom();


    }

    public void setNom(String nomCandidat) {
        this.nom = nomCandidat;
    }


    public String getNom() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteUpdateByUserDTO voteUpdateByUserDTO = (VoteUpdateByUserDTO) o;
        return nom.equals(voteUpdateByUserDTO.nom) ;
    }
}
