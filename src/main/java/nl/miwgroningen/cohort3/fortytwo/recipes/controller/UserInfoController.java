package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */
@Controller
public class UserInfoController {

    @Autowired
    UserRepository userRepository;

//    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserName(Principal principal) {
//        return principal.getName();
//    }

    @GetMapping("/userinfo")
    protected String showUser(Model model) {
        model.addAttribute("allUsers", userRepository.findAll());
        return "userinfo";
    }


//    @GetMapping("/userinfo/{userId}")
//    protected String showUserInfo(@PathVariable("userId") final Integer userId, Model model){
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            model.addAttribute("user", user.get());
//            return "userinfo";
//        }
//        return "redirect:/index";
//    }
//
//    // method to update userinfo using the userId
//    @GetMapping("/userinfo/update/{userId}")
//    protected String updateUserInfo(@PathVariable("userId") final Integer userId, Model model) {
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            model.addAttribute("user", user);
//            return "mykitchen";
//        }
//        return "index";
//    }

}