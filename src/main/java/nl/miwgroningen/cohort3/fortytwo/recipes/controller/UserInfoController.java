package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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