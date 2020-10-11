package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.*;
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

    @Autowired
    CookbookRepository cookbookRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    @GetMapping("/add")
    protected String createRecipe(Model model, Principal principal) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("cookbook", new Cookbook());
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

        // gets current users cookbooks
        User currentUser = userRepository.findByEmailAddress(principal.getName());
        List<Cookbook> userCookbooks = cookbookRepository.getCookbookByUserId(currentUser.getUserId());
        model.addAttribute("allUserCookbooks", userCookbooks);
        return "add";
    }

    @PostMapping({"/add"})
    protected String saveRecipe(@ModelAttribute("recipe") Recipe recipe, @ModelAttribute("cookbook") Cookbook cookbook,
                                @RequestParam("file") MultipartFile image,
                                @RequestParam("ingredientName[]") String[] ingredientName,
                                Principal principal, BindingResult result) throws IOException {

        // Create a list of recipes
        List<Recipe> recipeToCookbook = cookbook.getRecipes();
        if (result.hasErrors()) {
            return "add";
        }
        else {
            // If there is no image uploaded, save default image.
            if (image.isEmpty()) {
                recipe.setImage(null);
            } else {
                recipe.setImage(image.getBytes());
            }
            Set<RecipeIngredient> recipeIngredients = new HashSet<>();
            for (String string : ingredientName) {
                if (string != null && !string.trim().isEmpty()) {
                    Optional<Ingredient> ingredientOptional = ingredientRepository.findByIngredientName(string);
                    Ingredient ingredient;
                    if (ingredientOptional.isPresent()) {
                        ingredient = ingredientOptional.get();
                    } else {
                        ingredient = new Ingredient(string);
                        ingredientRepository.save(ingredient);
                    }
                    RecipeIngredient recipeIngredient = new RecipeIngredient();
                    recipeIngredient.setIngredient(ingredient);
                    recipeIngredients.add(recipeIngredient);
                }
            }

            //when updating a recipe, the recipe has an id
            if (recipe.getRecipeId() != null) {
                recipeIngredientRepository.deleteRecipeIngredientsByRecipeId(recipe.getRecipeId());
                Optional<Recipe> currentRecipe = recipeRepository.findById(recipe.getRecipeId());
                if (currentRecipe.isPresent()) {
                    currentRecipe.get().setRecipeTitle(recipe.getRecipeTitle());
                    currentRecipe.get().setCategoryName(recipe.getCategoryName());
                    currentRecipe.get().setCuisineName(recipe.getCuisineName());
                    currentRecipe.get().setCooktime(recipe.getCooktime());
                    currentRecipe.get().setPreperationTime(recipe.getPreperationTime());
                    currentRecipe.get().setRecipePreperation(recipe.getRecipePreperation());
                    currentRecipe.get().setServings(recipe.getServings());
                    for (RecipeIngredient ri : recipeIngredients) {
                        ri.setRecipe(currentRecipe.get());
                    }
                    currentRecipe.get().setRecipeIngredients(recipeIngredients);
                    currentRecipe.get().setImage(recipe.getImage());
                    recipeRepository.save(currentRecipe.get());
                    return "redirect:/index";
                }
            } else {
                recipe.setUser(userRepository.findByEmailAddress(principal.getName()));
                cookbook.setRecipes(recipeToCookbook);
                recipeRepository.save(recipe);
                for (RecipeIngredient recipeIngredient : recipeIngredients) {
                    recipeIngredient.setRecipe(recipe);
                    recipeIngredientRepository.save(recipeIngredient);
                }
            }
            return "redirect:/index";
        }
    }

    @GetMapping({"/index", "/"})
    protected String showRecipes(Model model) {
        List<Recipe> recipes = recipeRepository.findAll();
        List<String> imagesList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            imagesList.add(fileUploadService.convertToBase64(recipe));
        }

        Optional<Recipe> testRecipe = recipeRepository.findById(1);
        if (testRecipe.isPresent()) {
            Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String testIngredients = gsonBuilder.toJson(testRecipe.get().getRecipeIngredients());
            System.out.println(testIngredients);
        }

        model.addAttribute("allRecipes", recipeRepository.findAll());
        model.addAttribute("allImages", imagesList);

        return "index";
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
    protected String updateRecipe(@PathVariable("recipeId") final Integer recipeId, Model model, Principal principal) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        User user = userRepository.findByEmailAddress(principal.getName());
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allCuisines", cuisineRepository.findAll());
        model.addAttribute("allUserCookbooks", cookbookRepository.getCookbookByUserId(user.getUserId()));
        model.addAttribute("allIngredients", ingredientRepository.findAll());

        // generate a list of all the ingredient names and convert to Json (for the autocomplete)
        List<Ingredient> allIngredients = ingredientRepository.findAll();
        ArrayList<String> allIngredientNames = new ArrayList<>();
        for (Ingredient ingredient : allIngredients) {
            allIngredientNames.add(ingredient.getIngredientName());
        }
        Gson gson = new Gson();
        String allIngredientsJson = gson.toJson(allIngredientNames);
        model.addAttribute("allIngredientsJson", allIngredientsJson);

        if (recipe.isPresent()) {
            Set<RecipeIngredient> ingredientsRecipe = recipe.get().getRecipeIngredients();
            ArrayList<String> allIngredientsRecipe = new ArrayList<>();
            for (RecipeIngredient ri: ingredientsRecipe) {
                allIngredientsRecipe.add(ri.getIngredient().getIngredientName());
            }
            String ingredientsRecipeJson = gson.toJson(allIngredientsRecipe);
            model.addAttribute("ingredientsRecipeJson", ingredientsRecipeJson);

            // If current image is present then convert it to base64 string so it can be displayed as a place holder
            String currentImage = fileUploadService.convertToBase64(recipe.get());
            model.addAttribute("currentImage", currentImage);
            model.addAttribute("recipe", recipe.get());
            return "add";
        }
        return "index";
    }

    @GetMapping("/viewrecipe/{id}")
    protected String showRecipe(@PathVariable("id") final Integer recipeId, Model model) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
            model.addAttribute("image", fileUploadService.convertToBase64(recipe.get()));
            return "viewrecipe";
        }
        return "redirect:/index";
    }

    @GetMapping("/searchresults/{searchterm}")
    protected String showSearchResults(@PathVariable("searchterm") String searchTerm, Model model) {
        List<Recipe> searchResults = recipeRepository.getSuggestions(searchTerm);
        List<Recipe> searchResultsByIngredient = recipeRepository.getSuggestionsByIngredient(searchTerm);
        for (Recipe recipe: searchResultsByIngredient) {
            if (!searchResults.contains(recipe)) {
                searchResults.add(recipe);
            }
        }
        model.addAttribute("searchResults", searchResults);
        return "searchresults";
    }

}