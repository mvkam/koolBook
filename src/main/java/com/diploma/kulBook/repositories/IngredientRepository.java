package com.diploma.kulBook.repositories;

import com.diploma.kulBook.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false " +
            "END FROM Ingredient i WHERE i.name = :name")
    boolean existsByName(@Param("name") String name);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false " +
            "END FROM Ingredient i WHERE i.name = :name and i.id <> :id")
    boolean existsByNameAndId(@Param("name") String name, @Param("id") Long id);

    @Query("SELECT i FROM Ingredient i where i.name = :name")
    Ingredient getByName(@Param("name") String name);

    @Query("SELECT i.name FROM Ingredient i")
    String[] getAllNames();

    @Query("SELECT i FROM Ingredient i")
    List<Ingredient> getAllIngredients(Pageable pageable);

    @Modifying
    @Query("update Ingredient i set i.name = :name, i.cup = :cup, i.glass = :glass, i.tableSpoon = :tableSpoon, i.teaSpoon = :teaSpoon, i.piece = :piece where i.id = :id")
    void updateIngredient(@Param("id") Long id, @Param("name") String name, @Param("cup") Float cup, @Param("glass") Float glass, @Param("tableSpoon") Float tableSpoon, @Param("teaSpoon") Float teaSpoon, @Param("piece") Float piece);

}


