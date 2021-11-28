package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.User;

public class NomUserDTO {
    private String nom;

    public  NomUserDTO(){
    }

    public NomUserDTO(final User user ){
        nom = user.getNom();
    }

    public String getNom() {
        return nom;
    }

}
