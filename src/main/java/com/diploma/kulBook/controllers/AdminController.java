package com.diploma.kulBook.controllers;

import com.diploma.kulBook.JsonIngredient;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

import static com.diploma.kulBook.controllers.UserController.recipePage;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    @Autowired
    private CustUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

   /* @Autowired
    private JavaMailSender emailSender;*/

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private IngredientAmountService ingredientAmountService;

    String isExistIngredient = "1";

    @RequestMapping(value = "admin/main-admin", method = RequestMethod.GET)
    public String AdminPage(@RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        model.addAttribute("category", "All Recipes");
        model.addAttribute("recipes", recipeService.getAllNames(pageRequest));
        model.addAttribute("allPage", getPageCount());
        model.addAttribute("pageNo", pageNo);

        return "admin/main-admin";
    }

    @RequestMapping(value = "/admin/category_admin")
    public String userCategorySelection(@RequestParam String category, @RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        switch (category) {
            case "all":
                model.addAttribute("category", "All Recipes");
                model.addAttribute("recipes", recipeService.getAllNames(pageRequest));
                model.addAttribute("allPage", getPageCount());
                model.addAttribute("pageNo", pageNo);
                break;
            case "Appetizers":
                model.addAttribute("category", "Appetizers");
                model.addAttribute("recipes", recipeService.findByCategory("Appetizers", pageRequest));
                model.addAttribute("allPage", getPageCountByCategory("Appetizers"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Salads":
                model.addAttribute("category", "Salads");
                model.addAttribute("recipes", recipeService.findByCategory("Salads", pageRequest));
                model.addAttribute("allPage", getPageCountByCategory("Salads"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Soups":
                model.addAttribute("category", "Soups");
                model.addAttribute("recipes", recipeService.findByCategory("Soups", pageRequest));
                model.addAttribute("allPage", getPageCountByCategory("Soups"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Main Courses":
                model.addAttribute("category", "Main Courses");
                model.addAttribute("recipes", recipeService.findByCategory("Main Courses", pageRequest));
                model.addAttribute("allPage", getPageCountByCategory("Main Courses"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Sides":
                model.addAttribute("category", "Sides");
                model.addAttribute("recipes", recipeService.findByCategory("Sides", pageRequest));
                model.addAttribute("allPage", getPageCountByCategory("Sides"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Sauces":
                model.addAttribute("category", "Sauces");
                model.addAttribute("recipes", recipeService.findByCategory("Sauces", pageRequest));
                model.addAttribute("allPage", getPageCountByCategory("Sauces"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Desserts":
                model.addAttribute("category", "Desserts");
                model.addAttribute("recipes", recipeService.findByCategory("Desserts", pageRequest));
                model.addAttribute("allPage", getPageCountByCategory("Desserts"));
                model.addAttribute("pageNo", pageNo);
                break;
            case "Drinks":
                model.addAttribute("category", "Drinks");
                model.addAttribute("recipes", recipeService.findByCategory("Drinks", pageRequest));
                model.addAttribute("allPage", getPageCountByCategory("Drinks"));
                model.addAttribute("pageNo", pageNo);

        }
        return "admin/category_admin";
    }

    @RequestMapping(value = "/recipePageAdmin")
    public String recipePageAdmin(Model model, @RequestParam String nameOfCourse) {

        recipePage(model, nameOfCourse, recipeService);

        return "admin/admin_recipe";
    }

    @RequestMapping(value = "/admin/addRecipe")
    public String addRecipe(Model model) {
        model.addAttribute("allIngredients", ingredientService.getAllNames());
        model.addAttribute("allRecipeNames", recipeService.getAllNames());
        return "admin/admin_add_recipe";
    }

    @RequestMapping(value = "/admin/newRecipe", method = RequestMethod.POST)
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

        boolean a = access.equals("0,1");

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

        return "admin/main-admin";
    }

    @RequestMapping(value = "/admin/addIngredient", method = RequestMethod.POST)
    @ResponseBody
    public String addIngredient(@RequestParam String msg) {
        Gson gson = new Gson();
        JsonIngredient jsonIngredient = gson.fromJson(msg, JsonIngredient.class);
        System.out.println("name = " + jsonIngredient.toString());

        ingredientService.addIngredient(jsonIngredient.jsonToIngredient());

        return jsonIngredient.jsonToIngredient().getName();
    }

    @RequestMapping(value = "/admin/changeDelRecipe")
    public String changeDelRecipe(@RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        model.addAttribute("recipes", recipeService.getAllNames(pageRequest));
        model.addAttribute("allPage", getPageCount());
        model.addAttribute("pageNo", pageNo);

        return "admin/admin_change_del_recipe";
    }

    @RequestMapping(value = "/admin/deleteRecipes", method = RequestMethod.POST)
    public String delete(@RequestParam(name = "toDelete", required = false) List<String> recipes) {

        recipeService.deleteRecipes(recipes);

        return "redirect:/admin/changeDelRecipe";
    }

    @RequestMapping(value = "/admin/updateRecipePage", method = RequestMethod.GET)
    public String updatePage(Model model, @RequestParam String nameOfCourse) {

        model.addAttribute("allIngredients", ingredientService.getAllNames());
        recipePage(model, nameOfCourse, recipeService);
        model.addAttribute("id", recipeService.findByNameOfCourse(nameOfCourse).getId());

        return "admin/admin_updateRecipe";
    }

    @RequestMapping(value = "/admin/updateRecipe", method = RequestMethod.POST)
    public String updateRecipe(@RequestParam String id, @RequestParam String nameOfCourse, @RequestParam String category, @RequestParam String instruction, @RequestParam String[] name, @RequestParam String[] gram, @RequestParam String[] cup, @RequestParam String[] glass, @RequestParam String[] tableSpoon, @RequestParam String[] teaSpoon, @RequestParam String[] piece, @RequestParam String output, @RequestParam String servings, @RequestParam String access) {
        Long resId = Long.parseLong(id);

        ingredientAmountService.deleteIngredientAmount(resId);

        boolean a = access.equals("0,1");

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

        return "redirect:/admin/changeDelRecipe";
    }

    @RequestMapping(value = "/users")
    public String allUsers(@RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "login");

        model.addAttribute("users", userService.getAllUsers(pageRequest));
        model.addAttribute("allPage", getPageUserCount());
        model.addAttribute("pageNo", pageNo);


        return "admin/admin_users";
    }

    @RequestMapping(value = "/delUsers", method = RequestMethod.POST)
    public String delUser(@RequestParam(name = "toDelete", required = false) List<Long> ids) {

        for (Long id : ids) {
            recipeService.changeAccessByUserId(id);
        }

        userService.deleteUsers(ids);

        return "redirect:/users";
    }

    @RequestMapping(value = "/admin/updateUserPage", method = RequestMethod.GET)
    public String delUpdateUsers(@RequestParam(name = "id", required = false) Long id, Model model) {

        CustUser user = userService.getById(id);

        model.addAttribute("id", id);
        model.addAttribute("role", user.getRole());
        model.addAttribute("login", user.getLogin());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("role", user.getRole());

        return "admin/admin_update_user";
    }

    @RequestMapping(value = "/admin/userUpdate", method = RequestMethod.POST)
    public String userUpdate(@RequestParam String id,
                             @RequestParam String role,
                             @RequestParam String login,
                             @RequestParam String newPassword,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String phone) throws MessagingException {

        if (id.equals("1")) {
            return "redirect:/users";
        }

        role = "ROLE_" + role;

        CustUser user = userService.getById(Long.parseLong(id));

        if (role.equals(user.getRole().toString()) & login.equals(user.getLogin()) & email.equals(user.getEmail()) & phone.equals(user.getPhone()) & newPassword.isEmpty()) {
            return "redirect:/users";
        }

        userService.updateUser(Long.parseLong(id), role.equals("ROLE_USER") ? UserRole.USER : UserRole.ADMIN, login, email, phone);

        if (!newPassword.isEmpty()) {

            /*SimpleMailMessage message = new SimpleMailMessage();

            message.setTo("mvkamkin@gmail.com");
            message.setSubject("New Password for " + login);
            message.setText("Hello, you change password for login " + login + ". New password: " + newPassword + ".");

            this.emailSender.send(message);

            userService.changePassword(Long.parseLong(id), passwordEncoder.encode(newPassword));*/

//            Mailgun заблокировал аккаунт из-за открытого gitHub. Код выше работает в локале, но не работает в Heroku, код ниже работает везде.

            return "mail";

//            Mail.sendMail("mvkamkin@gmail.com", login, newPassword);

        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/ingredients")
    public String allIngredients(@RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        if (isExistIngredient.equals("0")) {
            model.addAttribute("isExist", "0");
        }

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "name");

        List<Ingredient> ingredients = ingredientService.getAllIngredients(pageRequest);
        ArrayList<Boolean> isUsed = new ArrayList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            isUsed.add(i, isUsed(ingredients.get(i).getId()));
        }

        model.addAttribute("ingredients", ingredients);
        model.addAttribute("isUsed", isUsed);
        model.addAttribute("allPage", getPageIngredientsCount());
        model.addAttribute("pageNo", pageNo);


        return "admin/admin_ingredients";
    }

    @RequestMapping(value = "/delIngredients", method = RequestMethod.POST)
    public String delIngredients(@RequestParam(name = "toDelete", required = false) List<Long> ids) {

        ingredientService.deleteIngredients(ids);

        return "redirect:/ingredients";
    }

    @RequestMapping(value = "/admin/updateIngredientPage", method = RequestMethod.GET)
    public String UpdateIngredientPage(@RequestParam(name = "id") Long id, @RequestParam(name = "alert") String alert, Model model) {

        Ingredient ingredient = ingredientService.getIngredientById(id);

        if (ingredient == null) {
            isExistIngredient = "0";
            return "redirect:/ingredients";
        }

        model.addAttribute("id", id);
        model.addAttribute("name", ingredient.getName());
        model.addAttribute("cup", Math.round(ingredient.getCup()));
        model.addAttribute("glass", Math.round(ingredient.getGlass()));
        model.addAttribute("tableSpoon", Math.round(ingredient.getTableSpoon()));
        model.addAttribute("teaSpoon", Math.round(ingredient.getTeaSpoon()));
        model.addAttribute("piece", Math.round(ingredient.getPiece()));
        model.addAttribute("alert", alert);

        return "admin/admin_update_ingredient";
    }

    @RequestMapping(value = "/updateIngredient", method = RequestMethod.POST)
    public String updateIngredient(@RequestParam(name = "id") String id, @RequestParam(name = "name") String name, @RequestParam(name = "cup") String cup, @RequestParam(name = "glass") String glass, @RequestParam(name = "tableSpoon") String tableSpoon, @RequestParam(name = "teaSpoon") String teaSpoon, @RequestParam(name = "piece") String piece) {

        boolean status = ingredientService.updateIngredient(Long.parseLong(id), name, Float.parseFloat(cup), Float.parseFloat(glass), Float.parseFloat(tableSpoon), Float.parseFloat(teaSpoon), Float.parseFloat(piece));
        System.out.println("status:     - " + status);


        if (status)
            return "redirect:/admin/updateIngredientPage?id=" + id + "&alert=alert";

        return "redirect:/ingredients";
    }

    @RequestMapping(value = "/searchByNameAdmin")
    public String searchByRecipe(Model model, @RequestParam(required = false) String word, @RequestParam(required = false, defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE - 1, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        model.addAttribute("category", "Found Recipes");
        model.addAttribute("recipes", recipeService.findRecipeByName(word, pageRequest));
        model.addAttribute("word", word);
        model.addAttribute("allPage", getPageRecipesFoundByName(word));
        model.addAttribute("pageNo", pageNo);

        return "admin/admin_searchByName";
    }

    @RequestMapping(value = "/searchByIngredientAdmin")
    public String searchByIngredient(Model model, @RequestParam(required = false) String word, @RequestParam(required = false, defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE - 1, Sort.DEFAULT_DIRECTION, "name_of_course");

        model.addAttribute("category", "Found Recipes");
        model.addAttribute("recipes", recipeService.findRecipeByIngredient(word, pageRequest));
        model.addAttribute("word", word);
        model.addAttribute("allPage", getPageRecipesFoundByIngredient(word));
        model.addAttribute("pageNo", pageNo);

        return "admin/admin_searchByIngredient";
    }

    private long getPageUserCount() {
        long totalCount = userService.countAll();
        return (totalCount / (AppConfig.ITEMS_PER_PAGE)) + ((totalCount % (AppConfig.ITEMS_PER_PAGE) > 0) ? 1 : 0);
    }

    private long getPageCount() {
        long totalCount = recipeService.countAll();
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageIngredientsCount() {
        long totalCount = ingredientService.countAll();
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCountByCategory(String category) {
        long totalCount = recipeService.countByCategory(category);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
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


    private User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public boolean isUsed(Long id) {
        System.out.println("id in method isused:   " + id);
        return ingredientAmountService.isUsedIngredient(id);
    }

    private long getPageRecipesFoundByIngredient(String word) {
        long totalCount = recipeService.countRecipesFoundByIngredient(word);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageRecipesFoundByName(String word) {
        long totalCount = recipeService.countRecipesFoundByName(word);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
}
