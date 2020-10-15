package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Autowired
    MeasuringUnitRepository measuringUnitRepository;

    @GetMapping("/add")
    protected String createRecipe(Model model, Principal principal) throws JsonProcessingException {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("cookbook", new Cookbook());
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allCuisines", cuisineRepository.findAll());
        // for some strange reason, you need to add this to the model here (even though it gets overwritten later):
        model.addAttribute("allMeasuringUnits", measuringUnitRepository.findAll());
        List<Ingredient> allIngredients = ingredientRepository.findAll();
        ArrayList<String> allIngredientNames = new ArrayList<>();
        for (Ingredient ingredient : allIngredients) {
            allIngredientNames.add(ingredient.getIngredientName());
        }
        Gson gson = new Gson();
        String allIngredientsJson = gson.toJson(allIngredientNames);
        model.addAttribute("allIngredientsJson", allIngredientsJson);

        Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String measuringUnitsToJson = gsonBuilder.toJson(measuringUnitRepository.findAll());
        model.addAttribute("allMeasuringUnits", measuringUnitsToJson);

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
                                @RequestParam("ingredientUnit[]") Integer[] ingredientUnit,
                                @RequestParam("ingredientQuantity[]") Integer[] ingredientQuantity,
                                Principal principal, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "addrecipe";
        } else {
            // Create a list of recipes
            List<Recipe> recipeToCookbook = cookbook.getRecipes();
            // If there is no image uploaded, save default image.
            if (image.isEmpty()) {
                recipe.setImage(null);
            } else {
                recipe.setImage(image.getBytes());
            }
            // a set of recipeIngredients must be filled with the wright ingredients, measuring units and quantities.
            Set<RecipeIngredient> recipeIngredients = new HashSet<>();
            for (int i = 0; i < ingredientName.length; i++) {
                MeasuringUnit measuringUnit = measuringUnitRepository.findByMeasuringUnitId(ingredientUnit[i]);
                if (ingredientName[i] != null && !ingredientName[i].trim().isEmpty()) {
                    // first check if the ingredient and measuring unit combination is already in the database.
                    List<Ingredient> ingredientOptional = ingredientRepository.findByIngredientName(ingredientName[i]);
                    Ingredient ingredient = null;
                    for (Ingredient ingredientOfList: ingredientOptional) {
                        if (ingredientOfList.getMeasuringUnit() == measuringUnit) {
                            ingredient = ingredientOfList;
                        }
                    }
                    // when it's a new ingredient / measuring unit combination, save it in the database.
                    if (ingredient == null) {
                        ingredient = new Ingredient(ingredientName[i]);
                        ingredient.setMeasuringUnit(measuringUnit);
                        ingredientRepository.save(ingredient);
                    }
                    RecipeIngredient recipeIngredient = new RecipeIngredient();
                    recipeIngredient.setIngredient(ingredient);
                    recipeIngredient.setQuantity(ingredientQuantity[i]);
                    recipeIngredients.add(recipeIngredient);
                }
            }

            //when updating a recipe, the recipe has an id
            if (recipe.getRecipeId() != null) {
                // because of the recipeIngredients it is not possible to save the recipe directly in the database (it
                // becomes a detached entity). Getting the recipe from the database, altering it and than saving it does
                // work.
                recipeIngredientRepository.deleteRecipeIngredientsByRecipeId(recipe.getRecipeId());
                Optional<Recipe> currentRecipe = recipeRepository.findById(recipe.getRecipeId());
                if (currentRecipe.isPresent()) {
                    currentRecipe.get().setRecipeTitle(recipe.getRecipeTitle());
                    currentRecipe.get().setRecipePreperation(recipe.getRecipePreperation());
                    currentRecipe.get().setPreperationTime(recipe.getPreperationTime());
                    currentRecipe.get().setServings(recipe.getServings());
                    for (RecipeIngredient ri : recipeIngredients) {
                        ri.setRecipe(currentRecipe.get());
                    }
                    currentRecipe.get().setRecipeIngredients(recipeIngredients);
                    currentRecipe.get().setCooktime(recipe.getCooktime());
                    currentRecipe.get().setCuisineName(recipe.getCuisineName());
                    currentRecipe.get().setCategoryName(recipe.getCategoryName());
                    currentRecipe.get().setImage(recipe.getImage());
                    recipeRepository.save(currentRecipe.get());
                    return "redirect:/mykitchen";
                }
            } else {
                // this is for the new recipes, it has to be after the updating recipe, because the recipe gets an id
                // after saving it.
                recipe.setUser(userRepository.findByEmailAddress(principal.getName()));
                cookbook.setRecipes(recipeToCookbook);
                recipeRepository.save(recipe);
                for (RecipeIngredient recipeIngredient : recipeIngredients) {
                    recipeIngredient.setRecipe(recipe);
                    recipeIngredientRepository.save(recipeIngredient);
                }
            }
            return "redirect:/mykitchen";
        }
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
            return "forward:/recipes";
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
        // for some strange reason, you need to add this to the model here (even though it gets overwritten later):
        model.addAttribute("allMeasuringUnits", measuringUnitRepository.findAll());

        Gson gsonBuilder1 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String measuringUnitsToJson = gsonBuilder1.toJson(measuringUnitRepository.findAll());
        model.addAttribute("allMeasuringUnits", measuringUnitsToJson);

        // generate a list of all the ingredient names and convert to Json (for the autocomplete).
        List<Ingredient> allIngredients = ingredientRepository.findAll();
        ArrayList<String> allIngredientNames = new ArrayList<>();
        for (Ingredient ingredient : allIngredients) {
            allIngredientNames.add(ingredient.getIngredientName());
        }
        Gson gson = new Gson();
        String allIngredientsJson = gson.toJson(allIngredientNames);
        model.addAttribute("allIngredientsJson", allIngredientsJson);

        if (recipe.isPresent()) {
            // convert all the recipeIngredients to Json.
            Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String recipeToJson = gsonBuilder.toJson(recipe.get().getRecipeIngredients());
            model.addAttribute("recipeToJson", recipeToJson);

            // If current image is present then convert it to base64 string so it can be displayed as a place holder.
            String currentImage = fileUploadService.convertToBase64(recipe.get());
            model.addAttribute("currentImage", currentImage);
            model.addAttribute("recipe", recipe.get());
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
        // Easter egg
        if (searchTerm.equals("42")) {
            return "/draw";
        }
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
}