package com.diploma.kulBook.repositories;

import com.diploma.kulBook.entities.Recipes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipes, Long> {

    @Modifying
    @Query("update Recipes r set  r.nameOfCourse = :nameOfCourse, r.category = :category, r.instruction = :instruction, r.output = :output, r.numberOfServings = :servings, r.access = :a where r.id = :resId")
    void updateRecipe(@Param("resId") Long resId, @Param("nameOfCourse") String nameOfCourse, @Param("category") String category, @Param("instruction") String instruction, @Param("output") Float output, @Param("servings") Integer servings, @Param("a") Boolean a);

    @Query("SELECT r.nameOfCourse FROM Recipes r")
    List<String> findAllNames(Pageable pageable);

    @Query("SELECT r.nameOfCourse FROM Recipes r")
    List<String> findAllNames();

    @Query("SELECT r FROM Recipes r where r.nameOfCourse = :nameOfCourse")
    Recipes findByNameOfCourse(@Param("nameOfCourse") String nameOfCourse);

    @Query("SELECT r.nameOfCourse FROM Recipes r where r.access = true")
    List<String> findByAccess(Pageable pageable);

    @Query("SELECT r.nameOfCourse FROM Recipes r where r.access = true or r.custUser.id = :userId")
    List<String> findByUserAndAccess(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT r.nameOfCourse FROM Recipes r where r.custUser.id = :userId")
    List<String> findByUser(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT r.nameOfCourse FROM Recipes r where r.category = :Category")
    List<String> findByCategory(@Param("Category") String category, Pageable pageable);

    @Query("SELECT r.nameOfCourse FROM Recipes r where r.category = :Category and r.access = true")
    List<String> findByCategoryAndAccess(@Param("Category") String category, Pageable pageable);

    @Query("SELECT r.nameOfCourse FROM Recipes r where r.category = :Category and (r.access = true or r.custUser.id = :userId)")
    List<String> findByCategoryAndAccessAndUser(@Param("Category") String category, @Param("userId") Long userId, Pageable pageable);

    @Query("SELECT r.nameOfCourse FROM Recipes r where r.category = :Category and r.custUser.id = :userId")
    List<String> findByCategoryAndUser(@Param("Category") String category, @Param("userId") Long userId, Pageable pageable);

    @Query("SELECT r.nameOfCourse FROM Recipes r WHERE r.nameOfCourse LIKE concat('%', :word, '%') and r.access = true")
    List<String> findRecipeWithAccess(@Param("word") String word, Pageable pageable);

    @Query("SELECT r.nameOfCourse FROM Recipes r WHERE r.nameOfCourse LIKE concat('%', :word, '%')")
    List<String> findRecipeByName(@Param("word") String word, Pageable pageable);

    @Query("SELECT r.nameOfCourse FROM Recipes r WHERE r.nameOfCourse LIKE concat('%', :word, '%') and (r.access = true or r.custUser.id = :userId)")
    List<String> findRecipeWithAccessAndByUser(@Param("word") String word, @Param("userId") Long userId, Pageable pageable);

    @Query(value = "select name_of_course from recipes where id in (select recipe_id from ingredient_amount where ingredient_id in (select id from ingredient where name like concat('%', :word, '%')) and access = true)", nativeQuery = true)
    List<String> findRecipeByIngredientWithAccess(@Param("word") String word, Pageable pageable);

    @Query(value = "select name_of_course from recipes where id in (select recipe_id from ingredient_amount where ingredient_id in (select id from ingredient where name like concat('%', :word, '%')))", nativeQuery = true)
    List<String> findRecipeByIngredient(@Param("word") String word, Pageable pageable);

    @Query(value = "select name_of_course from recipes where id in (select recipe_id from ingredient_amount where ingredient_id in (select id from ingredient where name like concat('%', :word, '%')) and (access = true or custuser_id = :userId))", nativeQuery = true)
    List<String> findRecipeByIngredientWithAccessAndByUser(@Param("word") String word, @Param("userId") Long userId, Pageable pageable);

    @Query("select COUNT(r)  FROM Recipes r")
    long countAll();

    long countByAccess(boolean access);

    @Query("select COUNT(r.nameOfCourse)  FROM Recipes r WHERE r.custUser.id = :userId or r.access = true")
    long countByUserAndAccess(@Param("userId") Long userId);

    @Query("select COUNT(r.nameOfCourse)  FROM Recipes r WHERE r.custUser.id = :userId")
    long countByUserId(@Param("userId") Long userId);

    long countRecipesByCategory(String category);

    long countRecipesByCategoryAndAccess(String category, boolean access);

    @Query("select COUNT(r.nameOfCourse)  FROM Recipes r WHERE r.category = :word and (r.access = true or r.custUser.id = :userId)")
    long countRecipesByCategoryAndAccessAndUser(@Param("word") String word, @Param("userId") Long userId);

    @Query("select COUNT(r.nameOfCourse)  FROM Recipes r WHERE r.category = :word and r.custUser.id = :userId")
    long countByCategoryAndUser(@Param("word") String word, @Param("userId") Long userId);

    @Query("select COUNT(r.nameOfCourse)  FROM Recipes r WHERE r.nameOfCourse LIKE concat('%', :word, '%') and r.access = true")
    long countRecipesFoundByNameOfCourseAndAccess(@Param("word") String word);

    @Query("select COUNT(r.nameOfCourse)  FROM Recipes r WHERE r.nameOfCourse LIKE concat('%', :word, '%')")
    long countRecipesFoundByNameOfCourse(@Param("word") String word);

    @Query("select COUNT(r.nameOfCourse)  FROM Recipes r WHERE r.nameOfCourse LIKE concat('%', :word, '%') and (r.access = true or r.custUser.id = :userId)")
    long countRecipesFoundByNameOfCourseAndAccessAndByUser(@Param("word") String word, @Param("userId") Long userId);

    @Query(value = "select count(name_of_course) from recipes where id in (select recipe_id from ingredient_amount where ingredient_id in (select id from ingredient where name like concat('%', :word, '%')) and access = true)", nativeQuery = true)
    long countRecipesFoundByIngredientAndAccess(@Param("word") String word);

    @Query(value = "select count(name_of_course) from recipes where id in (select recipe_id from ingredient_amount where ingredient_id in (select id from ingredient where name like concat('%', :word, '%')))", nativeQuery = true)
    long countRecipesFoundByIngredient(@Param("word") String word);

    @Query(value = "select count(name_of_course) from recipes where id in (select recipe_id from ingredient_amount where ingredient_id in (select id from ingredient where name like concat('%', :word, '%')) and (access = true or custuser_id = :userId))", nativeQuery = true)
    long countRecipesFoundByIngredientAndAccessAndByUser(@Param("word") String word, @Param("userId") Long userId);

    @Query("update Recipes r set  r.access = true, r.custUser.id = null where r.custUser.id = :userId")
    @Modifying
    void changeAccessByUserId(@Param("userId") Long userId);
}
