package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */
@Controller
public class UserUpdateController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/userupdate")
    protected String getInfo(Model model) {
        model.addAttribute("allUsers", userRepository.findAll());
        return "userupdate";
    }

    @PostMapping({"/userupdate"})
    protected String saveUser(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        } else {
            userRepository.save(user);
            return "redirect:/userupdate";
        }
    }

    @GetMapping("/userupdate/delete/{userId}")
    protected String deleteUser(@PathVariable("userId") final Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "forward:/userupdate/";
        }
        return "forward:/userupdate";
    }

    @GetMapping("/userupdate/update/{userId}")
    protected String updateUser(@PathVariable("userId") final Integer userId, Model model) {
        Optional<User> user = userRepository.findById(userId);
        model.addAttribute("allUsers", userRepository.findAll());
        if (user.isPresent()) {
            model.addAttribute("user", user);
            return "userupdate";
        }
        return "userupdate";
    }
}