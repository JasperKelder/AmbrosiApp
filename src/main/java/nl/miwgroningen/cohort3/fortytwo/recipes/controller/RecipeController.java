package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import com.google.gson.Gson;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Ingredient;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Cookbook;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.Recipe;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.User;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.*;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.FileUploadService;
import nl.miwgroningen.cohort3.fortytwo.recipes.service.RemoveDuplicatesFromList;
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
    RemoveDuplicatesFromList removeDuplicatesFromList = new RemoveDuplicatesFromList();

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
        return "addrecipe";
    }

    @PostMapping({"/add"})
    protected String saveRecipe(@ModelAttribute("recipe") Recipe recipe, @ModelAttribute("cookbook") Cookbook cookbook,
                                @RequestParam("file") MultipartFile image,
                                @RequestParam("ingredientName[]") String[] ingredientName,
                                Principal principal, BindingResult result) throws IOException {
        // Create a list of recipes
        List<Recipe> recipeToCookbook = cookbook.getRecipes();
        if (result.hasErrors()) {
            return "addrecipe";
        }
        else{
            List<Ingredient> ingredients = new ArrayList<>();
            for (String string : ingredientName) {
                if (string != null && !string.trim().isEmpty()) {
                    Optional<Ingredient> ingredientOptional = ingredientRepository.findByIngredientName(string);
                    Ingredient ingredient = ingredientOptional.orElse(new Ingredient(string));
                    ingredients.add(ingredient);
                    recipe.setIngredients(ingredients);
                }
            }
            recipe.setUser(userRepository.findByEmailAddress(principal.getName()));
            // If there is no image uploaded, save default image.
            if (image.isEmpty()){
                recipe.setImage(null);
            }
            else {
                recipe.setImage(image.getBytes());
            }

            // This will add the recipe to the cookbook
            recipeToCookbook.add(recipe);

            // save the recipe in a cookbook with creation of recipe, not when updating the recipe:
            if (recipe.getRecipeId() == null) {
                cookbook.setRecipes(recipeToCookbook);
            }


            recipeRepository.save(recipe);
        }
        return "redirect:/mykitchen";
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
        model.addAttribute("allCategories", categoryRepository.findAll());
        return "index";
    }

    @GetMapping("/recipes")
    protected String showRecipesAdmin(Model model) {
        model.addAttribute("allRecipes", recipeRepository.findAll());
        return "adminrecipe";
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

        List<Ingredient> allIngredients = ingredientRepository.findAll();
        ArrayList<String> allIngredientNames = new ArrayList<>();
        for (Ingredient ingredient : allIngredients) {
            allIngredientNames.add(ingredient.getIngredientName());
        }
        Gson gson = new Gson();
        String allIngredientsJson = gson.toJson(allIngredientNames);
        model.addAttribute("allIngredientsJson", allIngredientsJson);

        if (recipe.isPresent()) {

            List<Ingredient> ingredientsRecipe = recipe.get().getIngredients();
            ArrayList<String> allIngredientsRecipe = new ArrayList<>();
            for (Ingredient ingredient : ingredientsRecipe) {
                allIngredientsRecipe.add(ingredient.getIngredientName());
            }
            String ingredientsRecipeJson = gson.toJson(allIngredientsRecipe);
            model.addAttribute("ingredientsRecipeJson", ingredientsRecipeJson);

            // If current image is present then convert it to base64 string so it can be displayed as a place holder
            String currentImage = fileUploadService.convertToBase64(recipe.get());
            model.addAttribute("currentImage", currentImage);
            model.addAttribute("recipe", recipe);
            return "addrecipe";
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
        List<String> imagesList = new ArrayList<>();
        for (Recipe recipe: searchResultsByIngredient) {
            if (!searchResults.contains(recipe)) {
                searchResults.add(recipe);
            }
            imagesList.add(fileUploadService.convertToBase64(recipe));
        }
        for (Recipe recipe: searchResults) {
            imagesList.add(fileUploadService.convertToBase64(recipe));
        }
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("allImages", imagesList);
        return "searchresults";
    }


    @GetMapping("/index/filterresults/{categoryId}")
    protected String showFilterResults(@PathVariable("categoryId") ArrayList<Integer> categoryIds, Model model) {
        // Create a new ArrayList with unique categoryIds
        ArrayList<Integer> newListWithoutDuplicates = removeDuplicatesFromList.removeDuplicates(categoryIds);
        // Create list for images
        List<String> imagesList = new ArrayList<>();
        List<Recipe> recipesFilteredByCategory = new ArrayList<>();

        // Add all recipes from the database to the recipesfilteredbycategory list
        for (int categoryId : newListWithoutDuplicates) {
            recipesFilteredByCategory.addAll(recipeRepository.categoryFilter(categoryId));
        }

        // Add images to the Recipes
        for (Recipe recipe : recipesFilteredByCategory) {
            imagesList.add(fileUploadService.convertToBase64(recipe));
        }
        model.addAttribute("recipesByCategory", recipesFilteredByCategory);
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allImages", imagesList);
        model.addAttribute("categoriesSelected", newListWithoutDuplicates);
        return "filterresults";
    }
}