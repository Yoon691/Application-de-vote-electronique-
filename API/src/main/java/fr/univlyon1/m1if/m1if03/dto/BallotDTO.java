package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.Ballot;

public class BallotDTO {
    private String nomCandidat;


    /**
     *
     */
    public BallotDTO() {
    }

    /**
     * @param ballot
     */
    public BallotDTO(final Ballot ballot) {
        this.nomCandidat = ballot.getBulletin().getCandidat().getNom();


    }

    public void setNomCandidat(String nomCandidat) {
        this.nomCandidat = nomCandidat;
    }


    public String getNomCandidat() {
        return nomCandidat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BallotDTO ballotDTO = (BallotDTO) o;
        return nomCandidat.equals(ballotDTO.nomCandidat) ;
    }
}
