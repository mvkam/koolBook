package com.diploma.kulBook.services;

import com.diploma.kulBook.entities.IngredientAmount;
import com.diploma.kulBook.repositories.IngredientAmountRepository;
import com.diploma.kulBook.repositories.IngredientRepository;
import com.diploma.kulBook.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredientAmountService {

    @Autowired
    IngredientAmountRepository ingredientAmountRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Transactional
    public void addIngredientAndAmount(IngredientAmount ingredientAmount) {

        ingredientAmountRepository.save(ingredientAmount);

    }

    @Transactional
    public boolean isUsedIngredient(Long ingredientId) {
        System.out.println("id in method in service:   " + ingredientId);

        return ingredientAmountRepository.isUsedIngredient(ingredientId);
    }

    @Transactional
    public void deleteIngredientAmount(Long recipeId) {

        ingredientAmountRepository.deleteIngredientAmountByRecipe(recipeId);

    }

}
