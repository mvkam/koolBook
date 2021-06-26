package com.diploma.kulBook.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class Recipes extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nameOfCourse;

    String category;

    @Column(length = 2000)
    String instruction;

    Integer numberOfServings;

    Boolean access;

    Float output;

    @ManyToOne
    @JoinColumn(name = "custuser_id")
    private CustUser custUser;


    @OneToMany(mappedBy = "recipe")
    private Set<IngredientAmount> ingredientAmounts;

    public Recipes() {
    }


    public Recipes(String nameOfCourse, String category, String instruction, Integer numberOfServings, Boolean access, Float output) {
        this.nameOfCourse = nameOfCourse;
        this.category = category;
        this.instruction = instruction;
        this.numberOfServings = numberOfServings;
        this.access = access;
        this.output = output;
    }

    public Long getId() {
        return id;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public CustUser getCustUser() {
        return custUser;
    }

    public void setCustUser(CustUser custUser) {
        this.custUser = custUser;
    }

    public Set<IngredientAmount> getIngredientAmounts() {
        return ingredientAmounts;
    }

    public void setIngredientAmounts(Set<IngredientAmount> ingredientAmounts) {
        this.ingredientAmounts = ingredientAmounts;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Integer getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(Integer numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    public Float getOutput() {
        return output;
    }

    public void setOutput(Float output) {
        this.output = output;
    }

}
