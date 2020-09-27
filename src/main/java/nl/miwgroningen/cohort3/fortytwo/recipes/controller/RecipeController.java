package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import com.google.gson.Gson;
import nl.miwgroningen.cohort3.fortytwo.recipes.dto.LabelValueDto;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Ingredient;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.*;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.List;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

/**
 * @author Jasper Kelder, Nathalie Antoine, Reinout Smit, Jasmijn van der Veen
 */
@Controller
public class RecipeController {

    //Specify the number of characters that you must type in before the autocomplete gives suggestions. If you want to
    // change this, you also have to change this number in the javascript autocomplete script.
    private static final Integer NR_OF_CHARACTERS_AUTOCOMPLETE = 1;

    FileUploadService fileUploadService = new FileUploadService();

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CuisineRepository cuisineRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    UserRepository userRepository;

    private List<Ingredient> allIngredientsFiltered;

    @GetMapping("/add")
    protected String createRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allCuisines", cuisineRepository.findAll());
        List<Ingredient> allIngredients = ingredientRepository.findAll();
        ArrayList<String> allIngredientNames = new ArrayList<>();
        for (Ingredient ingredient : allIngredients) {
            allIngredientNames.add(ingredient.getIngredientName());
        }
        Gson gson = new Gson();
        String allIngredientsJson = gson.toJson(allIngredientNames);
        model.addAttribute("allIngredientsJson", allIngredientsJson);
        model.addAttribute("allIngredients", allIngredients);
        return "add";
    }

    @PostMapping({"/add"})
    protected String saveRecipe(@ModelAttribute("recipe") Recipe recipe, @RequestParam("file") MultipartFile image,
                                @RequestParam("ingredientName") String ingredientName,
                                Principal principal, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "add";
        }
        else{
            List<Ingredient> ingredients = new ArrayList<>();
            Optional<Ingredient> ingredientOptional = ingredientRepository.findByIngredientName(ingredientName);
            Ingredient ingredient = ingredientOptional.orElse(new Ingredient(ingredientName));
            ingredients.add(ingredient);
            recipe.setIngredients(ingredients);
            recipe.setUser(userRepository.findByEmailAddress(principal.getName()));
            // If there is no image uploaded, save default image.
            if (image.isEmpty()){
                recipe.setImage(null);
            }
            else {
                recipe.setImage(image.getBytes());
            }
            recipeRepository.save(recipe);
        }
            return "redirect:/index";
    }

    @GetMapping({"/index", "/"})
    protected String showRecipes(Model model) {
        List<Recipe> recipes = recipeRepository.findAll();
        List<String> imagesList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            imagesList.add(fileUploadService.convertToBase64(recipe));
        }
        model.addAttribute("allRecipes", recipeRepository.findAll());
        model.addAttribute("allImages", imagesList);

        return "index";
    }

    @GetMapping("/indexloggedin")
    protected String showRecipesLoggedIn(Model model) {
        List<Recipe> recipes = recipeRepository.findAll();
        List<String> imagesList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            imagesList.add(fileUploadService.convertToBase64(recipe));
        }
        model.addAttribute("allRecipes", recipeRepository.findAll());
        model.addAttribute("allImages", imagesList);
        return "indexloggedin";
    }

    @GetMapping("/recipes")
    protected String showRecipesAdmin(Model model) {
        model.addAttribute("allRecipes", recipeRepository.findAll());
        return "recipe";
    }

    @GetMapping({"/index/delete/{recipeId}", "/recipes/delete/{recipeId}"})
    protected String deleteRecipe(@PathVariable("recipeId") final Integer recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            recipeRepository.delete(recipe.get());
            return "forward:/recipes/";
        }
        return "forward:/recipes";
    }

    @GetMapping("/add/update/{recipeId}")
    protected String updateRecipe(@PathVariable("recipeId") final Integer recipeId, Model model) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allCuisines", cuisineRepository.findAll());
        model.addAttribute("allIngredients", ingredientRepository.findAll());
        if (recipe.isPresent()) {
            // If current image is present then convert it to base64 string so it can be displayed as a place holder
            String currentImage = fileUploadService.convertToBase64(recipe.get());
            model.addAttribute("currentImage", currentImage);
            model.addAttribute("recipe", recipe);
            return "add";
        }
        return "index";
    }

    @GetMapping("/view/{id}")
    protected String showRecipe(@PathVariable("id") final Integer recipeId, Model model) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
            model.addAttribute("image", fileUploadService.convertToBase64(recipe.get()));
            return "view";
        }
        return "redirect:/index";
    }

    @GetMapping("/viewloggedin/{id}")
    protected String showRecipeLoggedIn(@PathVariable("id") final Integer recipeId, Model model) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
            model.addAttribute("image", fileUploadService.convertToBase64(recipe.get()));
            return "viewloggedin";
        }
        return "redirect:/indexloggedin";
    }

//    @RequestMapping(value = "/ingredientAutocomplete")
//    @ResponseBody
//    public List<LabelValueDto> ingredientAutocomplete(@RequestParam(value = "term", required = false,
//            defaultValue = "") String term) {
//        List<LabelValueDto> suggestions = new ArrayList<>();
//        try {
//            if (term.length() == NR_OF_CHARACTERS_AUTOCOMPLETE) {
//                allIngredientsFiltered = ingredientRepository.getSuggestions(term);
//            }
//            for (Ingredient ingredient : allIngredientsFiltered) {
//                if (ingredient.getIngredientName().contains(term)) {
//                    LabelValueDto labelValueDto = new LabelValueDto();
//                    labelValueDto.setLabel(ingredient.getIngredientName());
//                    labelValueDto.setValue(Integer.toString(ingredient.getIngredientId()));
//                    suggestions.add(labelValueDto);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return suggestions;
//    }

    @RequestMapping(value = "/ingredientAutocomplete")
    @ResponseBody
    public String ingredientAutocomplete() {
        ArrayList<String> suggestions = new ArrayList<>();
        try {
            allIngredientsFiltered = ingredientRepository.getSuggestions("");
            for (Ingredient ingredient : allIngredientsFiltered) {
                suggestions.add(ingredient.getIngredientName());
                System.out.println("ok");
                System.out.println(ingredient.getIngredientName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String suggestionsJson = gson.toJson(suggestions);
        System.out.println(suggestionsJson);
        return suggestionsJson;
    }

}