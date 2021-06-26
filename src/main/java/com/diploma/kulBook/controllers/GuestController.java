package com.diploma.kulBook.controllers;

import com.diploma.kulBook.config.AppConfig;
import com.diploma.kulBook.entities.UserRole;
import com.diploma.kulBook.services.CustUserService;
import com.diploma.kulBook.services.IngredientAmountService;
import com.diploma.kulBook.services.IngredientService;
import com.diploma.kulBook.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuestController {

    @Autowired
    private CustUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*@Autowired
    public JavaMailSender emailSender;*/

    @Autowired
    RecipeService recipeService;

    @Autowired
    IngredientAmountService ingredientAmountService;

    @Autowired
    IngredientService ingredientService;

    @RequestMapping(value = "/guest/main-guest", method = RequestMethod.GET)
    public String GuestPage(@RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        model.addAttribute("category", "All Recipes");
        model.addAttribute("recipes", recipeService.findByAccess(pageRequest));
        model.addAttribute("allPage", getPageCountWithAccess());
        model.addAttribute("pageNo", pageNo);

        return "guest/main-guest";
    }

    @RequestMapping(value = "/guest/category")
    public String categorySelection(@RequestParam String category, @RequestParam(required = false, defaultValue = "0") Integer pageNo, Model model) {

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        switch (category) {
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
        return "guest/category";
    }

    @RequestMapping(value = "/guest/searchByName")
    public String searchByRecipe(Model model, @RequestParam(required = false) String word, @RequestParam(required = false, defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE - 1, Sort.DEFAULT_DIRECTION, "nameOfCourse");

        model.addAttribute("category", "Found Recipes");
        model.addAttribute("recipes", recipeService.findRecipeWithAccess(word, pageRequest));
        model.addAttribute("word", word);
        model.addAttribute("allPage", getPageRecipesFoundByNameOfCourseAndAccess(word));
        model.addAttribute("pageNo", pageNo);

        return "guest/searchByName";
    }

    @RequestMapping(value = "/guest/searchByIngredient")
    public String searchByIngredient(Model model, @RequestParam(required = false) String word, @RequestParam(required = false, defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) pageNo = 0;

        Pageable pageRequest = PageRequest.of(pageNo, AppConfig.ITEMS_PER_PAGE - 1, Sort.DEFAULT_DIRECTION, "name_of_course");

        model.addAttribute("category", "Found Recipes");
        model.addAttribute("recipes", recipeService.findRecipeByIngredientWithAccess(word, pageRequest));
        model.addAttribute("word", word);
        model.addAttribute("allPage", getPageRecipesFoundByIngredientAndAccess(word));
        model.addAttribute("pageNo", pageNo);

        return "guest/searchByIngredient";
    }

    @RequestMapping(value = "/recipePageGuest")
    public String recipePageGuest(Model model, @RequestParam String nameOfCourse) {

        UserController.recipePage(model, nameOfCourse, recipeService);

        return "guest/recipe";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String update(@RequestParam String login,
                         @RequestParam String password,
                         @RequestParam(required = false) String email,
                         @RequestParam(required = false) String phone,
                         Model model) {
        String passHash = passwordEncoder.encode(password);

        if (!userService.addUser(login, passHash, UserRole.USER, email, phone)) {
            model.addAttribute("exists", true);
            model.addAttribute("login", login);
            return "register";
        }

        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/access_recovery")
    public String access_recovery() {
        return "access_recovery";
    }

    private long getPageCountWithAccess() {
        long totalCount = recipeService.countByAccess();
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCountByCategoryWithAccess(String category) {
        long totalCount = recipeService.countByCategoryWithAccess(category);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageRecipesFoundByNameOfCourseAndAccess(String word) {
        long totalCount = recipeService.countRecipesFoundByNameOfCourseAndAccess(word);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageRecipesFoundByIngredientAndAccess(String word) {
        long totalCount = recipeService.countRecipesFoundByIngredientAndAccess(word);
        return (totalCount / AppConfig.ITEMS_PER_PAGE) + ((totalCount % AppConfig.ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
}
