package nl.miwgroningen.cohort3.fortytwo.recipes.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.miwgroningen.cohort3.fortytwo.recipes.model.*;
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

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    MeasuringUnitRepository measuringUnitRepository;


    @GetMapping("/add")
    protected String createRecipe(Model model, Principal principal) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allCuisines", cuisineRepository.findAll());
        // for some strange reason, you need to add this to the model here (even though it gets overwritten later):
        model.addAttribute("allMeasuringUnits", measuringUnitRepository.findAll());
        List<String> allIngredientNames = ingredientRepository.getDistinctIngredientNames();
        Gson gson = new Gson();
        String allIngredientsJson = gson.toJson(allIngredientNames);
        model.addAttribute("allIngredientsJson", allIngredientsJson);

        Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String measuringUnitsToJson = gsonBuilder.toJson(measuringUnitRepository.findAll());
        model.addAttribute("allMeasuringUnits", measuringUnitsToJson);
        return "addrecipe";
    }

    @PostMapping({"/add"})
    protected String saveRecipe(@ModelAttribute("recipe") Recipe recipe,
                                @RequestParam("file") MultipartFile image,
                                @RequestParam("ingredientName[]") String[] ingredientName,
                                @RequestParam("ingredientUnit[]") Integer[] ingredientUnit,
                                @RequestParam("ingredientQuantity[]") Integer[] ingredientQuantity,
                                @RequestParam("preparationlist[]") String[] preparationSteps,
                                Principal principal, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "addrecipe";
        }
        // If there is no image uploaded, save default image.
        byte[] imageInBytes = image.isEmpty()? null : image.getBytes();
        recipe.setImage(imageInBytes);

        User currentUser = userRepository.findByEmailAddress(principal.getName());

        // a set of recipeIngredients must be filled with the wright ingredients, measuring units and quantities.
        Set<RecipeIngredient> recipeIngredients = makeRecipeIngredientSet(ingredientName, ingredientUnit,
                ingredientQuantity);

        // create list with preparationsteps
        List<PreparationStep> preparationStepslist = new ArrayList<>();
        for (String step : preparationSteps) {
            if (step != null && !step.trim().isEmpty()) {
                preparationStepslist.add(new PreparationStep(step));
            }
        }
        //when updating a recipe, the recipe has an id
        if (recipe.getRecipeId() != null) {
            // Only the recipe owner and the admin user can update a recipe
            Recipe currentRecipe = recipeRepository.getOne(recipe.getRecipeId());
            if (currentUser != currentRecipe.getUser() && !currentUser.hasAdminRole()) {
                return "redirect:/index";
            }
            // because of the recipeIngredients it is not possible to save the recipe directly in the database (it
            // becomes a detached entity). Getting the recipe from the database, altering it and than saving it does
            // work.
            recipeIngredientRepository.deleteRecipeIngredientsByRecipeId(recipe.getRecipeId());
            currentRecipe = recipeRepository.getOne(recipe.getRecipeId());
            currentRecipe.setRecipeTitle(recipe.getRecipeTitle());
            currentRecipe.setPreparationStepList(preparationStepslist);
            currentRecipe.setPreperationTime(recipe.getPreperationTime());
            currentRecipe.setServings(recipe.getServings());
            for (RecipeIngredient ri : recipeIngredients) {
                ri.setRecipe(currentRecipe);
            }
            currentRecipe.setRecipeIngredients(recipeIngredients);
            currentRecipe.setCooktime(recipe.getCooktime());
            currentRecipe.setCuisineName(recipe.getCuisineName());
            currentRecipe.setCategoryName(recipe.getCategoryName());
            if (!image.isEmpty()) {
                currentRecipe.setImage(recipe.getImage());
            }
            recipeRepository.save(currentRecipe);
            return "redirect:/mykitchen";
        }
        // this is for the new recipes, it has to be after the updating recipe, because the recipe gets an id
        // after saving it.
        recipe.setUser(currentUser);
        recipe.setPreparationStepList(preparationStepslist);
        recipeRepository.save(recipe);
        for (RecipeIngredient recipeIngredient : recipeIngredients) {
            recipeIngredient.setRecipe(recipe);
            recipeIngredientRepository.save(recipeIngredient);
        }
        return "redirect:/mykitchen";
    }


    //this is a method to create a set of ingredients with the wright measuring units and quantities
    private Set<RecipeIngredient> makeRecipeIngredientSet(String[] ingredientName, Integer[] ingredientUnit,
                                                          Integer[] ingredientQuantity) {
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
                if (ingredientQuantity.length > 0) {
                    recipeIngredient.setQuantity(ingredientQuantity[i]);
                }
                recipeIngredients.add(recipeIngredient);
            }
        }
        return recipeIngredients;
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

    @GetMapping("/userrecipes/{userId}")
    protected String showRecipesUser(@PathVariable("userId") final Integer userId, Model model) {
        List<Recipe> recipes = recipeRepository.userRecipes(userId);
        List<String> imagesList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            imagesList.add(fileUploadService.convertToBase64(recipe));
        }
        model.addAttribute("allUserImages", imagesList);
        model.addAttribute("allUserRecipes", recipeRepository.userRecipes(userId));
        model.addAttribute("user", userRepository.findById(userId).get());
        return "userrecipes";
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
        // for some strange reason, you need to add this to the model here (even though it gets overwritten later):
        model.addAttribute("allMeasuringUnits", measuringUnitRepository.findAll());

        Gson gsonBuilder1 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String measuringUnitsToJson = gsonBuilder1.toJson(measuringUnitRepository.findAll());
        model.addAttribute("allMeasuringUnits", measuringUnitsToJson);

        // generate a list of all the ingredient names and convert to Json (for the autocomplete).
        List<String> allIngredientNames = ingredientRepository.getDistinctIngredientNames();
        Gson gson = new Gson();
        String allIngredientsJson = gson.toJson(allIngredientNames);
        model.addAttribute("allIngredientsJson", allIngredientsJson);

        if (recipe.isPresent()) {
            // convert all the recipeIngredients to Json.
            Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String recipeToJson = gsonBuilder.toJson(recipe.get().getRecipeIngredients());
            model.addAttribute("recipeToJson", recipeToJson);

            // convert all the preparationsteps to Json.
            Gson prepStepsBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String prepstepsToJson = prepStepsBuilder.toJson(recipe.get().getPreparationStepList());
            model.addAttribute("prepstepsToJson", prepstepsToJson);

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