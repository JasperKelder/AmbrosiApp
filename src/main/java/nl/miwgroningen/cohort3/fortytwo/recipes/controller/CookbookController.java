package nl.miwgroningen.cohort3.fortytwo.recipes.controller;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cookbook;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CookbookRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;
import java.util.List;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */

@Controller
public class CookbookController {

    @Autowired
    CookbookRepository cookbookRepository;

    @Autowired
    UserRepository userRepository;

    //method for creating new cookbook
    @GetMapping("/newcookbook")
    protected String createCookbook(Model model) {
        model.addAttribute("cookbook", new Cookbook());
        return "newcookbook";
    }

    @PostMapping("/newcookbook")
    protected String saveCookbook(@ModelAttribute("cookbook") Cookbook cookbook, Principal principal) {
        cookbook.setUser(userRepository.findByEmailAddress(principal.getName()));
        cookbookRepository.save(cookbook);

        return "redirect:/mykitchen";
    }

    //method to get all cookbooks linked to current user
    @GetMapping("/mycookbooks")
    protected String showCookbook(Model model) {
        List<Cookbook> cookbooks = cookbookRepository.findAll();
        model.addAttribute("allMyCookbooks", cookbookRepository.findAll());

        return "mycookbooks";
    }

}
