package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.repository.RecipeRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Controller
public class UserLoginController {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}

