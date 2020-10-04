package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.dto.EmailChangeDto;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Controller
@RequestMapping("/changeemailaddress")
public class ChangeEmailAddressController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    public ChangeEmailAddressController(UserService userService) {
        this.userService = userService;
    }

//    //method to get current user
//    @GetMapping("/changeemailaddress")
//    protected String showUserEmail(Model model, Principal principal) {
//        model.addAttribute("user", userRepository.findByEmailAddress(principal.getName()));
//        return "userinfo";
//    }

    @ModelAttribute("user")
    public EmailChangeDto emailChangeDto() { return new EmailChangeDto(); }

    //method to save new email address with current user
    @PostMapping
    public String saveNewEmailaddress(@ModelAttribute("user") EmailChangeDto emailChangeDto, Principal principal) {
        userService.save(emailChangeDto, principal);
        return "redirect:/logout";
    }

}
