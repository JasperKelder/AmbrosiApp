package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cookbook;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CookbookRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.RecipeRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Controller
public class CookbookController {

    @Autowired
    CookbookRepository cookbookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipeRepository recipeRepository;

    FileUploadService fileUploadService = new FileUploadService();

    @PostMapping("/newcookbook")
    protected String saveCookbook(@ModelAttribute("cookbook") Cookbook cookbook, Principal principal) {
        cookbook.setUser(userRepository.findByEmailAddress(principal.getName()));
        cookbookRepository.save(cookbook);

        return "redirect:/mykitchen";
    }

    // Method to get the recipes from the cookbookId by user
    @GetMapping("/viewcookbook/{id}")
    protected String showRecipesForMyFirstCookbook(@PathVariable("id") final Integer cookbookId, Model model) {
        Cookbook currentCookbook = cookbookRepository.getOne(cookbookId);
        model.addAttribute("myRecipes", currentCookbook.getRecipes());
        model.addAttribute("cookbookName", currentCookbook.getCookbookName());

        List<String> imagesList = new ArrayList<>();
        List<Recipe> recipes = currentCookbook.getRecipes();
        for (Recipe recipe : recipes) {
            imagesList.add(fileUploadService.convertToBase64(recipe));
        }
        model.addAttribute("allImages", imagesList);

        return "viewcookbook";
    }

    @RequestMapping(value = "/addtocookbook", method = RequestMethod.GET)
    protected String showCookBooks(@RequestParam("recipeid") String recipeId,
                                   Model model,
                                   Principal principal) {
        //method to get all cookbooks linked to current user
        User currentUser = userRepository.findByEmailAddress(principal.getName());
        List<Cookbook> cookbooks = cookbookRepository.findAll();
        List<Cookbook> myCookbooks = new ArrayList<>();
        for (Cookbook cookbook : cookbooks) {
            if (currentUser.getUserId() == cookbook.getUser().getUserId()) {
                myCookbooks.add(cookbook);
            }
        }
        model.addAttribute("cookbook", new Cookbook());
        model.addAttribute("usersCookbooks", myCookbooks);
        model.addAttribute("recipeId", recipeId);
        return "addtocookbook";
    }

    @RequestMapping(value = "/addtocookbook", method = RequestMethod.POST)
    protected String addToCookbook(@RequestParam("recipeId") String recipeId,
                                   @ModelAttribute("cookbook") Cookbook cookbook,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "addtocookbook";
        }
        Optional<Recipe> recipe = recipeRepository.findById(Integer.valueOf(recipeId));
        if (recipe.isPresent()) {
            // Create a list of recipes
            List<Recipe> recipeToCookbook = cookbook.getRecipes();
            if (!recipeToCookbook.contains(recipe.get())) {
                recipeToCookbook.add(recipe.get());
                cookbook.setRecipes(recipeToCookbook);
                cookbookRepository.save(cookbook);
            }
        }
        return "redirect:/viewcookbook/" + cookbook.getCookbookId();
    }
}

