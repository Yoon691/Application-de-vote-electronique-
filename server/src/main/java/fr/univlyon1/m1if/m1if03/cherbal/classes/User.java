package fr.univlyon1.m1if.m1if03.cherbal.classes;

import java.util.Objects;

public class User {
    private final String login;
    private String nom;

    public User(String login, String nom) {
        this.login = login;
        this.nom = nom;
    }

    
    /** 
     * @return String
     */
    public String getLogin() {
        return login;
    }

    
    /** 
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    /** 
     * @return String
     */
    public String getNom() {
        return nom;
    }

    
    /** 
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login);
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}