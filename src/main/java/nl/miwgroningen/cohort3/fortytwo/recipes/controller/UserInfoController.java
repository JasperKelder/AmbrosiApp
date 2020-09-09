package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

public class UserInfoController {

    private UserRepository userRepository;

    //TODO make my kitchen page and create userInfo html based on registration html (name new html userinfo)

    @GetMapping("/userinfo/{userId}")
    protected String showUserInfo(@PathVariable("userId") final Integer userId, Model model){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user);
            return "userinfo";
        }
        return "redirect:/index";
    }

    // method to update userinfo using the userId
    @GetMapping("/userinfo/update/{userId}")
    protected String updateUserInfo(@PathVariable("userId") final Integer userId, Model model) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user);
            return "mykitchen";
        }
        return "index";
    }

}