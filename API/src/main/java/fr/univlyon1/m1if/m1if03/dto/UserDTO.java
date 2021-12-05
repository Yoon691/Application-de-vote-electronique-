package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.User;

import java.util.Objects;

public class UserDTO {
    private String login;
    private String nom;
    private Boolean admin = false;

    /**
     *
     */
    public UserDTO() {
    }

    /**
     * @param user
     */
    public UserDTO(final User user) {
        this.login = user.getLogin();
        this.admin = user.isAdmin();
        this.nom = user.getNom();

    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getLogin() {
        return login;
    }

    public String getNom() {
        return nom;
    }

    public Boolean getAdmin() {
        return admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return login.equals(userDTO.login) &&
                admin.equals(userDTO.admin);
    }

}
