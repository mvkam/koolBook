package com.diploma.kulBook.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ingredient extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Float cup, glass, tableSpoon, teaSpoon, piece;

    @OneToMany(mappedBy = "ingredient")
    private Set<IngredientAmount> ingredientAmount;


    public Ingredient() {
    }

    public Ingredient(String name, Float cup, Float glass, Float tableSpoon, Float teaSpoon, Float piece) {
        this.name = name;
        this.cup = cup;
        this.glass = glass;
        this.tableSpoon = tableSpoon;
        this.teaSpoon = teaSpoon;
        this.piece = piece;
    }

    public void setIngredientAmount(Set<IngredientAmount> ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCup() {
        return cup;
    }

    public void setCup(Float cup) {
        this.cup = cup;
    }

    public Float getGlass() {
        return glass;
    }

    public void setGlass(Float glass) {
        this.glass = glass;
    }

    public Float getTableSpoon() {
        return tableSpoon;
    }

    public void setTableSpoon(Float tableSpoon) {
        this.tableSpoon = tableSpoon;
    }

    public Float getTeaSpoon() {
        return teaSpoon;
    }

    public void setTeaSpoon(Float teaSpoon) {
        this.teaSpoon = teaSpoon;
    }

    public Float getPiece() {
        return piece;
    }

    public void setPiece(Float piece) {
        this.piece = piece;
    }

}