package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.dto.PasswordChangeDto;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Controller
@RequestMapping("/changepassword")
public class ChangePasswordController {

    private UserService userService;

    public ChangePasswordController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public PasswordChangeDto passwordChangeDto() {
        return new PasswordChangeDto();
    }
    
    @GetMapping
    public String showPasswordChangeForm() {
        return "changepassword";
    }
    
    @PostMapping
    public String saveNewPassword(@ModelAttribute("user") PasswordChangeDto passwordChangeDto) {
        userService.save(passwordChangeDto);
        return "redirect:/changepassword?success";
    }
    
}

