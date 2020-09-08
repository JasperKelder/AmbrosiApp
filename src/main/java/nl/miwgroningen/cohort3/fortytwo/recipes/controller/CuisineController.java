package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cuisine;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */
@Controller
public class CuisineController {

    @Autowired
    CuisineRepository cuisineRepository;

    @GetMapping("/cuisine")
    protected String createCuisine(Model model) {
        model.addAttribute("cuisine", new Cuisine());
        return "cuisine";
    }

    @PostMapping({"/cuisine"})
    protected String saveCuisine(@ModelAttribute("cuisine") Cuisine cuisine, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        } else {
            cuisineRepository.save(cuisine);
            return "redirect:/cuisine";
        }
    }
}