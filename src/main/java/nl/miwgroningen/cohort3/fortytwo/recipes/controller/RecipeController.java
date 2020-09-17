package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cuisine;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CategoryRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CuisineRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.RecipeRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */
@Controller
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CuisineRepository cuisineRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/add")
    protected String createRecipe(Model model, Principal principal) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allCuisines", cuisineRepository.findAll());
        model.addAttribute("user", userRepository.findByEmailAddress(principal.getName()));
//        Optional<User> user = userRepository.findById(userRepository.findByEmailAddress(principal.getName()).getUserId());
        return "add";
    }

    @PostMapping({"/add"})
    protected String saveRecipe(@ModelAttribute("recipe") Recipe recipe, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        } else {
            recipeRepository.save(recipe);
            return "redirect:/indexloggedin";
        }
    }

    @GetMapping("/index")
    protected String showRecipes(Model model) {
        model.addAttribute("allRecipes", recipeRepository.findAll());
        return "indexloaded";
    }

    @GetMapping("/recipes")
    protected String showRecipesAdmin(Model model) {
        model.addAttribute("allRecipes", recipeRepository.findAll());
        return "recipe";
    }

    @GetMapping({"/index/delete/{recipeId}", "/recipes/delete/{recipeId}"})
    protected String deleteRecipe(@PathVariable("recipeId") final Integer recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            recipeRepository.delete(recipe.get());
            return "forward:/recipes/";
        }
        return "forward:/recipes";
    }

    @GetMapping("/add/update/{recipeId}")
    protected String updateRecipe(@PathVariable("recipeId") final Integer recipeId, Model model) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allCuisines", cuisineRepository.findAll());
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe);
            return "add";
        }
        return "indexloaded";
    }

    @GetMapping("/view/{id}")
    protected String showRecipe(@PathVariable("id") final Integer recipeId, Model model) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
            return "view";
        }
        return "redirect:/index";
    }
}