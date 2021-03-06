package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.dto.PasswordChangeDto;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Controller
@RequestMapping("/changepassword")
public class ChangePasswordController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    public ChangePasswordController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public PasswordChangeDto passwordChangeDto() {
        return new PasswordChangeDto();
    }

    //method to save new password with current user
    @PostMapping
    public String saveNewPassword(@ModelAttribute("user") PasswordChangeDto passwordChangeDto, Principal principal) {
        userService.save(passwordChangeDto, principal);
        return "redirect:/mykitchen?success";
    }

}
