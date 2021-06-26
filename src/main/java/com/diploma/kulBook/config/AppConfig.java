package com.diploma.kulBook.config;

import com.diploma.kulBook.entities.*;
import com.diploma.kulBook.services.CustUserService;
import com.diploma.kulBook.services.IngredientAmountService;
import com.diploma.kulBook.services.IngredientService;
import com.diploma.kulBook.services.RecipeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig extends GlobalMethodSecurityConfiguration {

    public static final String ADMIN = "admin";
    public static final int ITEMS_PER_PAGE = 7;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner demo(final CustUserService custUserService,
                                  final PasswordEncoder encoder) {
        return strings -> {
            custUserService.addUser(ADMIN,
                    encoder.encode("password"),
                    UserRole.ADMIN, "", "");
            custUserService.addUser("user",
                    encoder.encode("password"),
                    UserRole.USER, "", "");
            custUserService.addUser("mvkam",
                    encoder.encode("gjlkjcnm"),
                    UserRole.USER, "mvkam@ukr.net", "+380968446688");
        };
    }

    @Bean
    public CommandLineRunner insertIng(final IngredientService ingredientService) {
        return strings -> {
            ingredientService.addIngredient("Peanuts", 175f, 140f, 25f, 8f, 0f);
            ingredientService.addIngredient("Jam", 330f, 270f, 50f, 17f, 0f);
            ingredientService.addIngredient("Water", 250f, 200f, 18f, 5f, 0f);
            ingredientService.addIngredient("Ground cloves", 0f, 0f, 0f, 3f, 0f);
            ingredientService.addIngredient("Split peas", 230f, 205f, 25f, 5f, 0f);
            ingredientService.addIngredient("Unpeeled peas", 200f, 175f, 0f, 0f, 0f);
            ingredientService.addIngredient("Mustard", 0f, 0f, 0f, 4f, 0f);
            ingredientService.addIngredient("Gelatin powder", 0f, 0f, 15f, 5f, 0f);
            ingredientService.addIngredient("Raisins", 190f, 155f, 25f, 7f, 0f);
            ingredientService.addIngredient("Cabbage", 0f, 0f, 0f, 0f, 1500f);
            ingredientService.addIngredient("Potato", 0f, 0f, 0f, 0f, 100f);
            ingredientService.addIngredient("Citric Acid", 0f, 0f, 25f, 8f, 0f);
            ingredientService.addIngredient("Strawberry", 150f, 120f, 25f, 5f, 0f);
            ingredientService.addIngredient("Powdered cinnamon", 0f, 0f, 20f, 8f, 0f);
            ingredientService.addIngredient("Ground coffee", 0f, 0f, 20f, 7f, 0f);
            ingredientService.addIngredient("Rolled oats", 70f, 50f, 12f, 3f, 0f);
            ingredientService.addIngredient("Buckwheat", 210f, 165f, 25f, 7f, 0f);
            ingredientService.addIngredient("Semolina", 200f, 160f, 25f, 8f, 0f);
            ingredientService.addIngredient("Pearl barley", 230f, 180f, 25f, 8f, 0f);
            ingredientService.addIngredient("Cereal grain barley", 180f, 145f, 20f, 5f, 0f);
            ingredientService.addIngredient("Corn flour", 160f, 130f, 30f, 10f, 0f);
            ingredientService.addIngredient("Liqueur", 0f, 0f, 20f, 7f, 0f);
            ingredientService.addIngredient("Onion", 0f, 0f, 0f, 0f, 75f);
            ingredientService.addIngredient("Poppy", 155f, 135f, 18f, 5f, 0f);
            ingredientService.addIngredient("Raspberry", 140f, 110f, 20f, 5f, 0f);
            ingredientService.addIngredient("Melted margarine", 230f, 180f, 15f, 4f, 0f);
            ingredientService.addIngredient("Melted animal fat", 240f, 185f, 17f, 5f, 0f);
            ingredientService.addIngredient("Extra-virgin olive oil", 230f, 190f, 17f, 5f, 0f);
            ingredientService.addIngredient("Melted butter", 240f, 185f, 20f, 8f, 0f);
            ingredientService.addIngredient("Honey", 325f, 265f, 35f, 12f, 0f);
            ingredientService.addIngredient("Peeled almonds", 160f, 130f, 30f, 10f, 0f);
            ingredientService.addIngredient("Condensed milk", 300f, 250f, 30f, 12f, 0f);
            ingredientService.addIngredient("Milk powder", 120f, 100f, 20f, 5f, 0f);
            ingredientService.addIngredient("Whole milk", 250f, 200f, 20f, 5f, 0f);
            ingredientService.addIngredient("Carrot", 0f, 0f, 0f, 0f, 75f);
            ingredientService.addIngredient("Potato flour", 180f, 150f, 30f, 10f, 0f);
            ingredientService.addIngredient("Starch", 200f, 160f, 30f, 10f, 0f);
            ingredientService.addIngredient("Wheat flour", 160f, 130f, 30f, 10f, 0f);
            ingredientService.addIngredient("Cucumber", 0f, 0f, 0f, 0f, 100f);
            ingredientService.addIngredient("Hazelnut", 170f, 130f, 30f, 10f, 0f);
            ingredientService.addIngredient("Ground black pepper", 0f, 0f, 18f, 5f, 0f);
            ingredientService.addIngredient("Parsley root", 0f, 0f, 0f, 0f, 150f);
            ingredientService.addIngredient("Tomato", 0f, 0f, 0f, 0f, 100f);
            ingredientService.addIngredient("Millet", 220f, 200f, 25f, 0f, 0f);
            ingredientService.addIngredient("Berry puree", 350f, 290f, 50f, 17f, 0f);
            ingredientService.addIngredient("Rice", 230f, 180f, 25f, 8f, 0f);
            ingredientService.addIngredient("Sago", 180f, 160f, 20f, 6f, 0f);
            ingredientService.addIngredient("Lump sugar", 200f, 140f, 0f, 0f, 0f);
            ingredientService.addIngredient("Powdered sugar", 180f, 140f, 25f, 10f, 0f);
            ingredientService.addIngredient("Granulated sugar", 200f, 180f, 25f, 8f, 0f);
            ingredientService.addIngredient("Beetroot", 0f, 0f, 0f, 0f, 75f);
            ingredientService.addIngredient("Cream", 250f, 210f, 25f, 10f, 0f);
            ingredientService.addIngredient("Sour Cream", 250f, 210f, 25f, 10f, 0f);
            ingredientService.addIngredient("Baking soda", 0f, 0f, 28f, 12f, 0f);
            ingredientService.addIngredient("Salt", 320f, 220f, 30f, 10f, 0f);
            ingredientService.addIngredient("Bread crumbs", 125f, 100f, 15f, 5f, 0f);
            ingredientService.addIngredient("Tomato purée", 300f, 250f, 30f, 10f, 0f);
            ingredientService.addIngredient("Vinegar", 250f, 200f, 15f, 5f, 0f);
            ingredientService.addIngredient("Cornflakes", 50f, 40f, 7f, 2f, 0f);
            ingredientService.addIngredient("Oatmeal flakes", 100f, 80f, 14f, 4f, 0f);
            ingredientService.addIngredient("Wheat flakes", 60f, 50f, 9f, 2f, 0f);
            ingredientService.addIngredient("Currant", 180f, 130f, 30f, 0f, 0f);
            ingredientService.addIngredient("Lentils", 210f, 190f, 0f, 0f, 0f);
            ingredientService.addIngredient("Egg white", 0f, 0f, 0f, 0f, 31f);
            ingredientService.addIngredient("Egg yolk", 0f, 0f, 0f, 0f, 24f);
            ingredientService.addIngredient("Powdered eggs", 180f, 100f, 25f, 10f, 0f);
            ingredientService.addIngredient("Unshelled egg", 0f, 0f, 0f, 0f, 55f);
            ingredientService.addIngredient("Clove of garlic", 0f, 0f, 0f, 0f, 3f);
            ingredientService.addIngredient("Beef", 0f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Beef broth", 250f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Tomato sauce", 300f, 250f, 30f, 10f, 0f);
            ingredientService.addIngredient("Canned diced tomatoes", 0f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Italian seasoning", 0f, 0f, 12f, 4f, 0f);
            ingredientService.addIngredient("Paprika", 0f, 0f, 6f, 2f, 0f);
            ingredientService.addIngredient("Elbow macaroni", 150f, 120f, 0f, 0f, 0f);
            ingredientService.addIngredient("Cheddar shredded", 235f, 188f, 15f, 5f, 0f);
            ingredientService.addIngredient("Parsley", 0f, 0f, 3.8f, 0f, 0f);
            ingredientService.addIngredient("Butter", 0f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Vegetable oil", 230f, 190f, 17f, 5f, 0f);
            ingredientService.addIngredient("Instant yeast", 0f, 0f, 0f, 3f, 0f);
            ingredientService.addIngredient("Meat for steak", 0f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Shaoxing wine", 250f, 200f, 18f, 5f, 0f);
            ingredientService.addIngredient("Soy sauce", 0f, 0f, 15f, 5f, 0f);
            ingredientService.addIngredient("Cornstarch", 200f, 160f, 30f, 10f, 0f);
            ingredientService.addIngredient("Ginger", 0f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Mushrooms", 0f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("White wine", 250f, 200f, 18f, 5f, 0f);
            ingredientService.addIngredient("Garlic powder", 0f, 0f, 9f, 3f, 0f);
            ingredientService.addIngredient("Dried thyme", 0f, 0f, 3f, 1f, 0f);
            ingredientService.addIngredient("Dried sage", 0f, 0f, 2f, 0.7f, 0f);
            ingredientService.addIngredient("Meat", 0f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Tomato paste", 0f, 0f, 17f, 6f, 0f);
            ingredientService.addIngredient("Flavoring for kebab", 0f, 0f, 12f, 4f, 0f);
            ingredientService.addIngredient("Mayonnaise", 230f, 0f, 14f, 5f, 0f);
            ingredientService.addIngredient("Bread", 0f, 0f, 0f, 0f, 30f);
            ingredientService.addIngredient("Cress", 0f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Ham", 0f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Crisps", 0f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Mixed greens", 0f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Basil", 25f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Parmesan grated", 90f, 72f, 6f, 2f, 0f);
            ingredientService.addIngredient("Shallot", 0f, 0f, 0f, 0f, 30f);
            ingredientService.addIngredient("Vegetable broth", 250f, 0f, 0f, 0f, 0f);
            ingredientService.addIngredient("Heavy cream", 240f, 192f, 15f, 5f, 0f);
            ingredientService.addIngredient("Bell pepper", 0f, 0f, 0f, 0f, 130f);
            ingredientService.addIngredient("Cashew", 120f, 0f, 10f, 0f, 130f);
            ingredientService.addIngredient("Nutritional yeast", 60f, 48f, 9f, 3f, 130f);
            ingredientService.addIngredient("Almond extract", 0f, 0f, 0f, 5f, 0f);
            ingredientService.addIngredient("Campari", 250f, 200f, 18f, 5f, 0f);
            ingredientService.addIngredient("Vermouth sweet", 250f, 200f, 18f, 5f, 0f);
            ingredientService.addIngredient("Gin", 250f, 200f, 18f, 5f, 0f);
            ingredientService.addIngredient("Orange peel", 250f, 200f, 18f, 5f, 0f);

        };
    }

    @Bean
    public CommandLineRunner insertResipes(final RecipeService recipeService, final IngredientService ingredientService, final CustUserService custUserService, final IngredientAmountService ingredientAmountService) {
        return strings -> {

            Recipes res = new Recipes("Goulash", "Main Courses", "In a large skillet over medium heat, heat oil. Add onion and cook until soft, about 5 minutes. Add garlic and cook until fragrant, about 1 minute more. Add ground beef and cook until no longer pink, about 6 minutes. Drain fat and return to pan. Season with salt and pepper. Add tomato paste and stir to coat, then pour in broth, tomato sauce, and diced tomatoes. Season with Italian seasoning and paprika, and stir in macaroni. Bring to a simmer and cook, stirring occasionally, until pasta is tender, about 15 minutes. Stir in cheese and remove from heat. Garnish with parsley before serving.", 6, false, 2000f);

            res.setCustUser(custUserService.findByLogin("user"));

            recipeService.addRecipe(res);

            IngredientAmount ingredientAmount = new IngredientAmount(34f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Extra-virgin olive oil"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(75f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Onion"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(6f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Clove of garlic"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(450f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Beef"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Salt"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Ground black pepper"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(10f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Tomato purée"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(300f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Beef broth"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(450f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Tomato sauce"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(450f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Canned diced tomatoes"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(4f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Italian seasoning"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(2f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Paprika"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(225f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Elbow macaroni"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(235f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Cheddar shredded"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Parsley"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Goulash"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);


            res = new Recipes("Fried eggs", "Main Courses", "Carefully break an egg into a small bowl. Heat oil in a large (and preferably non-stick) sauté pan over medium heat.  Once the pan is fully heated, carefully pour in the egg, and let it cook until the whites are completely set but the yolks are still soft.  Remove immediately and serve for sunny-side-up eggs. Or, flip the egg over and cook for an additional 10-30 seconds for over-easy eggs, or 30-60 seconds for over-medium eggs, or 1-2 minutes for over-hard eggs. Remove and serve immediately, seasoned with salt and pepper if you’d like.", 1, true, 120f);

            res.setCustUser(custUserService.findByLogin("admin"));

            recipeService.addRecipe(res);

            ingredientAmount = new IngredientAmount(1f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Unshelled egg"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fried eggs"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(16f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Butter"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fried eggs"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Ground black pepper"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fried eggs"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Salt"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fried eggs"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);


            res = new Recipes("Fast Breads' Crumpets", "Main Courses", " In a small saucepan, heat the milk, water, and oil over medium heat to about 130oF on an instant-read thermometer. Remove from the heat.  In a large bowl, and using a large spoon, stir together both flours, the sugar, salt, and yeast. Stir in the hot milk mixture, then stir vigorously for about 2 minutes to form a smooth, thick batter. Cover the bowl with plastic wrap and let the batter sit for 45 minutes. The batter will rise and become bubbly.  When the batter has risen, arrange 5 metal rings each 3- to 4-in diameter and about 11/2 in high on a griddle. Spray the inside of each ring and the griddle with flavorless nonstick cooking spray. Heat the griddle over low heat.  Use the large spoon to stir down the batter. Gently stir in the dissolved baking soda, mixing well. Drop about 1/4 cup of the batter into each hot ring; it will spread evenly. Cook the crumpets over low heat until the bottom is browned and a firm skin has formed on top, about 10 minutes. Bubbles will form on the tops as the crumpets cook, and the tops should feel firmly baked and not sticky when touched lightly with a finger. Use tongs to lift off the rings and set them aside. If the crumpets stick to the rings (they won’t if the rings are well greased), jiggle the rings gently with the tongs and the crumpets should release. Turn the crumpets over and cook just until the second sides are firmly set, a minute or less. The insides of the crumpets will be soft. Transfer to a plate and serve, or keep warm in a low oven (250oF) while the second batch cooks. Before you cook the second batch, gently stir down the batter (it will bubble up and rise while the first batch cooks) and spray the rings and griddle again.", 6, false, 900f);

            res.setCustUser(custUserService.findByLogin("user"));

            recipeService.addRecipe(res);

            ingredientAmount = new IngredientAmount(250f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Whole milk"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fast Breads' Crumpets"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(250f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Water"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fast Breads' Crumpets"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(17f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Vegetable oil"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fast Breads' Crumpets"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(280f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Wheat flour"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fast Breads' Crumpets"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(25f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Granulated sugar"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fast Breads' Crumpets"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(15f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Salt"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fast Breads' Crumpets"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(7.5f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Instant yeast"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fast Breads' Crumpets"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(12f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Baking soda"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fast Breads' Crumpets"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Butter"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Fast Breads' Crumpets"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            res = new Recipes("Easy Stir-Fried Beef With Mushrooms and Butter", "Main Courses", " Place the beef in a large bowl. Add the salt, sugar, ground black pepper, Shaoxing wine, soy sauce, 1 teaspoon oil, and cornstarch. Mix well and set aside for 30 minutes.  When ready to cook, heat 2 tablespoons oil in a wok over high heat until smoking. Add the ginger. Cook for 30 seconds and then remove and discard the ginger.  If the wok is no longer smoking, reheat until it is, then add the beef. Spread the beef out with the spatula, cook without moving until lightly browned, about 1 minute. Continue to cook while stirring regularly until half cooked, about 2 minutes longer. Transfer to a bowl and set aside.  Heat 1 tablespoon oil in wok over high heat until smoking. Add the mushrooms. Stir and cook the mushrooms until they start releasing their water. Continue cooking, stirring frequently, until the water evaporates. Depending on the type of mushrooms you use, this can take 5 minutes or more.  Once the water evaporates, add 2 teaspoons of soy sauce. Stir and add in the butter and garlic. Toss the butter with the mushrooms until fragrant, about 1 minute, then return the beef to the wok. Cook, stirring, until beef is cooked through, about 1 minute longer. Transfer to a serving platter immediately and serve with white rice.", 2, true, 500f);

            res.setCustUser(custUserService.findByLogin("user"));

            recipeService.addRecipe(res);

            ingredientAmount = new IngredientAmount(225f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Meat for steak"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Easy Stir-Fried Beef With Mushrooms and Butter"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(2.5f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Salt"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Easy Stir-Fried Beef With Mushrooms and Butter"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(2f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Granulated sugar"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Easy Stir-Fried Beef With Mushrooms and Butter"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0.6f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Ground black pepper"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Easy Stir-Fried Beef With Mushrooms and Butter"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(2.5f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Shaoxing wine"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Easy Stir-Fried Beef With Mushrooms and Butter"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(12.5f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Soy sauce"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Easy Stir-Fried Beef With Mushrooms and Butter"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(56f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Vegetable oil"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Easy Stir-Fried Beef With Mushrooms and Butter"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(5f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Cornstarch"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Easy Stir-Fried Beef With Mushrooms and Butter"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(25f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Ginger"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Easy Stir-Fried Beef With Mushrooms and Butter"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(225f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Mushrooms"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Easy Stir-Fried Beef With Mushrooms and Butter"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(20f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Butter"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Easy Stir-Fried Beef With Mushrooms and Butter"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(6f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Clove of garlic"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Easy Stir-Fried Beef With Mushrooms and Butter"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            res = new Recipes("Roasted French-Style Potatoes", "Main Courses", """
                    Preheat oven to 400 degrees F.

                    Cut potatoes into French-style strips.

                    In a large bowl, place all ingredients including potatoes and mix well.

                    Pour onto a sheet pan and roast for 15 minutes. Turn potatoes and roast for another 15-30 minutes until cooked and browned. Cooking time will vary depending on the thickness of the potatoes.

                    Brush the tops with oil and broil until browned. Remove, brush with more oil and sprinkle on kosher or sea salt.""", 3, false, 800f);

            res.setCustUser(custUserService.findByLogin("admin"));

            recipeService.addRecipe(res);

            ingredientAmount = new IngredientAmount(600f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Potato"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Roasted French-Style Potatoes"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(30f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Extra-virgin olive oil"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Roasted French-Style Potatoes"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(30f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("White wine"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Roasted French-Style Potatoes"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(1.5f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Garlic powder"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Roasted French-Style Potatoes"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(5f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Salt"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Roasted French-Style Potatoes"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(1.25f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Ground black pepper"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Roasted French-Style Potatoes"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0.25f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Dried thyme"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Roasted French-Style Potatoes"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0.2f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Dried sage"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Roasted French-Style Potatoes"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            res = new Recipes("Shish Kebab in the Oven bag", "Main Courses", """
                    Meat is washed carefully and cut into pieces. Then put everything into a large cup, add salt, pepper and spices.
                    Then we clean and cut onions in half rings. Put them into the meat, squeezing the juice out of it.
                    Into a cup of meat pour tomato paste. Everything should be mixed well and left for 1 hour.
                    In the requested time we take wooden skewers and string meat onto them. After this, gently put everything into the sleeve for the oven bag. They should lie exactly in a row, you do not need to arrange them one on the other.
                    Preheat the oven up to 180 degrees and set to bake our dish for 30 minutes.""", 4, true, 1000f);

            res.setCustUser(custUserService.findByLogin("user"));

            recipeService.addRecipe(res);

            ingredientAmount = new IngredientAmount(1000f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Meat"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Shish Kebab in the Oven bag"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(75f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Onion"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Shish Kebab in the Oven bag"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(170f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Tomato paste"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Shish Kebab in the Oven bag"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(30f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Powdered sugar"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Shish Kebab in the Oven bag"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(30f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Powdered sugar"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Shish Kebab in the Oven bag"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            res = new Recipes("Egg & cress club sandwich", "Appetizers", "Bring a pan of water to the boil and carefully lower in the eggs. Cook for 6 mins, then cool under running water until they can be peeled. Peel the eggs, then leave to cool completely. " +
                    "Mash or chop the eggs, then mix with 1½ tbsp mayonnaise and some seasoning, if you like. Toast the bread. " +
                    "Lay one slice of bread on a board. Butter it, then spread on three quarters of the egg and scatter over the cress. Add another slice of toast and gently spread on the remaining mayo. Add the tomato or lettuce and ham or cheese (or whichever combination you prefer). Dot the remaining egg over the top, spread gently, then top with the final piece of toast. Cut the crusts off if you like, then gently cut the sandwich into four quarters, being careful not to squash out the egg. Skewer each sandwich with a sandwich pick. Serve with crisps.",
                    1, true, 300f);

            res.setCustUser(custUserService.findByLogin("user"));

            recipeService.addRecipe(res);

            ingredientAmount = new IngredientAmount(110f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Unshelled egg"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Egg & cress club sandwich"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(28f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Mayonnaise"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Egg & cress club sandwich"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(90f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Bread"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Egg & cress club sandwich"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(10f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Butter"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Egg & cress club sandwich"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(25f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Cress"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Egg & cress club sandwich"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(50f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Tomato"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Egg & cress club sandwich"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(30f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Ham"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Egg & cress club sandwich"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Crisps"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Egg & cress club sandwich"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            res = new Recipes("Perfect Simple Salad", "Salads", "Wash the greens and spin dry if you like, then lay them out on a towel to air-dry for a little while. " +
                    "Tear up your greens if you think they will be too big to spear and eat gracefully. " +
                    "No matter how you serve your salad, it's best to toss it in a really big bowl — much bigger than the volume of the green themselves. " +
                    "Here I added about 1/3 cup grated carrot (I didn't peel the carrot, and I grated it straight into the salad) and a small handful chiffonaded basil. " +
                    "For this salad dressing, whisk 2 tablespoons good olive oil with 2 teaspoons balsamic vinegar until thick and emulsified. " +
                    "Whisk in 1/2 teaspoon honey and blend. " +
                    "Taste the dressing and adjust as needed. " +
                    "Drizzle the salad very lightly with dressing, just enough to moisten the lettuce, and work it in with your hands or two forks, stopping to toss it before you add all the dressing you've made. You want to coat the greens very, very lightly. " +
                    "As you toss the salad with your hands or forks, sprinkle on salt and pepper. Taste and adjust as needed. " +
                    "Serve the salad in individual bowls, or on plates. Garnish with some pepper, a shaving of cheese, or some fruit or nuts.", 1, true, 250f);

            res.setCustUser(custUserService.findByLogin("user"));

            recipeService.addRecipe(res);

            ingredientAmount = new IngredientAmount(120f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Mixed greens"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Perfect Simple Salad"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(25f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Carrot"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Perfect Simple Salad"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(25f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Basil"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Perfect Simple Salad"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(34f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Extra-virgin olive oil"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Perfect Simple Salad"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(10f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Vinegar"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Perfect Simple Salad"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(6f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Honey"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Perfect Simple Salad"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Salt"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Perfect Simple Salad"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Ground black pepper"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Perfect Simple Salad"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Parmesan grated"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Perfect Simple Salad"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            res = new Recipes("Summer Tomato Soup", "Soups", "SoupsCut 3 pounds ripe tomatoes in half around the equator. Fit a fine-mesh strainer over a large bowl. Use your finger to scoop the tomato seeds into the strainer and collect the juices below (about 1/2 cup juice). Discard the tomato seeds. Place a box grater into the bowl and grate the cut-side of the tomato halves on the small holes of the grater. Discard the skins. You should have about 4 cups tomato pulp and juice. Finely chop 1 medium shallot (about 1/4 cup) and add to the pulp. " +
                    "Fit a gallon-sized freezer zip-top bag inside the insert of an Instant Pot or electric pressure cooker. Add the shallot and tomato pulp, 1/4 cup fresh basil leaves, 1 cup low-sodium vegetable broth, and 1 tablespoon kosher salt to the bag. Seal the bag, pressing out as much air as possible. Freeze (with the bag still inside the insert) until solid, at least 6 hours or overnight. Remove the bag from the insert and keep frozen up to 3 months. " +
                    "To cook, remove the frozen ingredients from the bag and place in the Instant Pot. Lock the lid on and make sure the pressure valve is closed. Set to cook under HIGH pressure for 5 minutes. It will take 20 to 22 minutes to come up to pressure. When the cook time is up, quick release the pressure. " +
                    "Use tongs to remove the basil leaves. Whisk the soup to combine. Serve with torn fresh basil leaves, freshly ground black pepper, and a drizzle of heavy cream, if desired.", 4, true, 1000f);

            res.setCustUser(custUserService.findByLogin("user"));

            recipeService.addRecipe(res);

            ingredientAmount = new IngredientAmount(1360f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Tomato"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Summer Tomato Soup"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(30f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Shallot"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Summer Tomato Soup"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(6f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Basil"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Summer Tomato Soup"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(250f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Vegetable broth"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Summer Tomato Soup"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(30f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Salt"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Summer Tomato Soup"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Ground black pepper"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Summer Tomato Soup"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Heavy cream"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Summer Tomato Soup"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            res = new Recipes("Classic Pommes Anna", "Sides", "Arrange a rack in the top third of the oven and heat the oven to 425ºF. " +
                    "Peel potatoes. Cut crosswise into 1/8-inch thick rounds. Divide the slices into 4 equal piles." +
                    "Melt butter in the microwave. Brush the bottom and sides of an oven-safe 10-inch nonstick frying pan with 1 tablespoon of the butter and sprinkle with 1/4 teaspoon of the salt. Brush a thin layer of butter onto a sheet of aluminum foil big enough to cover the pan. " +
                    "Starting in the center and overlapping the slices by about half, arrange one pile of the slices in a spiral pattern, making sure the bottom is completely and evenly covered. Use smaller slices in the center and larger slices on the edges. Brush with 1/2 tablespoon of the melted butter and sprinkle with 1/4 teaspoon of the salt. " +
                    "This time working your way from the outside in to the center (again using the larger slices on the edges), arrange the second pile of potato slices to build the second layer. Brush with 1/2 tablespoon of the butter and sprinkle with 1/4 teaspoon of the salt. " +
                    "Repeat arranging the third layer, working from the inside out, then buttering and seasoning. Build the fourth and final layer of potatoes from the outside in. Brush all the remaining butter onto the top layer and season with the remaining 1/4 teaspoon salt. " +
                    "Place the foil butter-side down onto the potatoes, then weigh down the foil by topping it with a 9-inch cake pan or pie plate. Fill the cake pan or pie plate with pie weights. Bake for 25 minutes. " +
                    "Remove the weights and foil. Bake uncovered until knife-tender and the bottom is golden brown, about 20 minutes more. " +
                    "Turn the oven to broil on high. Broil until the top is golden brown, about 5 minutes. " +
                    "Gently shake the pan to see if the potato cake slides around easily. If it doesn’t, run a thin knife around the edges and slide the spatula underneath the potato cake to loosen. Invert a cutting board over the pan. Grasping both the board and the pan (careful, hot!), flip over and remove the pan. Cut the potato cake into wedges.", 4, true, 1000f);

            res.setCustUser(custUserService.findByLogin("user"));

            recipeService.addRecipe(res);

            ingredientAmount = new IngredientAmount(900f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Potato"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Classic Pommes Anna"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(60f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Butter"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Classic Pommes Anna"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(13f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Salt"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Classic Pommes Anna"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            res = new Recipes("White Pound Cake", "Desserts", "Place the butter in a mixing bowl and work it with a wooden spoon until it becomes shiny, about 5 minutes. Add in the sugar and blend until the mixture becomes light and the sugar granules almost disappear. After the mixture becomes well blended, begin to stir clockwise in a circular motion. Add a little of the flour and some of the milk, stirring well after each addition until both are used up, beginning and ending with flour. Add salt and almond extract. " +
                    "Beat the egg whites until they form soft peaks but are not dry. Fold into the batter, then spoon the batter into the tube pan. Set into a preheated 300°F oven. Place on the middle shelf and back at 300°F for 40 minutes, then turn up to 325°F for 15 minutes more. Remove from oven and run a knife around the sides of the pan. Turn out upon a wire rack, then turn cake face up. Leave to cool. After 15 minutes of cooling, cover to prevent drying out.", 8, true, 1000f);

            res.setCustUser(custUserService.findByLogin("user"));

            recipeService.addRecipe(res);

            ingredientAmount = new IngredientAmount(225f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Butter"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("White Pound Cake"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(420f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Powdered sugar"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("White Pound Cake"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(480f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Wheat flour"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("White Pound Cake"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(125f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Whole milk"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("White Pound Cake"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(3f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Salt"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("White Pound Cake"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(20f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Almond extract"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("White Pound Cake"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(124f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Egg white"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("White Pound Cake"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            res = new Recipes("Classic Negroni", "Drinks", "Fill an old-fashioned glass with ice. Add the Campari, vermouth, and gin, and stir to combine. " +
                    "Run the flamed orange peel around the edge of the glass, lightly squeezing to express the oils. Drop the peel into the glass and enjoy. " +
                    "A plain orange peel works just as well here.", 1, true, 100f);

            res.setCustUser(custUserService.findByLogin("user"));

            recipeService.addRecipe(res);

            ingredientAmount = new IngredientAmount(30f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Campari"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Classic Negroni"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(30f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Vermouth sweet"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Classic Negroni"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(30f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Gin"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Classic Negroni"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Orange peel"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Classic Negroni"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            res = new Recipes("Vegan Cashew Cheese Sauce", "Sauces", "Soak the cashews overnight in cold water to soften, unless you have a high power blender such as a Vitamix. " +
                    "Combine all the ingredients in a blender, making sure the red peppers are on the bottom. Blend until a smooth sauce forms, adding a bit of water if the blender is having trouble blending. " +
                    "Transfer to a sealed container and refrigerate until ready to use. The cheese sauce will keep for up to 5 days in the fridge.", 1, true, 1000f);

            res.setCustUser(custUserService.findByLogin("user"));

            recipeService.addRecipe(res);

            ingredientAmount = new IngredientAmount(300f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Bell pepper"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Vegan Cashew Cheese Sauce"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(300f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Cashew"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Vegan Cashew Cheese Sauce"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(60f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Nutritional yeast"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Vegan Cashew Cheese Sauce"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(1000f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Meat"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Vegan Cashew Cheese Sauce"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(1000f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Meat"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Vegan Cashew Cheese Sauce"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(5f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Salt"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Vegan Cashew Cheese Sauce"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(1000f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Meat"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Vegan Cashew Cheese Sauce"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

        };
    }
}
