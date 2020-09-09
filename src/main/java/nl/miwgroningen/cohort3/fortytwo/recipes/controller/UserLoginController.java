package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Controller
public class UserLoginController {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    protected String showRecipesIndex(Model model) {
        model.addAttribute("allRecipes", recipeRepository.findAll());
        return "index";
    }

}

