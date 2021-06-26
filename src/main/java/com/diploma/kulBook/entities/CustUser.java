package com.diploma.kulBook.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CustUser extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    private String email;
    private String phone;

    @OneToMany(mappedBy = "custUser")
    private Set<Recipes> recipes;


    public CustUser(String login, String password, UserRole role, String email, String phone) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
    }

    public CustUser() {
    }

    public Set<Recipes> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipes> recipes) {
        this.recipes = recipes;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
