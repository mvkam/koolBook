package com.diploma.kulBook.services;

import com.diploma.kulBook.entities.Recipes;
import com.diploma.kulBook.repositories.IngredientAmountRepository;
import com.diploma.kulBook.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientAmountRepository ingredientAmountRepository;

    @Transactional
    public void addRecipe(Recipes recipe) {

        recipeRepository.save(recipe);

    }

    @Transactional
    public List<String> getAllNames(Pageable pageble) {

        return recipeRepository.findAllNames(pageble);
    }

    @Transactional
    public List<String> getAllNames() {

        return recipeRepository.findAllNames();
    }

    @Transactional
    public void deleteRecipes(List<String> recipe) {

        for (String i : recipe) {
            ingredientAmountRepository.getIngredientAmountsByRecipe(findByNameOfCourse(i).getId()).forEach(ingredientAmount -> ingredientAmountRepository.delete(ingredientAmount));
            recipeRepository.delete(findByNameOfCourse(i));
        }
    }

    @Transactional
    public void updateRecipe(Long resId, String nameOfCourse, String category, String instruction, Float output, Integer servings, Boolean a) {

        recipeRepository.updateRecipe(resId, nameOfCourse, category, instruction, output, servings, a);
    }

    @Transactional
    public Recipes findById(Long id) {
        return recipeRepository.getOne(id);
    }

    @Transactional
    public Recipes findByNameOfCourse(String nameOfCourse) {
        return recipeRepository.findByNameOfCourse(nameOfCourse);
    }

    @Transactional(readOnly = true)
    public List<String> findByAccess(Pageable pageble) {

        return recipeRepository.findByAccess(pageble);

    }

    @Transactional(readOnly = true)
    public List<String> findByUserAndAccess(Long userId, Pageable pageble) {

        return recipeRepository.findByUserAndAccess(userId, pageble);

    }

    @Transactional(readOnly = true)
    public List<String> findByUser(Long userId, Pageable pageble) {

        return recipeRepository.findByUser(userId, pageble);

    }

    @Transactional(readOnly = true)
    public List<String> findByCategory(String category, Pageable pageble) {

        return recipeRepository.findByCategory(category, pageble);

    }

    @Transactional(readOnly = true)
    public List<String> findByCategoryAndAccess(String category, Pageable pageble) {

        return recipeRepository.findByCategoryAndAccess(category, pageble);

    }

    @Transactional(readOnly = true)
    public List<String> findByCategoryAndAccessAndUser(String word, Long id, Pageable pageable) {

        return recipeRepository.findByCategoryAndAccessAndUser(word, id, pageable);

    }

    @Transactional(readOnly = true)
    public List<String> findByCategoryAndUser(String word, Long id, Pageable pageable) {

        return recipeRepository.findByCategoryAndUser(word, id, pageable);

    }

    @Transactional(readOnly = true)
    public List<String> findRecipeWithAccess(String word, Pageable pageable) {

        return recipeRepository.findRecipeWithAccess(word, pageable);
    }

    @Transactional(readOnly = true)
    public List<String> findRecipeByName(String word, Pageable pageable) {

        return recipeRepository.findRecipeByName(word, pageable);
    }

    @Transactional(readOnly = true)
    public List<String> findRecipeWithAccessAndByUser(String word, Long id, Pageable pageable) {

        return recipeRepository.findRecipeWithAccessAndByUser(word, id, pageable);
    }

    @Transactional(readOnly = true)
    public List<String> findRecipeByIngredientWithAccess(String word, Pageable pageable) {

        return recipeRepository.findRecipeByIngredientWithAccess(word, pageable);

    }

    @Transactional(readOnly = true)
    public List<String> findRecipeByIngredient(String word, Pageable pageable) {

        return recipeRepository.findRecipeByIngredient(word, pageable);

    }

    @Transactional(readOnly = true)
    public List<String> findRecipeByIngredientWithAccessAndByUser(String word, Long id, Pageable pageable) {

        return recipeRepository.findRecipeByIngredientWithAccessAndByUser(word, id, pageable);

    }

    @Transactional(readOnly = true)
    public long countAll() {
        return recipeRepository.countAll();
    }

    @Transactional(readOnly = true)
    public long countByAccess() {
        return recipeRepository.countByAccess(true);
    }

    @Transactional(readOnly = true)
    public long countByUserAndAccess(Long id) {
        return recipeRepository.countByUserAndAccess(id);
    }


    @Transactional(readOnly = true)
    public long countByCategory(String category) {
        return recipeRepository.countRecipesByCategory(category);

    }

    @Transactional(readOnly = true)
    public long countByCategoryWithAccess(String category) {
        return recipeRepository.countRecipesByCategoryAndAccess(category, true);

    }

    @Transactional(readOnly = true)
    public long countByCategoryWithAccessAndUser(String category, Long id) {

        return recipeRepository.countRecipesByCategoryAndAccessAndUser(category, id);

    }

    @Transactional(readOnly = true)

    public long countByCategoryAndUser(String category, Long id) {
        return recipeRepository.countByCategoryAndUser(category, id);

    }

    @Transactional(readOnly = true)
    public long countByUser(Long id) {
        return recipeRepository.countByUserId(id);
    }

    @Transactional(readOnly = true)
    public long countRecipesFoundByNameOfCourseAndAccess(String word) {
        return recipeRepository.countRecipesFoundByNameOfCourseAndAccess(word);
    }

    @Transactional(readOnly = true)
    public long countRecipesFoundByName(String word) {
        return recipeRepository.countRecipesFoundByNameOfCourse(word);
    }

    @Transactional(readOnly = true)
    public long countRecipesFoundByNameOfCourseAndAccessAndByUser(String word, Long id) {
        return recipeRepository.countRecipesFoundByNameOfCourseAndAccessAndByUser(word, id);
    }

    @Transactional(readOnly = true)
    public long countRecipesFoundByIngredientAndAccess(String word) {
        return recipeRepository.countRecipesFoundByIngredientAndAccess(word);
    }

    @Transactional(readOnly = true)
    public long countRecipesFoundByIngredient(String word) {
        return recipeRepository.countRecipesFoundByIngredient(word);
    }

    @Transactional(readOnly = true)
    public long countRecipesFoundByIngredientAndAccessAndByUser(String word, Long id) {
        return recipeRepository.countRecipesFoundByIngredientAndAccessAndByUser(word, id);
    }

    @Transactional
    public void changeAccessByUserId(Long id) {
        recipeRepository.changeAccessByUserId(id);
    }
}

