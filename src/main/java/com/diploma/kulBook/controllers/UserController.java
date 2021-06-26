package com.diploma.kulBook.controllers;

import com.diploma.kulBook.JsonIngredient;
import com.diploma.kulBook.Mail;
import com.diploma.kulBook.config.AppConfig;
import com.diploma.kulBook.entities.*;
import com.diploma.kulBook.services.CustUserService;
import com.diploma.kulBook.services.IngredientAmountService;
import com.diploma.kulBook.services.IngredientService;
import com.diploma.kulBook.services.RecipeService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class UserController {

    @Autowired
    private CustUserService userService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientAmountService ingredientAmountService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "user/main-user", method = RequestMethod.GET)
    public String UserPage(@RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        User user = getCurrentUser();
        String login = user.getUsername();
        CustUser cu = userService.findByLogin(login);

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        model.addAttribute("category", "All Recipes");
        model.addAttribute("recipes", recipeService.findByUserAndAccess(cu.getId(), pageRequest));
        model.addAttribute("allPage", getPageCountWithUserAndAccess(cu.getId()));
        model.addAttribute("pageNo", pageNo);

        return "user/main-user";
    }

    @RequestMapping(value = "user/my_recipes", method = RequestMethod.GET)
    public String MyRecipes(@RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        User user = getCurrentUser();
        String login = user.getUsername();
        CustUser cu = userService.findByLogin(login);

        System.out.println("login: " + login);

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        model.addAttribute("category", "My Recipes");
        model.addAttribute("recipes", recipeService.findByUser(cu.getId(), pageRequest));
        model.addAttribute("allPage", getPageCountMyRecipes(cu.getId()));
        model.addAttribute("pageNo", pageNo);

        return "user/user_my_recipes";
    }

    @RequestMapping(value = "user/public_recipes", method = RequestMethod.GET)
    public String PublicRecipes(@RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        model.addAttribute("category", "Public Recipes");
        model.addAttribute("recipes", recipeService.findByAccess(pageRequest));
        model.addAttribute("allPage", getPageCountWithAccess());
        model.addAttribute("pageNo", pageNo);

        return "user/user_public_recipes";
    }

    @RequestMapping(value = "/user/category_user")
    public String userCategorySelection(@RequestParam String category, @RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        User user = getCurrentUser();
        String login = user.getUsername();
        CustUser cu = userService.findByLogin(login);

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        switch (category) {
            case "all":
                model.addAttribute("category", "All Recipes");
                model.addAttribute("recipes", recipeService.findByUserAndAccess(cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountWithUserAndAccess(cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Appetizers":
                model.addAttribute("category", "Appetizers");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccessAndUser("Appetizers", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccessAndUser("Appetizers", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Salads":
                model.addAttribute("category", "Salads");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccessAndUser("Salads", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccessAndUser("Salads", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Soups":
                model.addAttribute("category", "Soups");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccessAndUser("Soups", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccessAndUser("Soups", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Main Courses":
                model.addAttribute("category", "Main Courses");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccessAndUser("Main Courses", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccessAndUser("Main Courses", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Sides":
                model.addAttribute("category", "Sides");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccessAndUser("Sides", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccessAndUser("Sides", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Sauces":
                model.addAttribute("category", "Sauces");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccessAndUser("Sauces", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccessAndUser("Sauces", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Desserts":
                model.addAttribute("category", "Desserts");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccessAndUser("Desserts", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccessAndUser("Desserts", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Drinks":
                model.addAttribute("category", "Drinks");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccessAndUser("Drinks", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccessAndUser("Drinks", cu.getId()));
                model.addAttribute("pageNo", pageNo);

        }
        return "user/category_user";
    }

    @RequestMapping(value = "/user/my_category")
    public String myCategorySelection(@RequestParam String category, @RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        User user = getCurrentUser();
        String login = user.getUsername();
        CustUser cu = userService.findByLogin(login);

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        switch (category) {
            case "all":
                model.addAttribute("category", "All Recipes");
                model.addAttribute("recipes", recipeService.findByUser(cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountMyRecipes(cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Appetizers":
                model.addAttribute("category", "Appetizers");
                model.addAttribute("recipes", recipeService.findByCategoryAndUser("Appetizers", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryAndUser("Appetizers", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Salads":
                model.addAttribute("category", "Salads");
                model.addAttribute("recipes", recipeService.findByCategoryAndUser("Salads", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryAndUser("Salads", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Soups":
                model.addAttribute("category", "Soups");
                model.addAttribute("recipes", recipeService.findByCategoryAndUser("Soups", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryAndUser("Soups", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Main Courses":
                model.addAttribute("category", "Main Courses");
                model.addAttribute("recipes", recipeService.findByCategoryAndUser("Main Courses", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryAndUser("Main Courses", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Sides":
                model.addAttribute("category", "Sides");
                model.addAttribute("recipes", recipeService.findByCategoryAndUser("Sides", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryAndUser("Sides", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Sauces":
                model.addAttribute("category", "Sauces");
                model.addAttribute("recipes", recipeService.findByCategoryAndUser("Sauces", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryAndUser("Sauces", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Desserts":
                model.addAttribute("category", "Desserts");
                model.addAttribute("recipes", recipeService.findByCategoryAndUser("Desserts", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryAndUser("Desserts", cu.getId()));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Drinks":
                model.addAttribute("category", "Drinks");
                model.addAttribute("recipes", recipeService.findByCategoryAndUser("Drinks", cu.getId(), pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryAndUser("Drinks", cu.getId()));
                model.addAttribute("pageNo", pageNo);

        }
        return "user/user_my_category";
    }

    @RequestMapping(value = "/user/public_category")
    public String publicCategorySelection(@RequestParam String category, @RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        switch (category) {
            case "all":
                model.addAttribute("category", "All Recipes");
                model.addAttribute("recipes", recipeService.findByAccess(pageRequest));
                model.addAttribute("allPage", getPageCountWithAccess());
                model.addAttribute("pageNo", pageNo);
                break;
            case "Appetizers":
                model.addAttribute("category", "Appetizers");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccess("Appetizers", pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccess("Appetizers"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Salads":
                model.addAttribute("category", "Salads");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccess("Salads", pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccess("Salads"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Soups":
                model.addAttribute("category", "Soups");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccess("Soups", pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccess("Soups"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Main Courses":
                model.addAttribute("category", "Main Courses");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccess("Main Courses", pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccess("Main Courses"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Sides":
                model.addAttribute("category", "Sides");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccess("Sides", pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccess("Sides"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Sauces":
                model.addAttribute("category", "Sauces");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccess("Sauces", pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccess("Sauces"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Desserts":
                model.addAttribute("category", "Desserts");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccess("Desserts", pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccess("Desserts"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Drinks":
                model.addAttribute("category", "Drinks");
                model.addAttribute("recipes", recipeService.findByCategoryAndAccess("Drinks", pageRequest));
                model.addAttribute("allPage", getPageCountByCategoryWithAccess("Drinks"));
                model.addAttribute("pageNo", pageNo);

        }
        return "user/user_public_category";
    }

    @RequestMapping(value = "/recipePageUser")
    public String recipePageUser(Model model, @RequestParam String nameOfCourse) {

        recipePage(model, nameOfCourse, recipeService);

        return "user/user_recipe";
    }

    static void recipePage(Model model, @RequestParam String nameOfCourse, RecipeService recipeService) {
        Recipes recipes = recipeService.findByNameOfCourse(nameOfCourse);
        Set<IngredientAmount> ingredientAmounts = recipes.getIngredientAmounts();
        Map<Ingredient, Float> ingredientsOfRecipe = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("y.MM.dd");
        String login;

        for (IngredientAmount iA : ingredientAmounts)
            ingredientsOfRecipe.put(iA.getIngredient(), iA.getAmount());

        Set<Map.Entry<Ingredient, Float>> ingredientSet = ingredientsOfRecipe.entrySet();

        if (recipes.getCustUser() == null)
            login = "unowned";
        else login = recipes.getCustUser().getLogin();

        model.addAttribute("login", login);
        model.addAttribute("nameOfCourse", nameOfCourse);
        model.addAttribute("category", recipes.getCategory());
        model.addAttribute("dateOfCreate", dateFormat.format(recipes.getCreatedAt()));
        model.addAttribute("dateOfUpdate", dateFormat.format(recipes.getUpdatedAt()));
        model.addAttribute("ingredients", ingredientSet);
        model.addAttribute("ingredientAmounts", ingredientAmounts);
        model.addAttribute("servings", recipes.getNumberOfServings());
        model.addAttribute("output", recipes.getOutput());
        model.addAttribute("instruction", recipes.getInstruction());
    }

    @RequestMapping(value = "/addRecipe")
    public String addRecipe(Model model) {
        model.addAttribute("allIngredients", ingredientService.getAllNames());
        model.addAttribute("allRecipeNames", recipeService.getAllNames());
        return "user/user_add_recipe";
    }

    @RequestMapping(value = "/ingredientData")
    @ResponseBody
    public String ingredientData(Model model, @RequestParam String ingred) {
        Ingredient ingredient = ingredientService.getIngredient(ingred);
        StringBuilder builder = new StringBuilder();
        if (ingredient.getCup().equals(0f))
            builder.append('0');
        else
            builder.append('1');
        if (ingredient.getGlass().equals(0f))
            builder.append('0');
        else
            builder.append('1');
        if (ingredient.getTableSpoon().equals(0f))
            builder.append('0');
        else
            builder.append('1');
        if (ingredient.getTeaSpoon().equals(0f))
            builder.append('0');
        else
            builder.append('1');
        if (ingredient.getPiece().equals(0f))
            builder.append('0');
        else
            builder.append('1');
        String request = builder.toString();
        System.out.println("request: " + request);
        model.addAttribute("response", request);
        return request;
    }

    @RequestMapping(value = "/newRecipe", method = RequestMethod.POST)
    public String newRecipe(@RequestParam String nameOfCourse, @RequestParam String category, @RequestParam String instruction, @RequestParam String[] name, @RequestParam String[] gram, @RequestParam String[] cup, @RequestParam String[] glass, @RequestParam String[] tableSpoon, @RequestParam String[] teaSpoon, @RequestParam String[] piece, @RequestParam String output, @RequestParam String servings, @RequestParam String access) {

        Float out;

        if (!output.equals(""))
            out = Float.parseFloat(output);
        else out = null;
        if (instruction.equals(""))
            instruction = null;

        User user = getCurrentUser();
        String login = user.getUsername();
        CustUser cu = userService.findByLogin(login);

        boolean a = (access.equals("0,1"));

        Recipes res = new Recipes(nameOfCourse, category, instruction, Integer.parseInt(servings), a, out);
        res.setCustUser(cu);

        recipeService.addRecipe(res);

        for (int i = 0; i < name.length; i++) {
            float g = Float.parseFloat(gram[i]);
            if (g != 0) {
                setIngredient(g, name[i], res);
            } else {
                if (!cup[i].equals("0.00")) {
                    setIngredientAfterConverting(cup[i], "cup", name[i], res);
                }
                if (!glass[i].equals("0.00")) {
                    setIngredientAfterConverting(glass[i], "glass", name[i], res);
                }
                if (!tableSpoon[i].equals("0.00")) {
                    setIngredientAfterConverting(tableSpoon[i], "tableSpoon", name[i], res);
                }
                if (!teaSpoon[i].equals("0.00")) {
                    setIngredientAfterConverting(teaSpoon[i], "teaSpoon", name[i], res);
                }
                if (!piece[i].equals("0.00")) {
                    setIngredientAfterConverting(piece[i], "piece", name[i], res);
                }
            }
        }

        return "user/main-user";
    }

    @RequestMapping(value = "/addIngredient", method = RequestMethod.POST)
    @ResponseBody
    public String addIngredient(@RequestParam String msg) {
        Gson gson = new Gson();
        JsonIngredient jsonIngredient = gson.fromJson(msg, JsonIngredient.class);
        System.out.println("name = " + jsonIngredient.toString());

        ingredientService.addIngredient(jsonIngredient.jsonToIngredient());

        return jsonIngredient.jsonToIngredient().getName();
    }

    @RequestMapping(value = "/changeDelRecipe")
    public String changeDelRecipe(@RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        User user = getCurrentUser();
        String login = user.getUsername();
        CustUser cu = userService.findByLogin(login);

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        model.addAttribute("recipes", recipeService.findByUser(cu.getId(), pageRequest));
        model.addAttribute("allPage", getPageCountMyRecipes(cu.getId()));
        model.addAttribute("pageNo", pageNo);

        return "user/user_change_del_recipe";
    }

    @RequestMapping(value = "/deleteRecipes", method = RequestMethod.POST)
    public String delete(@RequestParam(name = "toDelete", required = false) List<String> recipes) {

        recipeService.deleteRecipes(recipes);

        return "redirect:/changeDelRecipe";
    }

    @RequestMapping(value = "/updateRecipePage", method = RequestMethod.GET)
    public String updatePage(Model model, @RequestParam String nameOfCourse) {

        model.addAttribute("allIngredients", ingredientService.getAllNames());
        model.addAttribute("allRecipeNames", recipeService.getAllNames());
        recipePage(model, nameOfCourse, recipeService);
        model.addAttribute("id", recipeService.findByNameOfCourse(nameOfCourse).getId());

        return "user/user_updateRecipe";
    }

    @RequestMapping(value = "/updateRecipe", method = RequestMethod.POST)
    public String updateRecipe(@RequestParam String id, @RequestParam String nameOfCourse, @RequestParam String category, @RequestParam String instruction, @RequestParam String[] name, @RequestParam String[] gram, @RequestParam String[] cup, @RequestParam String[] glass, @RequestParam String[] tableSpoon, @RequestParam String[] teaSpoon, @RequestParam String[] piece, @RequestParam String output, @RequestParam String servings, @RequestParam String access) {
        Long resId = Long.parseLong(id);

        ingredientAmountService.deleteIngredientAmount(resId);

        boolean a = (access.equals("0,1"));

        recipeService.updateRecipe(resId, nameOfCourse, category, instruction, Float.parseFloat(output), Integer.parseInt(servings), a);

        Recipes res = recipeService.findById(resId);

        for (int i = 0; i < name.length; i++) {
            float g = Float.parseFloat(gram[i]);
            if (g != 0) {
                setIngredient(g, name[i], res);
            } else {
                if (!cup[i].equals("0.00")) {
                    setIngredientAfterConverting(cup[i], "cup", name[i], res);
                }
                if (!glass[i].equals("0.00")) {
                    setIngredientAfterConverting(glass[i], "glass", name[i], res);
                }
                if (!tableSpoon[i].equals("0.00")) {
                    setIngredientAfterConverting(tableSpoon[i], "tableSpoon", name[i], res);
                }
                if (!teaSpoon[i].equals("0.00")) {
                    setIngredientAfterConverting(teaSpoon[i], "teaSpoon", name[i], res);
                }
                if (!piece[i].equals("0.00")) {
                    setIngredientAfterConverting(piece[i], "piece", name[i], res);
                }
            }
        }

        return "redirect:/changeDelRecipe";
    }

    @RequestMapping(value = "/accountPage", method = RequestMethod.GET)
    public String accountPage(Model model) {

        User user = getCurrentUser();
        String login = user.getUsername();
        CustUser cu = userService.findByLogin(login);

        model.addAttribute("id", cu.getId());
        model.addAttribute("login", login);
        model.addAttribute("email", cu.getEmail());
        model.addAttribute("phone", cu.getPhone());

        return "user/user_account";
    }

    @RequestMapping(value = "/accountUpdate", method = RequestMethod.GET)
    public String accountUpdate(@RequestParam String id, @RequestParam String login, @RequestParam String email, @RequestParam String phone, @RequestParam String newPassword2) {

        userService.updateUser(Long.parseLong(id), UserRole.USER, login, email, phone);
        if (!newPassword2.isEmpty())
            userService.changePassword(Long.parseLong(id), passwordEncoder.encode(newPassword2));
        return "redirect:/user/main-user";
    }

    @RequestMapping(value = "/newPassword", method = RequestMethod.POST)
    public String newPassword(@RequestParam(name = "email", required = false) String email, Model model) throws MessagingException {

        CustUser user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("mistake", "0");
            return "access_recovery";
        }

        String password = new Random().ints(6, 33, 122).mapToObj(i -> String.valueOf((char) i)).collect(Collectors.joining());

        String passHash = passwordEncoder.encode(password);

        userService.changePassword(user.getId(), passHash);

        /*SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("New Password from Recipes");
        message.setText("Hello, Your login is \"" + user.getLogin() + "\" and password is \"" + password + "\". You can change your password inside your account.");

        this.emailSender.send(message);*/

//            Mailgun заблокировал аккаунт из-за открытого gitHub. Код выше работает в локале, но не работает в Heroku, код ниже работает везде.

//        Mail.sendMail(email, user.getLogin(), password);

        model.addAttribute("success", "1");

        return "access_recovery";
    }


    @RequestMapping(value = "/searchByNameByUser")
    public String searchByRecipe(Model model, @RequestParam(required = false) String word, @RequestParam(required = false, defaultValue = "0") Integer pageNo) {

        User user = getCurrentUser();
        String login = user.getUsername();
        CustUser cu = userService.findByLogin(login);

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE - 1, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        model.addAttribute("category", "Found Recipes");
        model.addAttribute("recipes", recipeService.findRecipeWithAccessAndByUser(word, cu.getId(), pageRequest));
        model.addAttribute("word", word);
        model.addAttribute("allPage", getPageRecipesFoundByNameOfCourseAndAccessByUser(word, cu.getId()));
        model.addAttribute("pageNo", pageNo);

        return "user/user_searchByName";
    }

    @RequestMapping(value = "/searchByIngredientByUser")
    public String searchByIngredient(Model model, @RequestParam(required = false) String word, @RequestParam(required = false, defaultValue = "0") Integer pageNo) {

        User user = getCurrentUser();
        String login = user.getUsername();
        CustUser cu = userService.findByLogin(login);

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE - 1, Sort.DEFAULT_DIRECTION, "name_of_course");

        model.addAttribute("category", "Found Recipes");
        model.addAttribute("recipes", recipeService.findRecipeByIngredientWithAccessAndByUser(word, cu.getId(), pageRequest));
        model.addAttribute("word", word);
        model.addAttribute("allPage", getPageRecipesFoundByIngredientAndAccessAndByUser(word, cu.getId()));
        model.addAttribute("pageNo", pageNo);

        return "user/user_searchByIngredient";
    }

    @GetMapping("/logOut")
    public String logoutSite(HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        model.addAttribute("logout", "1");

        return "index";
    }

    private void setIngredientAfterConverting(String volume, String volumeName, String name, Recipes res) {
        Ingredient i = ingredientService.getIngredient(name);
        Float volumeAmount = Float.parseFloat(volume);

        switch (volumeName) {
            case "cup":
                setIngredient(volumeAmount * i.getCup(), name, res);
                break;
            case "glass":
                setIngredient(volumeAmount * i.getGlass(), name, res);
                break;
            case "tableSpoon":
                setIngredient(volumeAmount * i.getTableSpoon(), name, res);
                break;
            case "teaSpoon":
                setIngredient(volumeAmount * i.getTeaSpoon(), name, res);
                break;
            case "piece":
                setIngredient(volumeAmount * i.getPiece(), name, res);
        }
    }

    private void setIngredient(Float g, String name, Recipes res) {
        IngredientAmount ingredientAmount = new IngredientAmount(g);
        ingredientAmount.setIngredient(ingredientService.getIngredient(name));
        ingredientAmount.setRecipe(res);
        ingredientAmountService.addIngredientAndAmount(ingredientAmount);
    }

    private long getPageCountWithUserAndAccess(Long id) {
        long totalCount = recipeService.countByUserAndAccess(id);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCountMyRecipes(Long id) {
        long totalCount = recipeService.countByUser(id);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCountWithAccess() {
        long totalCount = recipeService.countByAccess();
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCountByCategoryWithAccess(String category) {
        long totalCount = recipeService.countByCategoryWithAccess(category);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCountByCategoryWithAccessAndUser(String category, Long id) {
        long totalCount = recipeService.countByCategoryWithAccessAndUser(category, id);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCountByCategoryAndUser(String category, Long id) {
        long totalCount = recipeService.countByCategoryAndUser(category, id);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageRecipesFoundByNameOfCourseAndAccessByUser(String word, Long id) {
        long totalCount = recipeService.countRecipesFoundByNameOfCourseAndAccessAndByUser(word, id);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageRecipesFoundByIngredientAndAccessAndByUser(String word, Long id) {
        long totalCount = recipeService.countRecipesFoundByIngredientAndAccessAndByUser(word, id);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
