package nl.miwgroningen.cohort3.fortytwo.recipes.controller;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cookbook;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CookbookRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.RecipeRepository;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.UserRepository;
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
public class CookbookController {

    @Autowired
    CookbookRepository cookbookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipeRepository recipeRepository;

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
    protected String showCookbook(Model model, Principal principal) {
        User currentUser = userRepository.findByEmailAddress(principal.getName());

        List<Cookbook> cookbooks = cookbookRepository.findAll();
        List<Cookbook> myCookbooks = new ArrayList<>();

        for (Cookbook cookbook : cookbooks) {
            if (currentUser.getUserId() == cookbook.getUser().getUserId()) {
                myCookbooks.add(cookbook);

            }
        }
        model.addAttribute("allMyCookbooks", myCookbooks);
        return "mycookbooks";
    }

    //method to get al recipes linked to selected cookbook
    @GetMapping("/myrecipes")
    protected String showRecipesForCookbook(Cookbook cookbook) {
        Cookbook currentCookbook = cookbookRepository.getOne(cookbook.getCookbookId());

        List<Recipe> recipes = recipeRepository.findAll();
        List<Recipe> myRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            if (currentCookbook.getCookbookId() ==
        }
    }

}
