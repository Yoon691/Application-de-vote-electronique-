package fr.univlyon1.m1if.m1if03.p1507426.classes;

public class Candidat {
    String prenom, nom;

    public Candidat(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    
    /** 
     * @return String
     */
    public String getPrenom() {
        return prenom;
    }

    
    /** 
     * @return String
     */
    public String getNom() {
        return nom;
    }
}