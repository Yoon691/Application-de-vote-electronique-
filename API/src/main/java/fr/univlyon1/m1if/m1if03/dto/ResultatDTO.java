package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.Bulletin;
import fr.univlyon1.m1if.m1if03.classes.Candidat;
import fr.univlyon1.m1if.m1if03.classes.User;

import java.util.List;

public class ResultatDTO {
    private String nomCandidat;
    private Integer votes ;

    /**
     *
     */
    public ResultatDTO() {
    }

    public ResultatDTO(String nomC, final List<Bulletin> bulletins) {
        this.nomCandidat = nomC;
        int i = 0;
        for (Bulletin bulletin : bulletins){
            if (bulletin.getCandidat().getNom().equals(nomCandidat)){
                i++;
            }
        }
        this.votes = i;

    }

    public String getNomCandidat() {
        return nomCandidat;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public void setNomCandidat(String nomCandidat) {
        this.nomCandidat = nomCandidat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultatDTO resultatDTO = (ResultatDTO) o;
        return nomCandidat.equals(resultatDTO.nomCandidat) &&
                votes.equals(resultatDTO.votes);
    }
}
