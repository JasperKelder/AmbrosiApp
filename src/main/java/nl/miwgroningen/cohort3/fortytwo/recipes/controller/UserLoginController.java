package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Controller
public class UserLoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

