package com.diploma.kulBook.repositories;

import com.diploma.kulBook.entities.IngredientAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientAmountRepository extends JpaRepository<IngredientAmount, Long> {

    @Query("SELECT IA FROM IngredientAmount IA WHERE IA.recipe.id = :recipeId")
    List<IngredientAmount> getIngredientAmountsByRecipe(@Param("recipeId") Long recipeId);

    @Query("DELETE FROM IngredientAmount IA WHERE IA.recipe.id = :recipeId")
    @Modifying
    void deleteIngredientAmountByRecipe(@Param("recipeId") Long recipeId);

    @Query("SELECT CASE WHEN COUNT(ia) > 0 THEN true ELSE false END FROM IngredientAmount ia WHERE ia.ingredient.id = :ingredientId")
    boolean isUsedIngredient(@Param("ingredientId") Long ingredientId);
}

