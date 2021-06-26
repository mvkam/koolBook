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
            ingredientService.addIngredient("Beef broth", 0f, 0f, 0f, 0f, 0f);
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
        };
    }

    @Bean
    public CommandLineRunner insertResipes(final RecipeService recipeService, final IngredientService ingredientService, final CustUserService custUserService, final IngredientAmountService ingredientAmountService) {
        return strings -> {

            Recipes res = new Recipes("Goulash", "Appetizers", "In a large skillet over medium heat, heat oil. Add onion and cook until soft, about 5 minutes. Add garlic and cook until fragrant, about 1 minute more. Add ground beef and cook until no longer pink, about 6 minutes. Drain fat and return to pan. Season with salt and pepper. Add tomato paste and stir to coat, then pour in broth, tomato sauce, and diced tomatoes. Season with Italian seasoning and paprika, and stir in macaroni. Bring to a simmer and cook, stirring occasionally, until pasta is tender, about 15 minutes. Stir in cheese and remove from heat. Garnish with parsley before serving.", 6, false, 2000f);

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

            res = new Recipes("Roasted French-Style Potatoes", "Main Courses", "Preheat oven to 400 degrees F.\n" +
                    "\n" +
                    "Cut potatoes into French-style strips.\n" +
                    "\n" +
                    "In a large bowl, place all ingredients including potatoes and mix well.\n" +
                    "\n" +
                    "Pour onto a sheet pan and roast for 15 minutes. Turn potatoes and roast for another 15-30 minutes until cooked and browned. Cooking time will vary depending on the thickness of the potatoes.\n" +
                    "\n" +
                    "Brush the tops with oil and broil until browned. Remove, brush with more oil and sprinkle on kosher or sea salt.", 3, false, 800f);

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

            res = new Recipes("Shish Kebab in the Oven bag", "Main Courses", "Meat is washed carefully and cut into pieces. Then put everything into a large cup, add salt, pepper and spices.\n" +
                    "Then we clean and cut onions in half rings. Put them into the meat, squeezing the juice out of it.\n" +
                    "Into a cup of meat pour tomato paste. Everything should be mixed well and left for 1 hour.\n" +
                    "In the requested time we take wooden skewers and string meat onto them. After this, gently put everything into the sleeve for the oven bag. They should lie exactly in a row, you do not need to arrange them one on the other.\n" +
                    "Preheat the oven up to 180 degrees and set to bake our dish for 30 minutes.", 4, true, 1000f);

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

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Salt"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Shish Kebab in the Oven bag"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

            ingredientAmount = new IngredientAmount(0f);
            ingredientAmount.setIngredient(ingredientService.getIngredient("Ground black pepper"));
            ingredientAmount.setRecipe(recipeService.findByNameOfCourse("Shish Kebab in the Oven bag"));
            ingredientAmountService.addIngredientAndAmount(ingredientAmount);

        };
    }
}
