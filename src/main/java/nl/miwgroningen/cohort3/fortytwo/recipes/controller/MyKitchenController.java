package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.dto.PasswordChangeDto;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cookbook;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CookbookRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.RecipeRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Controller
public class MyKitchenController {

    @Autowired
    CookbookRepository cookbookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserService userService;

    public MyKitchenController(UserService userService) {
        this.userService = userService;
    }

    //method to get all cookbooks linked to current user
    @GetMapping("/mykitchen")
    protected String getMyKitchenInfo(Model model, Principal principal) {
        //Method for showing user name in mykitchen.html
        model.addAttribute("user", userRepository.findByEmailAddress(principal.getName()));

        //method for creating new cookbook
        model.addAttribute("cookbook", new Cookbook());

        //method to get all cookbooks linked to current user
        User currentUser = userRepository.findByEmailAddress(principal.getName());

        List<Cookbook> cookbooks = cookbookRepository.findAll();
        List<Cookbook> myCookbooks = new ArrayList<>();

        for (Cookbook cookbook : cookbooks) {
            if (currentUser.getUserId() == cookbook.getUser().getUserId()) {
                myCookbooks.add(cookbook);
            }
        }
        model.addAttribute("allMyCookbooks", myCookbooks);
        return "mykitchen";
    }

    @ModelAttribute("user")
    public PasswordChangeDto passwordChangeDto() {
        return new PasswordChangeDto();
    }

    //method to save new password with current user
    @PostMapping
    public String saveNewPassword(@ModelAttribute("user") PasswordChangeDto passwordChangeDto, Principal principal) {
        userService.save(passwordChangeDto, principal);
        return "redirect:/changepassword?success";
    }

    @GetMapping("/draw")
    public String draw() {
        return "draw";
    }
}