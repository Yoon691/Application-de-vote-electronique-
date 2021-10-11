package fr.univlyon1.m1if.m1if03.cherbal.classes;

public class Bulletin {
    final Candidat candidat;

    public Bulletin(Candidat candidat) {
        this.candidat = candidat;
    }

    
    /** 
     * @return Candidat
     */
    public Candidat getCandidat() {
        return candidat;
    }
}