package com.diploma.kulBook.services;

import com.diploma.kulBook.entities.Ingredient;
import com.diploma.kulBook.repositories.IngredientAmountRepository;
import com.diploma.kulBook.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    IngredientAmountRepository ingredientAmountRepository;

    @Transactional
    public void addIngredient(Ingredient ingredient) {
        if (ingredientRepository.existsByName(ingredient.getName()))
            return;
        ingredientRepository.save(ingredient);

    }

    @Transactional
    public void addIngredient(String name, Float cup, Float glass, Float tableSpoon, Float teaSpoon, Float piece) {
        if (ingredientRepository.existsByName(name))
            return;

        Ingredient ingredient = new Ingredient(name, cup, glass, tableSpoon, teaSpoon, piece);
        ingredientRepository.save(ingredient);

    }

    @Transactional
    public boolean updateIngredient(Long id, String name, Float cup, Float glass, Float tableSpoon, Float teaSpoon, Float piece) {

        if (ingredientRepository.existsByNameAndId(name, id))
            return true;

        ingredientRepository.updateIngredient(id, name, cup, glass, tableSpoon, teaSpoon, piece);

        return false;
    }

    @Transactional
    public Ingredient getIngredient(String name) {
        return ingredientRepository.getByName(name);
    }

    @Transactional
    public Ingredient getIngredientById(Long ingredientId) {

        if (ingredientRepository.findById(ingredientId).isPresent())
            return ingredientRepository.findById(ingredientId).get();
        else return null;
    }

    @Transactional
    public String[] getAllNames() {
        return ingredientRepository.getAllNames();
    }

    @Transactional
    public List<Ingredient> getAllIngredients(Pageable pageable) {
        return ingredientRepository.getAllIngredients(pageable);
    }

    @Transactional
    public Long countAll() {
        return ingredientRepository.count();
    }

    @Transactional
    public void deleteIngredients(List<Long> ids) {
        ids.forEach(id -> ingredientRepository.deleteById(id));
    }
}
