package nl.miwgroningen.cohort3.fortytwo.recipes.controller;


import nl.miwgroningen.cohort3.fortytwo.recipes.model.Ingredient;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;

@Controller
public class IngredientController {
    @Autowired
    IngredientRepository ingredientRepository;

    @GetMapping("/ingredients")
    protected String getInfo(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        return "adminingredients";
    }

    @GetMapping("/ingredients/delete/{ingredientId}")
    protected String deleteIngredient(@PathVariable("ingredientId") final Integer ingredientId) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        if (ingredient.isPresent()) {
            ingredientRepository.delete(ingredient.get());
            return "forward:/ingredients/";
        }
        return "forward:/ingredients/";
    }

    @PostMapping({"/ingredients"})
    protected String saveIngredient(@ModelAttribute("ingredient") Ingredient ingredient, BindingResult result) {
        if (result.hasErrors()) {
            return "adminingredients";
        } else {
            ingredientRepository.save(ingredient);
            return "redirect:/ingredients";
        }
    }

    @GetMapping("/ingredients/update/{ingredientId}")
    protected String updateIngredient(@PathVariable("ingredientId") final Integer ingredientId, Model model) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        if (ingredient.isPresent()) {
            model.addAttribute("ingredient", ingredient);
            return "adminingredients";
        }
        return "adminingredients";
    }

}



