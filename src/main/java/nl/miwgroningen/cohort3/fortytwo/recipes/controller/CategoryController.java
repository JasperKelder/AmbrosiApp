package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.Category;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.CategoryRepository;
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
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/category")
    protected String createCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("allCategories", categoryRepository.findAll());
        return "category";
    }

    @PostMapping({"/category"})
    protected String saveCategory(@ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        } else {
            categoryRepository.save(category);
            return "redirect:/category";
        }
    }

    @GetMapping("/category/delete/{categoryId}")
    protected String deleteCategory(@PathVariable("categoryId") final Integer categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return "forward:/category/";
        }
        return "forward:/category";
    }

    @GetMapping("/category/update/{categoryId}")
    protected String updateCategory(@PathVariable("categoryId") final Integer categoryId, Model model) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            model.addAttribute("category", category);
            return "category";
        }
        return "category";
    }
}