package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cookbook;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.*;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.List;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */
@Controller
public class RecipeController {

    FileUploadService fileUploadService = new FileUploadService();

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CuisineRepository cuisineRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CookbookRepository cookbookRepository;

    @GetMapping("/add")
    protected String createRecipe(Model model, Principal principal) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("cookbook", new Cookbook());
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allCuisines", cuisineRepository.findAll());

        User currentUser = userRepository.findByEmailAddress(principal.getName());
        List<Cookbook> cookbooks = new ArrayList<>(cookbookRepository.findAll());
        List<Cookbook> userCookbooks = new ArrayList<>();

        for (Cookbook cookbook : cookbooks) {
            if (currentUser.getUserId() == cookbook.getUser().getUserId()){
                userCookbooks.add(cookbook);
            }
        }
        model.addAttribute("allUserCookbooks", userCookbooks);
        return "add";
    }

    @PostMapping({"/add"})
    protected String saveRecipe(@ModelAttribute("recipe") Recipe recipe,@ModelAttribute("cookbook") Cookbook cookbook,
    @RequestParam("file") MultipartFile image, Principal principal, BindingResult result) throws IOException {
        // Create a list of recipes
        List<Recipe> recipeToCookbook = new ArrayList<>();

        if (result.hasErrors()) {
            return "add";
        }
        else{
            recipe.setUser(userRepository.findByEmailAddress(principal.getName()));
            // If there is no image uploaded, save default image.
            if (image.isEmpty()){
                recipe.setImage(null);
            }
            else {
                recipe.setImage(image.getBytes());
            }

            // This will add the recipe to the cookbook
            recipeToCookbook.add(recipe);
            cookbook.setRecipes(recipeToCookbook);

            cookbookRepository.save(cookbook);
            recipeRepository.save(recipe);
        }
            return "redirect:/index";
    }


    @GetMapping({"/index", "/"})
    protected String showRecipes(Model model) {
        List<Recipe> recipes = recipeRepository.findAll();
        List<String> imagesList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            imagesList.add(fileUploadService.convertToBase64(recipe));
        }
        model.addAttribute("allRecipes", recipeRepository.findAll());
        model.addAttribute("allImages", imagesList);

        return "index";
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
            // If current image is present then convert it to base64 string so it can be displayed as a place holder
            String currentImage = fileUploadService.convertToBase64(recipe.get());
            model.addAttribute("currentImage", currentImage);
            model.addAttribute("recipe", recipe);
            return "add";
        }
        return "index";
    }

    @GetMapping("/viewrecipe/{id}")
    protected String showRecipe(@PathVariable("id") final Integer recipeId, Model model) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
            model.addAttribute("image", fileUploadService.convertToBase64(recipe.get()));
            return "viewrecipe";
        }
        return "redirect:/index";
    }
}