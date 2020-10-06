package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cuisine;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CuisineRepository;
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
public class CuisineController {

    @Autowired
    CuisineRepository cuisineRepository;

    @GetMapping("/cuisine")
    protected String createCuisine(Model model) {
        model.addAttribute("cuisine", new Cuisine());
        model.addAttribute("allCuisines", cuisineRepository.findAll());
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

    @GetMapping("/cuisine/delete/{cuisineId}")
    protected String deleteCuisine(@PathVariable("cuisineId") final Integer cuisineId) {
        Optional<Cuisine> cuisine = cuisineRepository.findById(cuisineId);
        if (cuisine.isPresent()) {
            cuisineRepository.delete(cuisine.get());
            return "forward:/cuisine/";
        }
        return "forward:/cuisine";
    }

    @GetMapping("/cuisine/update/{cuisineId}")
    protected String updateCuisine(@PathVariable("cuisineId") final Integer cuisineId, Model model) {
        Optional<Cuisine> cuisine = cuisineRepository.findById(cuisineId);
        model.addAttribute("allCuisines", cuisineRepository.findAll());
        if (cuisine.isPresent()) {
            model.addAttribute("cuisine", cuisine);
            return "cuisine";
        }
        return "cuisine";
    }
}