package com.diploma.kulBook.controllers;

import com.diploma.kulBook.entities.Ingredient;
import com.diploma.kulBook.entities.IngredientAmount;
import com.diploma.kulBook.entities.Recipes;
import com.diploma.kulBook.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @RequestMapping(value = "/recipe")
    public String categorySelection(@RequestParam Integer servingAmount, @RequestParam String role, String nameOfCourse, Model model) {

        Recipes recipe = recipeService.findByNameOfCourse(nameOfCourse);
        Set<IngredientAmount> ingredientAmountSet = recipe.getIngredientAmounts();
        SimpleDateFormat dateFormat = new SimpleDateFormat("y.MM.dd");
        Map<Ingredient, Float> ingredientsOfRecipe = new HashMap<>();
        recipe.setOutput((recipe.getOutput() / recipe.getNumberOfServings()) * servingAmount);
        String login;

        for (IngredientAmount iA : ingredientAmountSet) {
            iA.setAmount((iA.getAmount() / recipe.getNumberOfServings()) * servingAmount);
            ingredientsOfRecipe.put(iA.getIngredient(), iA.getAmount());
        }

        if (recipe.getCustUser() == null)
            login = "unowned";
        else login = recipe.getCustUser().getLogin();

        recipe.setNumberOfServings(servingAmount);

        Set<Map.Entry<Ingredient, Float>> ingredientSet = ingredientsOfRecipe.entrySet();

        model.addAttribute("login", login);
        model.addAttribute("nameOfCourse", nameOfCourse);
        model.addAttribute("category", recipe.getCategory());
        model.addAttribute("dateOfCreate", dateFormat.format(recipe.getCreatedAt()));
        model.addAttribute("dateOfUpdate", dateFormat.format(recipe.getUpdatedAt()));
        model.addAttribute("ingredients", ingredientSet);
        model.addAttribute("ingredientAmounts", ingredientAmountSet);
        model.addAttribute("servings", recipe.getNumberOfServings());
        model.addAttribute("output", recipe.getOutput());
        model.addAttribute("instruction", recipe.getInstruction());

        switch (role) {
            case "guest":
                return "guest/recipe";
            case "user":
                return "user/user_recipe";
            case "admin":
                return "admin/admin_recipe";
        }
        return "login";
    }

}
