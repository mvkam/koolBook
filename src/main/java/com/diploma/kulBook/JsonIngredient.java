package com.diploma.kulBook;

import com.diploma.kulBook.entities.Ingredient;

public class JsonIngredient {
    private String name;
    private String cup;
    private String glass;
    private String tableSpoon;
    private String teaSpoon;
    private String piece;

    public JsonIngredient(String name, String cup, String glass, String tableSpoon, String teaSpoon, String piece) {
        this.name = name;
        this.cup = cup;
        this.glass = glass;
        this.tableSpoon = tableSpoon;
        this.teaSpoon = teaSpoon;
        this.piece = piece;
    }

    public Ingredient jsonToIngredient() {
        return new Ingredient(this.name, Float.parseFloat(this.cup), Float.parseFloat(this.glass), Float.parseFloat(this.tableSpoon), Float.parseFloat(this.teaSpoon), Float.parseFloat(this.piece));
    }
}
