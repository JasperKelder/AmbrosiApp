package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.dto.EmailChangeDto;
import nl.miwgroningen.cohort3.fortytwo.recipes.dto.PasswordChangeDto;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */
@Controller
public class UserInfoController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

//    //method for showing userinfo. Principal is used to get the current user info.
//    //principal.getname() gets email from db
//    @GetMapping("/userinfo")
//    protected String showUser(Model model, Principal principal) {
//        model.addAttribute("user", userRepository.findByEmailAddress(principal.getName()));
//        return "userinfo";
//    }

    // method for updating the first and last name of a user
    @RequestMapping(path = "/update", produces = MediaType.TEXT_PLAIN_VALUE)
    protected String updateName(@RequestParam String firstName,
                                @RequestParam String lastName,
                                Principal principal) {
        User user = userRepository.findByEmailAddress(principal.getName());
        userRepository.updateName(firstName, lastName, user.getUserId());
        return "redirect:/mykitchen";
    }

}