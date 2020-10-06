package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cookbook;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CookbookRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.RecipeRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;

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

    @PostMapping("/newcookbook")
    protected String saveCookbook(@ModelAttribute("cookbook") Cookbook cookbook, Principal principal) {
        cookbook.setUser(userRepository.findByEmailAddress(principal.getName()));
        cookbookRepository.save(cookbook);

        return "redirect:/mykitchen";
    }

    // Method to get the recipes from the cookbookId by user
    @GetMapping("/myrecipes/{id}")
    protected String showRecipesForMyFirstCookbook(@PathVariable("id") final Integer cookbookId, Model model) {
        Cookbook currentCookbook = cookbookRepository.getOne(cookbookId);
        model.addAttribute("myRecipes", currentCookbook.getRecipes());

        return "myrecipes";
    }

}

