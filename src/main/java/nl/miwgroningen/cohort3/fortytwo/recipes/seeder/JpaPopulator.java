package nl.miwgroningen.cohort3.fortytwo.recipes.seeder;

import nl.miwgroningen.cohort3.fortytwo.recipes.model.*;
import nl.miwgroningen.cohort3.fortytwo.recipes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*
* This class will take JSON objects and convert it to datasources which can be interpreted by the SQL database.
* */
@Component
public class JpaPopulator implements CommandLineRunner, seedTablesInterface {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CuisineRepository cuisineRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CookbookRepository cookbookRepository;

    @Autowired
    MeasuringUnitRepository measuringUnitRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    PreparationStepRepository preparationStepRepository;

    @Override
    public void run(String... args) throws Exception {
        seedMeasuringUnit();
        seedCategory();
        seedCuisine();
        seedUser();
        seedIngredient();
        seedRecipeAndCookbookAndPreparationSteps();
        seedRecipeIngredient();
        seedCookbook();
    }

    public void seedCategory() {
        if (categoryRepository.count() == 0) {
            Category category1 = new Category("Breakfast");
            Category category2 = new Category("Lunch");
            Category category3 = new Category("Dinner");
            Category category4 = new Category("Snacks");
            Category category5 = new Category("Cheating");

            categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4, category5));
        }
    }

    @Override
    public void seedCuisine() {
        if (cuisineRepository.count() == 0) {
            Cuisine cuisine1 = new Cuisine("Italian");
            Cuisine cuisine2 = new Cuisine("Asian");
            Cuisine cuisine3 = new Cuisine("Dutch");
            Cuisine cuisine4 = new Cuisine("Oriental");
            Cuisine cuisine5 = new Cuisine("Spanisch");
            Cuisine cuisine6 = new Cuisine("Greek");
            Cuisine cuisine7 = new Cuisine("American");
            Cuisine cuisine8 = new Cuisine("Caribbean");
            Cuisine cuisine9 = new Cuisine("Indian");

            cuisineRepository.saveAll(Arrays.asList(cuisine1, cuisine2, cuisine3, cuisine4, cuisine5, cuisine6, cuisine7, cuisine8, cuisine9));
        }
    }

    @Override
    public void seedUser() {
        if (userRepository.count() == 0 && roleRepository.count() == 0) {
            //Normally only admin user is added with seeder, for demo purpose also added 4 team members as users, password = password
            User userAdmin = new User("Admin", "Admin", "admin@admin.nl", "$2a$10$B4jSaArVmAgd926i04LPx.P64kSKRsXc0bPY5/wkDr.7AJjwdwL2C");
            User user1 = new User("Reinout", "Smit", "reinout@smit.nl", "$2a$10$gaQxAP/k53OqKru//IlVkO8Gsepm20v4NrUBXTASB2NbjmLnheQs6");
            User user2 = new User("Jasper", "Kelder", "jasper@kelder.nl", "$2a$10$gaQxAP/k53OqKru//IlVkO8Gsepm20v4NrUBXTASB2NbjmLnheQs6");
            User user3 = new User("Jasmijn", "van der Veen", "jasmijn@vanderveen.nl", "$2a$10$gaQxAP/k53OqKru//IlVkO8Gsepm20v4NrUBXTASB2NbjmLnheQs6");
            User user4 = new User("Nathalie", "Antoine", "nathalie@antoine.nl", "$2a$10$gaQxAP/k53OqKru//IlVkO8Gsepm20v4NrUBXTASB2NbjmLnheQs6");
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");

            userAdmin.setRoles(Arrays.asList(roleAdmin));
            user1.setRoles(Arrays.asList(roleUser));
            user2.setRoles(Arrays.asList(roleUser));
            user3.setRoles(Arrays.asList(roleUser));
            user4.setRoles(Arrays.asList(roleUser));

            userRepository.saveAll(Arrays.asList(userAdmin, user1, user2, user3, user4));
        }
    }

    @Override
    public void seedRecipeAndCookbookAndPreparationSteps() throws IOException {
        // First declare the images you want to use in the recipes
        byte[]caramelpieImage = imageFromFileToByteArray("src/main/resources/static/images/demo/1- Salted-caramel-pie.jpg");
        byte[]squashSaladImage = imageFromFileToByteArray("src/main/resources/static/images/demo/2- squash salad.jpg");
        byte[]chiaPuddingImage = imageFromFileToByteArray("src/main/resources/static/images/demo/3- ChiaPudding.jpg");
        byte[]codImage = imageFromFileToByteArray("src/main/resources/static/images/demo/4- cod-chorizo.jpg");
        byte[]cookieDoughImage = imageFromFileToByteArray("src/main/resources/static/images/demo/5- Cookie-dough-truffels.jpg");
        byte[]tunaImage = imageFromFileToByteArray("src/main/resources/static/images/demo/6- sesame tuna.jpg");
        byte[]gnocciImage = imageFromFileToByteArray("src/main/resources/static/images/demo/7-gnocchi-peas.jpg");
        byte[]yoghurtBallsImage = imageFromFileToByteArray("src/main/resources/static/images/demo/8- yoghurtballs.jpg");
        byte[]pinappleCaqrpaccioImage = imageFromFileToByteArray("src/main/resources/static/images/demo/9- pineapple carpaccio.jpg");
        byte[]bananabreadImage = imageFromFileToByteArray("src/main/resources/static/images/demo/10- raspberrieBananabread.jpg");
        byte[]lemoniceImage = imageFromFileToByteArray("src/main/resources/static/images/demo/11- lemonice.jpg");
        byte[]orzoImage = imageFromFileToByteArray("src/main/resources/static/images/demo/12- Orzo-with-bacon-and-goatchees.jpg");
        byte[]pancakesImage = imageFromFileToByteArray("src/main/resources/static/images/demo/13- American-pancakes.jpg");
        byte[]crumblebarsImage = imageFromFileToByteArray("src/main/resources/static/images/demo/14- Crumble-bars-bluberries.jpg");
        byte[]curryImage = imageFromFileToByteArray("src/main/resources/static/images/demo/15- Curry.jpg");
        byte[]breakfastpieImage = imageFromFileToByteArray("src/main/resources/static/images/demo/16- breakfastpie-blueberriesAndLemon.jpg");
        byte[]aspergeQuicheImage = imageFromFileToByteArray("src/main/resources/static/images/demo/17- Aspergequiche.jpg");
        byte[]parsnipSoupImage = imageFromFileToByteArray("src/main/resources/static/images/demo/18- ParsnipSoup.jpg");
        byte[]hempSmootieImage = imageFromFileToByteArray("src/main/resources/static/images/demo/19- HempSmoothie.jpg");
        byte[]falafelImage = imageFromFileToByteArray("src/main/resources/static/images/demo/20- falafel.jpg");
        byte[]beefCurryImage = imageFromFileToByteArray("src/main/resources/static/images/demo/21-beefcurry.jpg");
        byte[]burrataSaladImage = imageFromFileToByteArray("src/main/resources/static/images/demo/22- salade-burrata-black-olives.jpg");
        byte[]carbonaraImage = imageFromFileToByteArray("src/main/resources/static/images/demo/23- sausage carbonara.jpg");
        byte[]datesImage = imageFromFileToByteArray("src/main/resources/static/images/demo/24-dates.jpg");
        byte[]pineapplericeImage = imageFromFileToByteArray("src/main/resources/static/images/demo/25-pineapple-rice.jpg");

        // Add the preperationSteps per recipe and save them to a list
        PreparationStep preparationStep1 = new PreparationStep("Grind the cookies in the food processor. Meanwhile, melt 100 grams of butter and stir into the cookie crumbs.");
        PreparationStep preparationStep2 = new PreparationStep("Line a tin (we use a 24cm tin) with parchment paper, grease the sides of the tin with some butter and then divide the biscuit mixture over the bottom. Make sure you also make the border about 2 inches high. Press down well with a spoon and put in it in the fridge.");
        PreparationStep preparationStep3 = new PreparationStep("Now melt the rest of the butter in a pot. Then add the condensed milk and caster sugar and keep stirring. It takes about 10 minutes for the caramel to have the right consistency.");
        PreparationStep preparationStep4 = new PreparationStep("After 5 minutes, add 2 teaspoons of sea salt and keep stirring.");
        PreparationStep preparationStep5 = new PreparationStep("Remove the tin from the refrigerator and divide the caramel over it. Put the tin back in the refrigerator and let it cool for an hour.");
        PreparationStep preparationStep6 = new PreparationStep("Meanwhile, melt the chocolate together with 100 milliliters of milk au-bain marie (in a bowl over a pan of boiling water). Pour the chocolate over the caramel layer once it has cooled down properly. Put the cake in the fridge for another 30 minutes, so that the chocolate hardens.");
        PreparationStep preparationStep7 = new PreparationStep("Melt 100 grams of fudge with the rest of the milk in a pot until caramel. In the meantime, cut the rest of the fudge into small cubes. Finish the cake with the fudge cubes, 2 teaspoons of sea salt and a drizzle of the caramel on top!");
        PreparationStep preparationStep8 = new PreparationStep("Preheat the oven to 180ºC/350ºF/gas 4.");
        PreparationStep preparationStep9 = new PreparationStep("Carefully cut the squash into rough 5cm chunks (seeds and all). Then, in a roasting tray, rub all over with the harissa, 1 tablespoon of olive oil and a pinch of sea salt and black pepper.");
        PreparationStep preparationStep10 = new PreparationStep("Roast for 50 minutes, or until soft, golden and gnarly.");
        PreparationStep preparationStep11 = new PreparationStep("With a few minutes to go, place 1 tablespoon each of extra virgin olive oil and red wine vinegar, and a little salt and pepper, in a large bowl.");
        PreparationStep preparationStep12 = new PreparationStep("Halve, peel, destone, slice and toss in the avocados, then gently mix in the salad leaves.");
        PreparationStep preparationStep13 = new PreparationStep("Use forks to divide and tear the hot squash (skin, seeds and all) between your plates. Divide up the salad on top and tear over the mozzarella, then serve.");
        PreparationStep preparationStep14 = new PreparationStep("Mash the bananas using a fork until smooth (the riper the bananas are, the easier this will be).");
        PreparationStep preparationStep15 = new PreparationStep("Combine with the remaining ingredients in a mixing bowl and stir well. Place the bowl in the fridge for 15 minutes, then give the pudding a quick stir and place it back in the fridge overnight or for at least four hours.");
        PreparationStep preparationStep16 = new PreparationStep("Divide the mixture into two servings and add your topping of choice.");
        PreparationStep preparationStep17 = new PreparationStep("Preheat the oven to 180 degrees.");
        PreparationStep preparationStep18 = new PreparationStep("Chop the onion and garlic fine, the cherry tomatoes in half and the chorizo into cubes.");
        PreparationStep preparationStep19 = new PreparationStep("Heat a dash of oil in a frying pan and fry the onion and garlic in it, until the onion looks translucent. Add the cherry tomatoes and season with salt and pepper. Fry for 5 minutes, until the tomatoes become soft. Add the passata and half of the fresh basil and let it simmer for about 10 minutes on low heat.");
        PreparationStep preparationStep20 = new PreparationStep("Spoon the sauce into an oven dish and spread the cod over it. Finish with the chorizo pieces and the rest of the fresh basil. ");
        PreparationStep preparationStep21 = new PreparationStep("Bake the casserole in the oven for about 10-12 minutes. Serve with the ciabatta.");
        PreparationStep preparationStep22 = new PreparationStep("Mix the butter and sugar until creamy. This can be done in a small saucepan, in which the butter can be melted over low heat so that it mixes easily.");
        PreparationStep preparationStep23 = new PreparationStep("Add the milk and mix until well incorporated.");
        PreparationStep preparationStep24 = new PreparationStep("Then add the flour and a pinch of salt and mix into a crumbly dough.");
        PreparationStep preparationStep25 = new PreparationStep("Crumble the chocolate chip cookies into chocolate chips and mix well.");
        PreparationStep preparationStep26 = new PreparationStep("Now turn 16 small balls from the dough and place them on a plate. Let the balls rest in the refrigerator for 1 hour. ");
        PreparationStep preparationStep27 = new PreparationStep("Only when the dough balls have rested in the refrigerator long enough, do you melt the chocolate. Put this au bain-marie (in a bowl over a pan of boiling water).");
        PreparationStep preparationStep28 = new PreparationStep("Remove the balls from the refrigerator and use a skewer to pass them through the melted chocolate. When you have done this, place the truffles on a parchment-lined plate.");
        PreparationStep preparationStep29 = new PreparationStep("Return the truffles to the refrigerator until the chocolate has set.");
        PreparationStep preparationStep30 = new PreparationStep("Place a large non-stick frying pan on a medium-high heat.");
        PreparationStep preparationStep31 = new PreparationStep("Rub the miso all over the tuna, then pat on the sesame seeds to cover.");
        PreparationStep preparationStep32 = new PreparationStep("Place in the hot pan with 1 tablespoon of olive oil and sear for 1½ minutes on each side, so it’s beautifully golden on the outside but blushing in the middle.");
        PreparationStep preparationStep33 = new PreparationStep("Remove to a board to rest. Quickly wipe out the pan with a ball of kitchen paper, then return to the heat.");
        PreparationStep preparationStep34 = new PreparationStep("Trim the spring onions and slice at an angle the same length as the sugar snaps, tossing both into the hot pan with a few drips of red wine vinegar and a pinch of sea salt for 2 minutes to lightly catch and char.");
        PreparationStep preparationStep35 = new PreparationStep("Slice the sesame tuna and serve on top of the veg, drizzled with 1 teaspoon of extra virgin olive oil.");
        PreparationStep preparationStep36 = new PreparationStep("Heat a dash of oil in a frying pan and fry the gnocchi for 20 minutes.");
        PreparationStep preparationStep37 = new PreparationStep("Cut the chicken thigh into pieces, grate the zest of half a lemon and squeeze out the juice.");
        PreparationStep preparationStep38 = new PreparationStep("Heat a dash of oil in another frying pan and fry the chicken for 10 minutes. Season with salt and pepper.");
        PreparationStep preparationStep39 = new PreparationStep("Add the peas to the chicken for the last 3 minutes. Then add the lemon juice and cream and bring to the boil. Let this boil for another 3 minutes until the cream has thickened slightly.");
        PreparationStep preparationStep40 = new PreparationStep("Divide the gnocchi and the sauce over two plates and finish with some lemon zest on top!");
        PreparationStep preparationStep41 = new PreparationStep("Preheat the oven to 200 degrees. Cover a baking tray with parchment paper.");
        PreparationStep preparationStep42 = new PreparationStep("Beat the Greek yogurt with the eggs and milk and add a good pinch of salt and pepper.");
        PreparationStep preparationStep43 = new PreparationStep("Mix the flour with the baking powder and add this little by little.");
        PreparationStep preparationStep44 = new PreparationStep("Grate 100 grams of the halloumi. Cut the remaining 50 grams of the halloumi into small pieces (we will use this later for the topping).");
        PreparationStep preparationStep45 = new PreparationStep("Add the halloumi grater to the mixture and stir well.");
        PreparationStep preparationStep46 = new PreparationStep("Using a large spoon or ice cream scoop, place 8 scoops on the baking tray lined with parchment paper. Sprinkle the halloumi pieces and pumpkin seeds on top of the bulbs.");
        PreparationStep preparationStep47 = new PreparationStep("Bake the buns in the oven for 15 minutes. Let it cool down and enjoy.");
        PreparationStep preparationStep48 = new PreparationStep("Pick the mint leaves into a pestle and mortar, reserving a small handful of leaves to one side.");
        PreparationStep preparationStep49 = new PreparationStep("Pound the rest into a paste, then muddle in 1 to 2 tablespoons of extra virgin olive oil to make a mint oil.");
        PreparationStep preparationStep50 = new PreparationStep("Top and tail the pineapple, then slice off the skin. Quarter lengthways, remove the core, then finely slice lengthways. Arrange on a large platter or divide between your plates.");
        PreparationStep preparationStep51 = new PreparationStep("Take the time to halve the blueberries, then sprinkle over the top.");
        PreparationStep preparationStep52 = new PreparationStep("Ripple some mint oil through the yoghurt (saving the rest for another recipe), then spoon over the fruit.");
        PreparationStep preparationStep53 = new PreparationStep("Finely grate over the lime zest from a height and squeeze over the juice.");
        PreparationStep preparationStep54 = new PreparationStep("Finely slice and sprinkle over the reserved mint leaves, then drizzle with a tiny bit of extra virgin olive oil (yes, you heard it – delicious).");
        PreparationStep preparationStep55 = new PreparationStep("Preheat the oven to 200 degrees.");
        PreparationStep preparationStep56 = new PreparationStep("Peel the bananas. Place bananas, spelled flour, eggs, honey and baking powder together in the bowl or a food processor.");
        PreparationStep preparationStep57 = new PreparationStep("Melt the coconut oil and put this in the bowl as well. Mix everything until you have a smooth batter.");
        PreparationStep preparationStep58 = new PreparationStep("Coarsely chop the dark chocolate. Mix three quarters of the chocolate with 175 grams of raspberries through the batter.");
        PreparationStep preparationStep59 = new PreparationStep("Cover a cake tin with baking paper and pour the batter into the tin. Finish the banana bread with the remaining raspberries and chocolate on top.");
        PreparationStep preparationStep60 = new PreparationStep("Bake in the oven for 45 minutes. Let the cake cool and serve.");
        PreparationStep preparationStep61 = new PreparationStep("Fill the popsicle moulds 3/4 of the way full with lemonade.");
        PreparationStep preparationStep62 = new PreparationStep("Roughly chop the mint leaves and add them to the lemonade in the moulds. Add slices of lime just for the look, if you want.");
        PreparationStep preparationStep63 = new PreparationStep("Place the popsicles in the freezer (for an hour in total) and, after about 15 minutes, at which point the lemonade should be partly frozen, insert the wooden sticks.");
        PreparationStep preparationStep64 = new PreparationStep("Prepare the orzo according to the directions on the package.");
        PreparationStep preparationStep65 = new PreparationStep("Heat a frying pan and fry the bacon in a dry frying pan for 5 minutes until crispy.");
        PreparationStep preparationStep66 = new PreparationStep("Remove the seeds from the pepper and cut into cubes.");
        PreparationStep preparationStep67 = new PreparationStep("Fry the bell pepper for the last 2 minutes with the bacon. Stir the bacon, bell pepper, pesto and 75 grams of goat cheese through the orzo and divide between 2 plates.");
        PreparationStep preparationStep68 = new PreparationStep("Finish with the rest of the goat cheese.");
        PreparationStep preparationStep69 = new PreparationStep("Melt 50 grams of butter in a pan.");
        PreparationStep preparationStep70 = new PreparationStep("Place the melted butter, egg and milk in a bowl and mix with a mixer to a smooth batter.");
        PreparationStep preparationStep71 = new PreparationStep("Then add the flour, baking powder, sugar and a good pinch of salt to the batter. Mix this again into a smooth batter.");
        PreparationStep preparationStep72 = new PreparationStep("Melt a knob of butter in a pan and scoop the batter into 3 heaps with a spoon. Bake the pancakes in about 5 minutes. Turn them over if bubbles form in the batter.");
        PreparationStep preparationStep73 = new PreparationStep("Repeat this step until the batter is used up.");
        PreparationStep preparationStep74 = new PreparationStep("Preheat the oven to 180 degrees and line a baking pan with baking paper.");
        PreparationStep preparationStep75 = new PreparationStep("Put 225 grams of flour, 125 grams of sugar, the baking powder and a pinch of salt in a bowl and mix together. Cut open the vanilla pod and scrape out the marrow with a knife.");
        PreparationStep preparationStep76 = new PreparationStep("Then add the egg, the pith from the vanilla pod and 120 grams of butter and knead it into a dough ball.");
        PreparationStep preparationStep77 = new PreparationStep("Divide the dough over the bottom of the baking tin and press it with the convex side of a spoon.");
        PreparationStep preparationStep78 = new PreparationStep("Grate the zest of the lemon and then squeeze out the juice.");
        PreparationStep preparationStep79 = new PreparationStep("Mix the blueberries with 20 grams of sugar, lemon juice and cornflour in a bowl. Divide the blueberries over the dough.");
        PreparationStep preparationStep80 = new PreparationStep("Make the crumble by kneading the rest of the flour, sugar, butter and lemon zest into coarse crumbs.");
        PreparationStep preparationStep81 = new PreparationStep("Divide the crumbs over the blueberries, put the baking pan in the oven and bake for 45 minutes.");
        PreparationStep preparationStep82 = new PreparationStep("Heat a dash of oil in a frying pan and add the curry.");
        PreparationStep preparationStep83 = new PreparationStep("Cut the bell pepper and add it to the pan. In the meantime, cut the tomatoes and let them simmer briefly on the fire.");
        PreparationStep preparationStep84 = new PreparationStep("Add the coconut milk. Make 4 wells in the mixture and break an egg in each well.");
        PreparationStep preparationStep85 = new PreparationStep("Sprinkle with a pinch of salt and pepper, put the lid on the pan and let it cook for about 10 minutes on low heat.");
        PreparationStep preparationStep86 = new PreparationStep("Meanwhile, crumble the feta. Give the shakshuka curry a delicious taste and a cozy appearance with the feta!");
        PreparationStep preparationStep87 = new PreparationStep("Preheat the oven to 170 degrees.");
        PreparationStep preparationStep88 = new PreparationStep("Melt the coconut oil in a saucepan. Put 150 grams of oat flakes in the bowl of a food processor and grind into flour.");
        PreparationStep preparationStep89 = new PreparationStep("Then put the flour together with the rest of the oat flakes, 4 tablespoons of honey and the melted coconut oil in a bowl and knead everything into a dough.");
        PreparationStep preparationStep90 = new PreparationStep("Cover a springform tin (we use a 24 cm tin) with baking paper and divide the dough over it. Push the bottom with the convex side of a spoon and make sure that the dough rises 2 centimeters at the edges.");
        PreparationStep preparationStep91 = new PreparationStep("Place the pie crust in the oven and bake for 20 minutes until golden brown and crispy. Then let it cool down. In the meantime, grate the zest of the lemon and squeeze the juice from half a lemon.");
        PreparationStep preparationStep92 = new PreparationStep("Then mix the quark with 1.5 tablespoons of honey, two thirds of the blueberries, the lemon zest and the juice of half a lemon.");
        PreparationStep preparationStep93 = new PreparationStep("Divide the quark mixture over the cooled bottom and finish with the rest of the blueberries and honey.");
        PreparationStep preparationStep94 = new PreparationStep("Preheat the oven to 180 degrees. Lightly grease a 22 cm tin (20 or 24 is also possible) with some oil.");
        PreparationStep preparationStep95 = new PreparationStep("Let the slices of dough thaw and once that's done, line the tin with it. Meanwhile cut the zucchini into small cubes and the spring onions into rings.");
        PreparationStep preparationStep96 = new PreparationStep("Heat some oil in a frying pan and add the zucchini and onion, fry over medium heat.");
        PreparationStep preparationStep97 = new PreparationStep("Cut the woody bottoms off the green asparagus and cut them in half. Put the top halves of the asparagus aside for a while, cut the bottom halves into three pieces per half.");
        PreparationStep preparationStep98 = new PreparationStep("Add the asparagus pieces to the zucchini and spring onion and fry for another minute or two.");
        PreparationStep preparationStep99 = new PreparationStep("Place the eggs in a bowl with the crème fraîche and a good pinch of salt and pepper and stir well.");
        PreparationStep preparationStep100 = new PreparationStep("Remove the vegetables from the frying pan (if necessary, pat dry with some kitchen paper if there is still a lot of oil on it) and stir through the mixture as well.");
        PreparationStep preparationStep101 = new PreparationStep("Now take the piece of cheese and grate some cheese into the mixture, five tablespoons. Give it a stir.");
        PreparationStep preparationStep102 = new PreparationStep("Prick a few holes in the bottom of your lined pan with a fork and pour the mixture into it. Take the remaining halves of the asparagus and divide over the cake. Finish with some more grated cheese, about five tablespoons.");
        PreparationStep preparationStep103 = new PreparationStep("Place the cake in the oven and bake for 35-40 minutes until golden brown and done.");
        PreparationStep preparationStep104 = new PreparationStep("Peel the parsnip and carrot with a vegetable peeler and cut into pieces. Boil the parsnip and carrot together in 1.5 l water with the stock cubes for about 20 minutes. Both ingredients are done if you can easily pierce them with a fork.");
        PreparationStep preparationStep105 = new PreparationStep("Peel and chop the onion. Peel and finely chop the ginger.");
        PreparationStep preparationStep106 = new PreparationStep("Heat a pan on the stove with a splash of oil. Briefly fry the onion here.");
        PreparationStep preparationStep107 = new PreparationStep("Finely chop the cilantro. Leave 1/3 of the chopped cilantro for garnish.");
        PreparationStep preparationStep108 = new PreparationStep("Add the onion, ginger and cilantro to the pan with parsnip and carrot and mash everything with a hand blender until smooth. Add another pinch of salt and pepper.");
        PreparationStep preparationStep109 = new PreparationStep("If the soup is still a bit too thick, you can add a little more water. Divide the soup between two bowls and garnish with the coriander.");
        PreparationStep preparationStep110 = new PreparationStep("Put all the ingredients in a blender and process until smooth.");
        PreparationStep preparationStep111 = new PreparationStep("enjoy immediately!");
        PreparationStep preparationStep112 = new PreparationStep("Peel and finely chop the onion and garlic. Heat a dash of oil in a frying pan. Fry the onion with the garlic.");
        PreparationStep preparationStep113 = new PreparationStep("Add the cumin and a good pinch of salt and pepper.");
        PreparationStep preparationStep114 = new PreparationStep("Drain the water from the chickpea pot and rinse in a colander. Place the chickpeas in a food processor with the onion mixture, parsley and wheat flour and mix until coarsely.");
        PreparationStep preparationStep115 = new PreparationStep("Then make 12 balls with your hands. Heat a good splash of oil in the pan again and fry the falafel in 10 minutes until golden brown.");
        PreparationStep preparationStep116 = new PreparationStep("Place the minced beef in a large shallow casserole pan with 1 tablespoon of olive oil, then break it up and fry on a high heat, stirring regularly.");
        PreparationStep preparationStep117 = new PreparationStep("Click off and discard any tatty outer leaves from the cauliflower, putting the nice leaves into a food processor.");
        PreparationStep preparationStep118 = new PreparationStep("Halve the cauliflower, breaking up one half into the processor. Cut little bite-sized florets off the other half into the mince pan, chucking all the stalks into the processor as you go.");
        PreparationStep preparationStep119 = new PreparationStep("Stir the rendang powder into the pan and cook it all for 10 minutes, or until crispy, stirring regularly.");
        PreparationStep preparationStep120 = new PreparationStep("Meanwhile, pick half the mint leaves into the processor, add a pinch of sea salt and black pepper, and blitz until fine. Tip into a heatproof bowl, cover, and microwave on high for 4 to 5 minutes.");
        PreparationStep preparationStep121 = new PreparationStep("Reserving the baby leaves, pick the remaining mint leaves into the pan, toss well, then pour in the coconut milk and half a tin’s worth of water.");
        PreparationStep preparationStep122 = new PreparationStep("Bring to the boil, simmer for 5 minutes, then taste, season to perfection, and scatter over the reserved mint leaves.");
        PreparationStep preparationStep123 = new PreparationStep("Give the cauli rice a good mix up, and serve on the side.");
        PreparationStep preparationStep124 = new PreparationStep("Slice the olives and quarter the cherry tomatoes. ");
        PreparationStep preparationStep125 = new PreparationStep("Place half of the basil in a bowl with 3 tablespoons of olive oil and a pinch of salt and pepper and mix well with a hand blender.");
        PreparationStep preparationStep126 = new PreparationStep("Mix the lettuce with the olives, cherry tomatoes, basil oil and the rest of the basil leaves and finish with the burrata on top.");
        PreparationStep preparationStep127 = new PreparationStep("Cook the pasta in a pan of boiling salted water according to the packet instructions, then drain, reserving a mugful of cooking water.");
        PreparationStep preparationStep128 = new PreparationStep("Meanwhile, squeeze the sausagemeat out of the skins, then, with wet hands, quickly shape into 18 even-sized balls.");
        PreparationStep preparationStep129 = new PreparationStep("Roll and coat them in black pepper, then cook in a non- stick frying pan on a medium heat with ½ a tablespoon of olive oil until golden and cooked through, tossing regularly, then turn the heat off.");
        PreparationStep preparationStep130 = new PreparationStep("Finely chop the parsley, stalks and all, beat it with the egg and a splash of pasta cooking water, then finely grate and mix in most of the Parmesan.");
        PreparationStep preparationStep131 = new PreparationStep("Toss the drained pasta into the sausage pan, pour in the egg mixture, and toss for 1 minute off the heat (the egg will gently cook in the residual heat).");
        PreparationStep preparationStep132 = new PreparationStep("Loosen with a good splash of reserved cooking water, season to perfection with sea salt and pepper, and finely grate over the remaining Parmesan.");
        PreparationStep preparationStep133 = new PreparationStep("Cut and remove the stone from the dates and fill the centre of each date with nutbutter.");
        PreparationStep preparationStep134 = new PreparationStep("Melt chocolate au bain-marie and spoon the chocolate over the nut butter centres.");
        PreparationStep preparationStep135 = new PreparationStep("Sprinkle some sea salt on top, place them into the freezer to let the chocolate harden for at least 30 minutes & voilà!");
        PreparationStep preparationStep136 = new PreparationStep("Cook the rice according to the directions on the package and cut the pineapple into cubes.");
        PreparationStep preparationStep137 = new PreparationStep("Heat a dash of oil in a pan and fry the wok vegetables for about 5 minutes. Then add the curry powder for the last 2 minutes.");
        PreparationStep preparationStep138 = new PreparationStep("Add the rice and pineapple and fry on high heat for 3 minutes.");
        PreparationStep preparationStep139 = new PreparationStep("Finally add the soy sauce with a pinch of salt and pepper and heat it for 1 minute.");

        PreparationStep preparationStep140 = new PreparationStep("");
        PreparationStep preparationStep141 = new PreparationStep("");
        PreparationStep preparationStep142 = new PreparationStep("");

        // Add prep steps to a list so we can save it into a recipe
        ArrayList<PreparationStep> preparationStepsRecipe1 = new ArrayList<>(Arrays.asList(preparationStep1, preparationStep2, preparationStep3, preparationStep4, preparationStep5, preparationStep6, preparationStep7));
        ArrayList<PreparationStep> preparationStepsRecipe2 = new ArrayList<>(Arrays.asList(preparationStep8, preparationStep9, preparationStep10,preparationStep11, preparationStep12, preparationStep13));
        ArrayList<PreparationStep> preparationStepsRecipe3 = new ArrayList<>(Arrays.asList(preparationStep14, preparationStep15, preparationStep16));
        ArrayList<PreparationStep> preparationStepsRecipe4 = new ArrayList<>(Arrays.asList(preparationStep17, preparationStep18, preparationStep19, preparationStep20, preparationStep21));
        ArrayList<PreparationStep> preparationStepsRecipe5 = new ArrayList<>(Arrays.asList(preparationStep22, preparationStep23, preparationStep24, preparationStep25, preparationStep26, preparationStep27, preparationStep28, preparationStep29));
        ArrayList<PreparationStep> preparationStepsRecipe6 = new ArrayList<>(Arrays.asList(preparationStep30, preparationStep31, preparationStep32, preparationStep33, preparationStep34, preparationStep35));
        ArrayList<PreparationStep> preparationStepsRecipe7 = new ArrayList<>(Arrays.asList(preparationStep36, preparationStep37, preparationStep38, preparationStep39, preparationStep40));
        ArrayList<PreparationStep> preparationStepsRecipe8 = new ArrayList<>(Arrays.asList(preparationStep41, preparationStep42, preparationStep43, preparationStep44, preparationStep45, preparationStep46, preparationStep47));
        ArrayList<PreparationStep> preparationStepsRecipe9 = new ArrayList<>(Arrays.asList(preparationStep48, preparationStep49, preparationStep50, preparationStep51, preparationStep52, preparationStep53, preparationStep54));
        ArrayList<PreparationStep> preparationStepsRecipe10 = new ArrayList<>(Arrays.asList(preparationStep55, preparationStep56, preparationStep57, preparationStep58, preparationStep59, preparationStep60));
        ArrayList<PreparationStep> preparationStepsRecipe11 = new ArrayList<>(Arrays.asList(preparationStep61, preparationStep62, preparationStep63));
        ArrayList<PreparationStep> preparationStepsRecipe12 = new ArrayList<>(Arrays.asList(preparationStep64, preparationStep65, preparationStep66, preparationStep67, preparationStep68));
        ArrayList<PreparationStep> preparationStepsRecipe13 = new ArrayList<>(Arrays.asList(preparationStep69, preparationStep70, preparationStep71, preparationStep72, preparationStep73));
        ArrayList<PreparationStep> preparationStepsRecipe14 = new ArrayList<>(Arrays.asList(preparationStep74, preparationStep75, preparationStep76, preparationStep77, preparationStep78, preparationStep79, preparationStep80, preparationStep81));
        ArrayList<PreparationStep> preparationStepsRecipe15 = new ArrayList<>(Arrays.asList(preparationStep82, preparationStep83, preparationStep84, preparationStep85, preparationStep86));
        ArrayList<PreparationStep> preparationStepsRecipe16 = new ArrayList<>(Arrays.asList(preparationStep87, preparationStep88, preparationStep89, preparationStep90, preparationStep91, preparationStep92, preparationStep93));
        ArrayList<PreparationStep> preparationStepsRecipe17 = new ArrayList<>(Arrays.asList(preparationStep94, preparationStep95, preparationStep96, preparationStep97, preparationStep98, preparationStep99, preparationStep100, preparationStep101, preparationStep102, preparationStep103));
        ArrayList<PreparationStep> preparationStepsRecipe18 = new ArrayList<>(Arrays.asList(preparationStep104, preparationStep105, preparationStep106, preparationStep107, preparationStep108, preparationStep109));
        ArrayList<PreparationStep> preparationStepsRecipe19 = new ArrayList<>(Arrays.asList(preparationStep110, preparationStep111));
        ArrayList<PreparationStep> preparationStepsRecipe20 = new ArrayList<>(Arrays.asList(preparationStep112, preparationStep113, preparationStep114, preparationStep115));
        ArrayList<PreparationStep> preparationStepsRecipe21 = new ArrayList<>(Arrays.asList(preparationStep116, preparationStep117, preparationStep117, preparationStep118, preparationStep119, preparationStep120, preparationStep121, preparationStep122, preparationStep123));
        ArrayList<PreparationStep> preparationStepsRecipe22 = new ArrayList<>(Arrays.asList(preparationStep124, preparationStep125, preparationStep126));
        ArrayList<PreparationStep> preparationStepsRecipe23 = new ArrayList<>(Arrays.asList(preparationStep127, preparationStep128, preparationStep129, preparationStep130, preparationStep131, preparationStep132));
        ArrayList<PreparationStep> preparationStepsRecipe24 = new ArrayList<>(Arrays.asList(preparationStep133, preparationStep134, preparationStep135));
        ArrayList<PreparationStep> preparationStepsRecipe25 = new ArrayList<>(Arrays.asList(preparationStep136, preparationStep137, preparationStep138, preparationStep139));

        // Initialize the recipes
        Recipe recipe1 = new Recipe("Salted caramel pie", 30, 10, 60, cuisineRepository.getOne(3), categoryRepository.getOne(5), userRepository.getOne(4), caramelpieImage);
        Recipe recipe2 = new Recipe("Squash saled", 10, 4, 50, cuisineRepository.getOne(3), categoryRepository.getOne(2), userRepository.getOne(2), squashSaladImage);
        Recipe recipe3 = new Recipe("Chia pudding", 10, 2, 0, cuisineRepository.getOne(3), categoryRepository.getOne(1), userRepository.getOne(3), chiaPuddingImage);
        Recipe recipe4 = new Recipe("Cod with chorizo", 10, 2, 20, cuisineRepository.getOne(1), categoryRepository.getOne(3), userRepository.getOne(5), codImage);
        Recipe recipe5 = new Recipe("Cookie dough truffels", 20, 16, 100, cuisineRepository.getOne(7), categoryRepository.getOne(4), userRepository.getOne(5), cookieDoughImage);
        Recipe recipe6 = new Recipe("Tuna salad", 0, 2, 10, cuisineRepository.getOne(4), categoryRepository.getOne(2), userRepository.getOne(4), tunaImage);
        Recipe recipe7 = new Recipe("Gnocchi with peas", 0, 2, 20, cuisineRepository.getOne(1), categoryRepository.getOne(3), userRepository.getOne(3), gnocciImage);
        Recipe recipe8 = new Recipe("Greek yoghurt balls", 5, 4, 25, cuisineRepository.getOne(6), categoryRepository.getOne(1), userRepository.getOne(2), yoghurtBallsImage);
        Recipe recipe9 = new Recipe("Pinapple carpaccio", 5, 4, 5, cuisineRepository.getOne(8), categoryRepository.getOne(2), userRepository.getOne(5), pinappleCaqrpaccioImage);
        Recipe recipe10 = new Recipe("Raspberrie bananabread", 5, 4, 5, cuisineRepository.getOne(8), categoryRepository.getOne(4), userRepository.getOne(4), bananabreadImage);
        Recipe recipe11 = new Recipe("Lemon popsicles", 15, 4, 0, cuisineRepository.getOne(1), categoryRepository.getOne(4), userRepository.getOne(3), lemoniceImage);
        Recipe recipe12 = new Recipe("Bacon orzo", 5, 2, 15, cuisineRepository.getOne(1), categoryRepository.getOne(3), userRepository.getOne(2), orzoImage);
        Recipe recipe13 = new Recipe("American pancakes", 5, 6, 20, cuisineRepository.getOne(7), categoryRepository.getOne(5), userRepository.getOne(5), pancakesImage);
        Recipe recipe14 = new Recipe("Crumble bars", 15, 12, 40, cuisineRepository.getOne(3), categoryRepository.getOne(5), userRepository.getOne(4), crumblebarsImage);
        Recipe recipe15 = new Recipe("Curry shakshuka", 5, 2, 10, cuisineRepository.getOne(9), categoryRepository.getOne(3), userRepository.getOne(3), curryImage);
        Recipe recipe16 = new Recipe("Breakfast pie", 5, 8, 35, cuisineRepository.getOne(3), categoryRepository.getOne(1), userRepository.getOne(2), breakfastpieImage);
        Recipe recipe17 = new Recipe("Asperge quiche", 10, 2, 50, cuisineRepository.getOne(3), categoryRepository.getOne(3), userRepository.getOne(5), aspergeQuicheImage);
        Recipe recipe18 = new Recipe("Parsnip soup", 10, 4, 15, cuisineRepository.getOne(3), categoryRepository.getOne(3), userRepository.getOne(4), parsnipSoupImage);
        Recipe recipe19 = new Recipe("Hemp smootie", 5, 2, 0, cuisineRepository.getOne(3), categoryRepository.getOne(1), userRepository.getOne(3), hempSmootieImage);
        Recipe recipe20 = new Recipe("Falafel", 10, 12, 10, cuisineRepository.getOne(4), categoryRepository.getOne(2), userRepository.getOne(2), falafelImage);
        Recipe recipe21 = new Recipe("Beef curry", 5, 4, 20, cuisineRepository.getOne(9), categoryRepository.getOne(3), userRepository.getOne(5), beefCurryImage);
        Recipe recipe22 = new Recipe("Burrata saled", 10, 2, 0, cuisineRepository.getOne(1), categoryRepository.getOne(2), userRepository.getOne(4), burrataSaladImage);
        Recipe recipe23 = new Recipe("Carbonara", 5, 2, 10, cuisineRepository.getOne(1), categoryRepository.getOne(3), userRepository.getOne(3), carbonaraImage);
        Recipe recipe24 = new Recipe("Stuffed dates", 5, 10, 30, cuisineRepository.getOne(4), categoryRepository.getOne(4), userRepository.getOne(2), datesImage);
        Recipe recipe25 = new Recipe("Pineapple rice", 5, 2, 15, cuisineRepository.getOne(8), categoryRepository.getOne(3), userRepository.getOne(5), pineapplericeImage);

        // Add the preperationSteps to the recipe
        recipe1.setPreparationStepList(preparationStepsRecipe1);
        recipe2.setPreparationStepList(preparationStepsRecipe2);
        recipe3.setPreparationStepList(preparationStepsRecipe3);
        recipe4.setPreparationStepList(preparationStepsRecipe4);
        recipe5.setPreparationStepList(preparationStepsRecipe5);
        recipe6.setPreparationStepList(preparationStepsRecipe6);
        recipe7.setPreparationStepList(preparationStepsRecipe7);
        recipe8.setPreparationStepList(preparationStepsRecipe8);
        recipe9.setPreparationStepList(preparationStepsRecipe9);
        recipe10.setPreparationStepList(preparationStepsRecipe10);
        recipe11.setPreparationStepList(preparationStepsRecipe11);
        recipe12.setPreparationStepList(preparationStepsRecipe12);
        recipe13.setPreparationStepList(preparationStepsRecipe13);
        recipe14.setPreparationStepList(preparationStepsRecipe14);
        recipe15.setPreparationStepList(preparationStepsRecipe15);
        recipe16.setPreparationStepList(preparationStepsRecipe16);
        recipe17.setPreparationStepList(preparationStepsRecipe17);
        recipe18.setPreparationStepList(preparationStepsRecipe18);
        recipe19.setPreparationStepList(preparationStepsRecipe19);
        recipe20.setPreparationStepList(preparationStepsRecipe20);
        recipe21.setPreparationStepList(preparationStepsRecipe21);
        recipe22.setPreparationStepList(preparationStepsRecipe22);
        recipe23.setPreparationStepList(preparationStepsRecipe23);
        recipe24.setPreparationStepList(preparationStepsRecipe24);
        recipe25.setPreparationStepList(preparationStepsRecipe25);

        // Save recipes to repository
        if (preparationStepRepository.count() == 0 &&
                recipeRepository.count() == 0) {
            recipeRepository.saveAll(Arrays.asList(recipe1, recipe2, recipe3, recipe4, recipe5, recipe6, recipe7, recipe8,recipe9, recipe10, recipe11, recipe12,
                    recipe13, recipe14, recipe15, recipe16, recipe17, recipe18, recipe19, recipe20, recipe21, recipe22, recipe23, recipe24, recipe25));
        }
    }

    @Override
    public void seedMeasuringUnit() {
        if (measuringUnitRepository.count() == 0) {
            MeasuringUnit measuringUnit1 = new MeasuringUnit("", "");
            MeasuringUnit measuringUnit2 = new MeasuringUnit("gram", "gr");
            MeasuringUnit measuringUnit3 = new MeasuringUnit("milliliter", "ml");
            MeasuringUnit measuringUnit4 = new MeasuringUnit("pieces", "pcs");
            MeasuringUnit measuringUnit5 = new MeasuringUnit("liter", "l");
            MeasuringUnit measuringUnit6 = new MeasuringUnit("teaspoon", "tsp");
            MeasuringUnit measuringUnit7 = new MeasuringUnit("tablespoon", "tbsp");
            MeasuringUnit measuringUnit8 = new MeasuringUnit("kilogram", "kg");
            MeasuringUnit measuringUnit9 = new MeasuringUnit("pinch", "pinch");
            MeasuringUnit measuringUnit10 = new MeasuringUnit("centimeter", "cm");

            ArrayList<MeasuringUnit> measuringUnits = new ArrayList<>(Arrays.asList(measuringUnit1, measuringUnit2, measuringUnit3,
                    measuringUnit4, measuringUnit5, measuringUnit6, measuringUnit7, measuringUnit8, measuringUnit9, measuringUnit10));

            measuringUnitRepository.saveAll(measuringUnits);
        }
    }

    @Override
    public void seedIngredient() {
        if (ingredientRepository.count() == 0) {
            Ingredient condensedMilk = new Ingredient("condensed milk",measuringUnitRepository.getOne(2), true);
            Ingredient casterSugar = new Ingredient("caster sugar",measuringUnitRepository.getOne(2), true);
            Ingredient milkChocolate = new Ingredient("milk chocolate",measuringUnitRepository.getOne(2), true);
            Ingredient butterCookies = new Ingredient("butter cookies",measuringUnitRepository.getOne(2), true);
            Ingredient unsaltedButter = new Ingredient("unsalted butter",measuringUnitRepository.getOne(2), true);
            Ingredient milk = new Ingredient("milk",measuringUnitRepository.getOne(3), true);
            Ingredient seaSalt = new Ingredient("sea salt",measuringUnitRepository.getOne(9), true);
            Ingredient fudge = new Ingredient("fudge",measuringUnitRepository.getOne(2), true);
            Ingredient butternutSquash = new Ingredient("butternut squash", measuringUnitRepository.getOne(8), true);
            Ingredient harissa = new Ingredient("harissa", measuringUnitRepository.getOne(7), true);
            Ingredient avocado = new Ingredient("avocado", measuringUnitRepository.getOne(1), true);
            Ingredient mixedSaled = new Ingredient("mixed salad", measuringUnitRepository.getOne(2), true);
            Ingredient mozzarella  = new Ingredient("mozzarella", measuringUnitRepository.getOne(2), true);
            Ingredient banana  = new Ingredient("banana", measuringUnitRepository.getOne(1), true);
            Ingredient vanillaSuger  = new Ingredient("vanilla suger", measuringUnitRepository.getOne(6), true);
            Ingredient chiaSeeds  = new Ingredient("chia seeds", measuringUnitRepository.getOne(7), true);
            Ingredient onion  = new Ingredient("onion", measuringUnitRepository.getOne(1), true);
            Ingredient garlic  = new Ingredient("garlic", measuringUnitRepository.getOne(1), true);
            Ingredient chorizo  = new Ingredient("chorizo", measuringUnitRepository.getOne(2), true);
            Ingredient passata  = new Ingredient("passata", measuringUnitRepository.getOne(2), true);
            Ingredient cod  = new Ingredient("cod", measuringUnitRepository.getOne(2), true);
            Ingredient ciabatta  = new Ingredient("ciabatta bread", measuringUnitRepository.getOne(1), true);
            Ingredient butter  = new Ingredient("butter", measuringUnitRepository.getOne(2), true);
            Ingredient flower  = new Ingredient("flower", measuringUnitRepository.getOne(2), true);
            Ingredient chocolateCookie  = new Ingredient("chocolate cookie", measuringUnitRepository.getOne(2), true);
            Ingredient sugar  = new Ingredient("sugar", measuringUnitRepository.getOne(2), true);
            Ingredient misoPaste  = new Ingredient("miso paste", measuringUnitRepository.getOne(7), true);
            Ingredient tuna  = new Ingredient("tuna steaks", measuringUnitRepository.getOne(2), true);
            Ingredient sesameSeeds  = new Ingredient("sesame seeds", measuringUnitRepository.getOne(7), true);
            Ingredient springOnion  = new Ingredient("spring onions", measuringUnitRepository.getOne(1), true);
            Ingredient sugersnapPeas = new Ingredient("sugersnap peas", measuringUnitRepository.getOne(2), true);
            Ingredient gnocchi = new Ingredient("gnocchi", measuringUnitRepository.getOne(2), true);
            Ingredient chicken = new Ingredient("chicken", measuringUnitRepository.getOne(2), true);
            Ingredient lemon = new Ingredient("lemon", measuringUnitRepository.getOne(1), true);
            Ingredient peas = new Ingredient("peas", measuringUnitRepository.getOne(2), true);
            Ingredient cream = new Ingredient("cream", measuringUnitRepository.getOne(3), true);
            Ingredient greekYoghurt = new Ingredient("Greek yoghurt", measuringUnitRepository.getOne(2), true);
            Ingredient egg = new Ingredient("egg", measuringUnitRepository.getOne(1), true);
            Ingredient bakingPowder = new Ingredient("baking powder", measuringUnitRepository.getOne(6), true);
            Ingredient halloumi = new Ingredient("halloumi", measuringUnitRepository.getOne(2), true);
            Ingredient pumpkinSeeds = new Ingredient("pumpkin seeds", measuringUnitRepository.getOne(2), true);
            Ingredient freshMint = new Ingredient("fresh mint", measuringUnitRepository.getOne(2), true);
            Ingredient pineapple = new Ingredient("pinapple", measuringUnitRepository.getOne(1), true);
            Ingredient blueberries = new Ingredient("blueberries", measuringUnitRepository.getOne(2), true);
            Ingredient lime = new Ingredient("lime", measuringUnitRepository.getOne(1), true);
            Ingredient coconutYohurt = new Ingredient("coconut yoghurt", measuringUnitRepository.getOne(7), true);
            Ingredient honey = new Ingredient("honey", measuringUnitRepository.getOne(7), true);
            Ingredient coconutoil = new Ingredient("coconut oil", measuringUnitRepository.getOne(7), true);
            Ingredient darkChocolate = new Ingredient("dark chocolate", measuringUnitRepository.getOne(2), true);
            Ingredient raspberries = new Ingredient("raspberries", measuringUnitRepository.getOne(2), true);
            Ingredient lemonade = new Ingredient("lemonade", measuringUnitRepository.getOne(3), true);
            Ingredient orzo = new Ingredient("orzo", measuringUnitRepository.getOne(2), true);
            Ingredient bacon = new Ingredient("bacon", measuringUnitRepository.getOne(2), true);
            Ingredient redPepper = new Ingredient("red pepper", measuringUnitRepository.getOne(1), true);
            Ingredient goatCheese = new Ingredient("goat cheese", measuringUnitRepository.getOne(2), true);
            Ingredient redPesto = new Ingredient("red pesto", measuringUnitRepository.getOne(7), true);
            Ingredient vanillaPod = new Ingredient("vanilla pod", measuringUnitRepository.getOne(1), true);
            Ingredient cornstarch = new Ingredient("cornstarch", measuringUnitRepository.getOne(7), true);
            Ingredient curryPaste = new Ingredient("curry paste", measuringUnitRepository.getOne(7), true);
            Ingredient tomatoes = new Ingredient("tomatoes", measuringUnitRepository.getOne(1), true);
            Ingredient coconutMilk = new Ingredient("coconut milk", measuringUnitRepository.getOne(3), true);
            Ingredient fetaCheese = new Ingredient("feta cheese", measuringUnitRepository.getOne(2), true);
            Ingredient oatFlakes = new Ingredient("oat flakes", measuringUnitRepository.getOne(2), true);
            Ingredient cottageCheese = new Ingredient("cottage cheese", measuringUnitRepository.getOne(2), true);
            Ingredient asperge = new Ingredient("green asperges", measuringUnitRepository.getOne(1), true);
            Ingredient zucchini = new Ingredient("zucchine", measuringUnitRepository.getOne(1), true);
            Ingredient cremeFraiche = new Ingredient("creme fraiche", measuringUnitRepository.getOne(3), true);
            Ingredient granaPadano = new Ingredient("grana padano", measuringUnitRepository.getOne(2), true);
            Ingredient puffPastry = new Ingredient("puff pastry", measuringUnitRepository.getOne(1), true);
            Ingredient pepper = new Ingredient("pepper", measuringUnitRepository.getOne(9), true);
            Ingredient salt = new Ingredient("salt", measuringUnitRepository.getOne(9), true);
            Ingredient parsnip = new Ingredient("parsnip", measuringUnitRepository.getOne(2), true);
            Ingredient carrot = new Ingredient("carrot", measuringUnitRepository.getOne(2), true);
            Ingredient stockCube = new Ingredient("stock cube", measuringUnitRepository.getOne(1), true);
            Ingredient ginger = new Ingredient("ginger", measuringUnitRepository.getOne(10), true);
            Ingredient cilantro = new Ingredient("cilantro", measuringUnitRepository.getOne(2), true);
            Ingredient hempMilk = new Ingredient("hemp milk", measuringUnitRepository.getOne(3), true);
            Ingredient pear = new Ingredient("pear", measuringUnitRepository.getOne(1), true);
            Ingredient juice = new Ingredient("juice", measuringUnitRepository.getOne(3), true);
            Ingredient cinnamon = new Ingredient("cinnamon", measuringUnitRepository.getOne(6), true);
            Ingredient maca = new Ingredient("maca", measuringUnitRepository.getOne(7), true);
            Ingredient cumin = new Ingredient("cumin", measuringUnitRepository.getOne(7), true);
            Ingredient chickpeas = new Ingredient("chickpeas", measuringUnitRepository.getOne(2), true);
            Ingredient parsley = new Ingredient("parsley", measuringUnitRepository.getOne(2), true);
            Ingredient mincedBeef = new Ingredient("minced beef", measuringUnitRepository.getOne(2), true);
            Ingredient cauliflower = new Ingredient("cauliflower", measuringUnitRepository.getOne(2), true);
            Ingredient rendangPowder = new Ingredient("rendang powder", measuringUnitRepository.getOne(7), true);
            Ingredient blackOlives = new Ingredient("black olives", measuringUnitRepository.getOne(2), true);
            Ingredient cherryTomatoes = new Ingredient("cherry tomatoes", measuringUnitRepository.getOne(2), true);
            Ingredient basil = new Ingredient("basil", measuringUnitRepository.getOne(2), true);
            Ingredient saladLeaves = new Ingredient("salad leaves", measuringUnitRepository.getOne(2), true);
            Ingredient burrata = new Ingredient("burrata", measuringUnitRepository.getOne(1), true);
            Ingredient tagliatelle = new Ingredient("tagliatelle", measuringUnitRepository.getOne(2), true);
            Ingredient sausages = new Ingredient("sausages", measuringUnitRepository.getOne(1), true);
            Ingredient parmesan = new Ingredient("parmesan", measuringUnitRepository.getOne(2), true);
            Ingredient dates = new Ingredient("medjoul dates", measuringUnitRepository.getOne(1), true);
            Ingredient nutbutter = new Ingredient("nutbutter", measuringUnitRepository.getOne(7), true);
            Ingredient chocolate = new Ingredient("chocolate", measuringUnitRepository.getOne(2), true);
            Ingredient rice = new Ingredient("rice", measuringUnitRepository.getOne(2), true);
            Ingredient wokVegetables = new Ingredient("wok vegetables", measuringUnitRepository.getOne(2), true);
            Ingredient curryPowder = new Ingredient("curry powder", measuringUnitRepository.getOne(6), true);
            Ingredient soySauce = new Ingredient("soy sauce", measuringUnitRepository.getOne(7), true);



            ArrayList<Ingredient> ingredients = new ArrayList<>(Arrays.asList(condensedMilk, casterSugar, milkChocolate, butterCookies, unsaltedButter, milk, seaSalt, fudge,
                    butternutSquash, harissa, avocado, mixedSaled, mozzarella, banana, vanillaSuger, chiaSeeds, onion, garlic, chorizo, passata, cod, ciabatta, butter, flower,
                    chocolateCookie, sugar, misoPaste, tuna, sesameSeeds, springOnion, sugersnapPeas, gnocchi, chicken, lemon, peas, cream, greekYoghurt, egg, bakingPowder, halloumi,
                    pumpkinSeeds, freshMint, pineapple, blueberries, lime, coconutYohurt, honey, coconutoil, darkChocolate, raspberries, lemonade, orzo, bacon ,redPepper, goatCheese,
                    redPesto, vanillaPod, blueberries, cornstarch, curryPaste, tomatoes, coconutMilk, fetaCheese, oatFlakes, cottageCheese, asperge, zucchini, cremeFraiche,
                    granaPadano, puffPastry, salt, pepper, parsnip, carrot, stockCube, ginger, cilantro, hempMilk, pear, juice, cinnamon, maca, cumin, chickpeas, parsley, mincedBeef,
                    cauliflower, rendangPowder, blackOlives, cherryTomatoes, basil, saladLeaves, burrata, tagliatelle, sausages, parmesan, dates, nutbutter, chocolate, rice,
                    wokVegetables, curryPowder, soySauce));

            ingredientRepository.saveAll(ingredients);
        }
    }

    @Override
    public void seedRecipeIngredient() {
        if (recipeIngredientRepository.count() == 0) {
            RecipeIngredient recipeIngredient1 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(1), 400);
            RecipeIngredient recipeIngredient2 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(2), 75);
            RecipeIngredient recipeIngredient3 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(3), 250);
            RecipeIngredient recipeIngredient4 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(4), 350);
            RecipeIngredient recipeIngredient5 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(5), 175);
            RecipeIngredient recipeIngredient6 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(6), 100);
            RecipeIngredient recipeIngredient7 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(7), 4);
            RecipeIngredient recipeIngredient8 = new RecipeIngredient(recipeRepository.getOne(1), ingredientRepository.getOne(8), 130);
            RecipeIngredient recipeIngredient9 = new RecipeIngredient(recipeRepository.getOne(2), ingredientRepository.getOne(9), 1);
            RecipeIngredient recipeIngredient10 = new RecipeIngredient(recipeRepository.getOne(2), ingredientRepository.getOne(10), 1);
            RecipeIngredient recipeIngredient11 = new RecipeIngredient(recipeRepository.getOne(2), ingredientRepository.getOne(11), 2);
            RecipeIngredient recipeIngredient12 = new RecipeIngredient(recipeRepository.getOne(2), ingredientRepository.getOne(12), 100);
            RecipeIngredient recipeIngredient13 = new RecipeIngredient(recipeRepository.getOne(2), ingredientRepository.getOne(13), 125);
            RecipeIngredient recipeIngredient14 = new RecipeIngredient(recipeRepository.getOne(3), ingredientRepository.getOne(14), 2);
            RecipeIngredient recipeIngredient15 = new RecipeIngredient(recipeRepository.getOne(3), ingredientRepository.getOne(6), 500);
            RecipeIngredient recipeIngredient16 = new RecipeIngredient(recipeRepository.getOne(3), ingredientRepository.getOne(15), 1);
            RecipeIngredient recipeIngredient17 = new RecipeIngredient(recipeRepository.getOne(3), ingredientRepository.getOne(16), 6);
            RecipeIngredient recipeIngredient18 = new RecipeIngredient(recipeRepository.getOne(4), ingredientRepository.getOne(17), 1);
            RecipeIngredient recipeIngredient19 = new RecipeIngredient(recipeRepository.getOne(4), ingredientRepository.getOne(18), 2);
            RecipeIngredient recipeIngredient20 = new RecipeIngredient(recipeRepository.getOne(4), ingredientRepository.getOne(19), 250);
            RecipeIngredient recipeIngredient21 = new RecipeIngredient(recipeRepository.getOne(4), ingredientRepository.getOne(20), 275);
            RecipeIngredient recipeIngredient22 = new RecipeIngredient(recipeRepository.getOne(4), ingredientRepository.getOne(21), 260);
            RecipeIngredient recipeIngredient23 = new RecipeIngredient(recipeRepository.getOne(4), ingredientRepository.getOne(22), 1);
            RecipeIngredient recipeIngredient24 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(23), 100);
            RecipeIngredient recipeIngredient25 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(24), 225);
            RecipeIngredient recipeIngredient26 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(6), 30);
            RecipeIngredient recipeIngredient27 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(25), 100);
            RecipeIngredient recipeIngredient28 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(26), 60);
            RecipeIngredient recipeIngredient29 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(2), 115);
            RecipeIngredient recipeIngredient30 = new RecipeIngredient(recipeRepository.getOne(5), ingredientRepository.getOne(3), 200);
            RecipeIngredient recipeIngredient31 = new RecipeIngredient(recipeRepository.getOne(6), ingredientRepository.getOne(27), 1);
            RecipeIngredient recipeIngredient32 = new RecipeIngredient(recipeRepository.getOne(6), ingredientRepository.getOne(28), 150);
            RecipeIngredient recipeIngredient33 = new RecipeIngredient(recipeRepository.getOne(6), ingredientRepository.getOne(29), 4);
            RecipeIngredient recipeIngredient34 = new RecipeIngredient(recipeRepository.getOne(6), ingredientRepository.getOne(30), 8);
            RecipeIngredient recipeIngredient35 = new RecipeIngredient(recipeRepository.getOne(6), ingredientRepository.getOne(31), 150);
            RecipeIngredient recipeIngredient36 = new RecipeIngredient(recipeRepository.getOne(7), ingredientRepository.getOne(32), 300);
            RecipeIngredient recipeIngredient37 = new RecipeIngredient(recipeRepository.getOne(7), ingredientRepository.getOne(33), 250);
            RecipeIngredient recipeIngredient38 = new RecipeIngredient(recipeRepository.getOne(7), ingredientRepository.getOne(34), 1);
            RecipeIngredient recipeIngredient39 = new RecipeIngredient(recipeRepository.getOne(7), ingredientRepository.getOne(35), 200);
            RecipeIngredient recipeIngredient40 = new RecipeIngredient(recipeRepository.getOne(7), ingredientRepository.getOne(36), 125);
            RecipeIngredient recipeIngredient41 = new RecipeIngredient(recipeRepository.getOne(8), ingredientRepository.getOne(37), 250);
            RecipeIngredient recipeIngredient42 = new RecipeIngredient(recipeRepository.getOne(8), ingredientRepository.getOne(38), 2);
            RecipeIngredient recipeIngredient43 = new RecipeIngredient(recipeRepository.getOne(8), ingredientRepository.getOne(6), 30);
            RecipeIngredient recipeIngredient44 = new RecipeIngredient(recipeRepository.getOne(8), ingredientRepository.getOne(24), 250);
            RecipeIngredient recipeIngredient45 = new RecipeIngredient(recipeRepository.getOne(8), ingredientRepository.getOne(39), 1);
            RecipeIngredient recipeIngredient46 = new RecipeIngredient(recipeRepository.getOne(8), ingredientRepository.getOne(40), 150);
            RecipeIngredient recipeIngredient47 = new RecipeIngredient(recipeRepository.getOne(8), ingredientRepository.getOne(41), 15);
            RecipeIngredient recipeIngredient48 = new RecipeIngredient(recipeRepository.getOne(9), ingredientRepository.getOne(42), 30);
            RecipeIngredient recipeIngredient49 = new RecipeIngredient(recipeRepository.getOne(9), ingredientRepository.getOne(43), 1);
            RecipeIngredient recipeIngredient50 = new RecipeIngredient(recipeRepository.getOne(9), ingredientRepository.getOne(44), 100);
            RecipeIngredient recipeIngredient51 = new RecipeIngredient(recipeRepository.getOne(9), ingredientRepository.getOne(45), 1);
            RecipeIngredient recipeIngredient52 = new RecipeIngredient(recipeRepository.getOne(9), ingredientRepository.getOne(46), 4);
            RecipeIngredient recipeIngredient53 = new RecipeIngredient(recipeRepository.getOne(10), ingredientRepository.getOne(14), 4);
            RecipeIngredient recipeIngredient54 = new RecipeIngredient(recipeRepository.getOne(10), ingredientRepository.getOne(24), 200);
            RecipeIngredient recipeIngredient55 = new RecipeIngredient(recipeRepository.getOne(10), ingredientRepository.getOne(47), 4);
            RecipeIngredient recipeIngredient56 = new RecipeIngredient(recipeRepository.getOne(10), ingredientRepository.getOne(38), 2);
            RecipeIngredient recipeIngredient57 = new RecipeIngredient(recipeRepository.getOne(10), ingredientRepository.getOne(39), 2);
            RecipeIngredient recipeIngredient58 = new RecipeIngredient(recipeRepository.getOne(10), ingredientRepository.getOne(48), 2);
            RecipeIngredient recipeIngredient59 = new RecipeIngredient(recipeRepository.getOne(10), ingredientRepository.getOne(49), 50);
            RecipeIngredient recipeIngredient60 = new RecipeIngredient(recipeRepository.getOne(10), ingredientRepository.getOne(50), 225);
            RecipeIngredient recipeIngredient61 = new RecipeIngredient(recipeRepository.getOne(11), ingredientRepository.getOne(51), 200);
            RecipeIngredient recipeIngredient62 = new RecipeIngredient(recipeRepository.getOne(11), ingredientRepository.getOne(45), 1);
            RecipeIngredient recipeIngredient63 = new RecipeIngredient(recipeRepository.getOne(11), ingredientRepository.getOne(42), 15);
            RecipeIngredient recipeIngredient64 = new RecipeIngredient(recipeRepository.getOne(12), ingredientRepository.getOne(52), 150);
            RecipeIngredient recipeIngredient65 = new RecipeIngredient(recipeRepository.getOne(12), ingredientRepository.getOne(53), 100);
            RecipeIngredient recipeIngredient66 = new RecipeIngredient(recipeRepository.getOne(12), ingredientRepository.getOne(54), 2);
            RecipeIngredient recipeIngredient67 = new RecipeIngredient(recipeRepository.getOne(12), ingredientRepository.getOne(55), 100);
            RecipeIngredient recipeIngredient68 = new RecipeIngredient(recipeRepository.getOne(12), ingredientRepository.getOne(56), 4);
            RecipeIngredient recipeIngredient69 = new RecipeIngredient(recipeRepository.getOne(13), ingredientRepository.getOne(5), 75);
            RecipeIngredient recipeIngredient70 = new RecipeIngredient(recipeRepository.getOne(13), ingredientRepository.getOne(38), 1);
            RecipeIngredient recipeIngredient71 = new RecipeIngredient(recipeRepository.getOne(13), ingredientRepository.getOne(6), 175);
            RecipeIngredient recipeIngredient72 = new RecipeIngredient(recipeRepository.getOne(13), ingredientRepository.getOne(24), 200);
            RecipeIngredient recipeIngredient73 = new RecipeIngredient(recipeRepository.getOne(13), ingredientRepository.getOne(39), 2);
            RecipeIngredient recipeIngredient74 = new RecipeIngredient(recipeRepository.getOne(13), ingredientRepository.getOne(26), 30);
            RecipeIngredient recipeIngredient75 = new RecipeIngredient(recipeRepository.getOne(14), ingredientRepository.getOne(24), 325);
            RecipeIngredient recipeIngredient76 = new RecipeIngredient(recipeRepository.getOne(14), ingredientRepository.getOne(26), 190);
            RecipeIngredient recipeIngredient77 = new RecipeIngredient(recipeRepository.getOne(14), ingredientRepository.getOne(39), 1);
            RecipeIngredient recipeIngredient78 = new RecipeIngredient(recipeRepository.getOne(14), ingredientRepository.getOne(57), 1);
            RecipeIngredient recipeIngredient79 = new RecipeIngredient(recipeRepository.getOne(14), ingredientRepository.getOne(38), 1);
            RecipeIngredient recipeIngredient80 = new RecipeIngredient(recipeRepository.getOne(14), ingredientRepository.getOne(5), 180);
            RecipeIngredient recipeIngredient81 = new RecipeIngredient(recipeRepository.getOne(14), ingredientRepository.getOne(34), 1);
            RecipeIngredient recipeIngredient82 = new RecipeIngredient(recipeRepository.getOne(14), ingredientRepository.getOne(44), 300);
            RecipeIngredient recipeIngredient83 = new RecipeIngredient(recipeRepository.getOne(14), ingredientRepository.getOne(58), 1);
            RecipeIngredient recipeIngredient84 = new RecipeIngredient(recipeRepository.getOne(15), ingredientRepository.getOne(59), 1);
            RecipeIngredient recipeIngredient85 = new RecipeIngredient(recipeRepository.getOne(15), ingredientRepository.getOne(54), 1);
            RecipeIngredient recipeIngredient86 = new RecipeIngredient(recipeRepository.getOne(15), ingredientRepository.getOne(55), 7);
            RecipeIngredient recipeIngredient87 = new RecipeIngredient(recipeRepository.getOne(15), ingredientRepository.getOne(56), 50);
            RecipeIngredient recipeIngredient88 = new RecipeIngredient(recipeRepository.getOne(15), ingredientRepository.getOne(38), 4);
            RecipeIngredient recipeIngredient89 = new RecipeIngredient(recipeRepository.getOne(15), ingredientRepository.getOne(57), 50);
            RecipeIngredient recipeIngredient90 = new RecipeIngredient(recipeRepository.getOne(16), ingredientRepository.getOne(48), 5);
            RecipeIngredient recipeIngredient91 = new RecipeIngredient(recipeRepository.getOne(16), ingredientRepository.getOne(63), 300);
            RecipeIngredient recipeIngredient92 = new RecipeIngredient(recipeRepository.getOne(16), ingredientRepository.getOne(47), 7);
            RecipeIngredient recipeIngredient93 = new RecipeIngredient(recipeRepository.getOne(16), ingredientRepository.getOne(34), 1);
            RecipeIngredient recipeIngredient94 = new RecipeIngredient(recipeRepository.getOne(16), ingredientRepository.getOne(64), 250);
            RecipeIngredient recipeIngredient95 = new RecipeIngredient(recipeRepository.getOne(16), ingredientRepository.getOne(44), 300);
            RecipeIngredient recipeIngredient96 = new RecipeIngredient(recipeRepository.getOne(17), ingredientRepository.getOne(65), 10);
            RecipeIngredient recipeIngredient97 = new RecipeIngredient(recipeRepository.getOne(17), ingredientRepository.getOne(66), 1);
            RecipeIngredient recipeIngredient98 = new RecipeIngredient(recipeRepository.getOne(17), ingredientRepository.getOne(30), 2);
            RecipeIngredient recipeIngredient99 = new RecipeIngredient(recipeRepository.getOne(17), ingredientRepository.getOne(38), 3);
            RecipeIngredient recipeIngredient100 = new RecipeIngredient(recipeRepository.getOne(17), ingredientRepository.getOne(67), 200);
            RecipeIngredient recipeIngredient101 = new RecipeIngredient(recipeRepository.getOne(17), ingredientRepository.getOne(68), 50);
            RecipeIngredient recipeIngredient102 = new RecipeIngredient(recipeRepository.getOne(17), ingredientRepository.getOne(69), 5);
            RecipeIngredient recipeIngredient103 = new RecipeIngredient(recipeRepository.getOne(17), ingredientRepository.getOne(70), 1);
            RecipeIngredient recipeIngredient104 = new RecipeIngredient(recipeRepository.getOne(17), ingredientRepository.getOne(71), 1);
            RecipeIngredient recipeIngredient105 = new RecipeIngredient(recipeRepository.getOne(18), ingredientRepository.getOne(72), 500);
            RecipeIngredient recipeIngredient106 = new RecipeIngredient(recipeRepository.getOne(18), ingredientRepository.getOne(73), 450);
            RecipeIngredient recipeIngredient107 = new RecipeIngredient(recipeRepository.getOne(18), ingredientRepository.getOne(74), 2);
            RecipeIngredient recipeIngredient108 = new RecipeIngredient(recipeRepository.getOne(18), ingredientRepository.getOne(17), 1);
            RecipeIngredient recipeIngredient109 = new RecipeIngredient(recipeRepository.getOne(18), ingredientRepository.getOne(75), 1);
            RecipeIngredient recipeIngredient110 = new RecipeIngredient(recipeRepository.getOne(18), ingredientRepository.getOne(76), 10);
            RecipeIngredient recipeIngredient111 = new RecipeIngredient(recipeRepository.getOne(19), ingredientRepository.getOne(77), 300);
            RecipeIngredient recipeIngredient112 = new RecipeIngredient(recipeRepository.getOne(19), ingredientRepository.getOne(14), 1);
            RecipeIngredient recipeIngredient113 = new RecipeIngredient(recipeRepository.getOne(19), ingredientRepository.getOne(78), 1);
            RecipeIngredient recipeIngredient114 = new RecipeIngredient(recipeRepository.getOne(19), ingredientRepository.getOne(44), 80);
            RecipeIngredient recipeIngredient115 = new RecipeIngredient(recipeRepository.getOne(19), ingredientRepository.getOne(79), 30);
            RecipeIngredient recipeIngredient116 = new RecipeIngredient(recipeRepository.getOne(19), ingredientRepository.getOne(80), 1);
            RecipeIngredient recipeIngredient117 = new RecipeIngredient(recipeRepository.getOne(19), ingredientRepository.getOne(81), 1);
            RecipeIngredient recipeIngredient118 = new RecipeIngredient(recipeRepository.getOne(20), ingredientRepository.getOne(17), 1);
            RecipeIngredient recipeIngredient119 = new RecipeIngredient(recipeRepository.getOne(20), ingredientRepository.getOne(18), 1);
            RecipeIngredient recipeIngredient120 = new RecipeIngredient(recipeRepository.getOne(20), ingredientRepository.getOne(82), 1);
            RecipeIngredient recipeIngredient121 = new RecipeIngredient(recipeRepository.getOne(20), ingredientRepository.getOne(83), 400);
            RecipeIngredient recipeIngredient122 = new RecipeIngredient(recipeRepository.getOne(20), ingredientRepository.getOne(84), 15);
            RecipeIngredient recipeIngredient123 = new RecipeIngredient(recipeRepository.getOne(20), ingredientRepository.getOne(24), 5);
            RecipeIngredient recipeIngredient124 = new RecipeIngredient(recipeRepository.getOne(21), ingredientRepository.getOne(85), 500);
            RecipeIngredient recipeIngredient125 = new RecipeIngredient(recipeRepository.getOne(21), ingredientRepository.getOne(86), 800);
            RecipeIngredient recipeIngredient126 = new RecipeIngredient(recipeRepository.getOne(21), ingredientRepository.getOne(87), 1);
            RecipeIngredient recipeIngredient127 = new RecipeIngredient(recipeRepository.getOne(21), ingredientRepository.getOne(42), 30);
            RecipeIngredient recipeIngredient128 = new RecipeIngredient(recipeRepository.getOne(21), ingredientRepository.getOne(61), 150);
            RecipeIngredient recipeIngredient129 = new RecipeIngredient(recipeRepository.getOne(22), ingredientRepository.getOne(88), 100);
            RecipeIngredient recipeIngredient130 = new RecipeIngredient(recipeRepository.getOne(22), ingredientRepository.getOne(89), 250);
            RecipeIngredient recipeIngredient131 = new RecipeIngredient(recipeRepository.getOne(22), ingredientRepository.getOne(90), 15);
            RecipeIngredient recipeIngredient132 = new RecipeIngredient(recipeRepository.getOne(22), ingredientRepository.getOne(91), 150);
            RecipeIngredient recipeIngredient133 = new RecipeIngredient(recipeRepository.getOne(22), ingredientRepository.getOne(92), 1);
            RecipeIngredient recipeIngredient134 = new RecipeIngredient(recipeRepository.getOne(23), ingredientRepository.getOne(93), 150);
            RecipeIngredient recipeIngredient135 = new RecipeIngredient(recipeRepository.getOne(23), ingredientRepository.getOne(94), 3);
            RecipeIngredient recipeIngredient136 = new RecipeIngredient(recipeRepository.getOne(23), ingredientRepository.getOne(84), 15);
            RecipeIngredient recipeIngredient137 = new RecipeIngredient(recipeRepository.getOne(23), ingredientRepository.getOne(38), 1);
            RecipeIngredient recipeIngredient138 = new RecipeIngredient(recipeRepository.getOne(23), ingredientRepository.getOne(95), 30);
            RecipeIngredient recipeIngredient139 = new RecipeIngredient(recipeRepository.getOne(24), ingredientRepository.getOne(96), 10);
            RecipeIngredient recipeIngredient140 = new RecipeIngredient(recipeRepository.getOne(24), ingredientRepository.getOne(97), 15);
            RecipeIngredient recipeIngredient141 = new RecipeIngredient(recipeRepository.getOne(24), ingredientRepository.getOne(98), 15);
            RecipeIngredient recipeIngredient142 = new RecipeIngredient(recipeRepository.getOne(24), ingredientRepository.getOne(7), 1);

            RecipeIngredient recipeIngredient143 = new RecipeIngredient(recipeRepository.getOne(25), ingredientRepository.getOne(99), 150);
            RecipeIngredient recipeIngredient144 = new RecipeIngredient(recipeRepository.getOne(25), ingredientRepository.getOne(43), 250);
            RecipeIngredient recipeIngredient145 = new RecipeIngredient(recipeRepository.getOne(25), ingredientRepository.getOne(100), 300);
            RecipeIngredient recipeIngredient146 = new RecipeIngredient(recipeRepository.getOne(25), ingredientRepository.getOne(101), 2);
            RecipeIngredient recipeIngredient147 = new RecipeIngredient(recipeRepository.getOne(25), ingredientRepository.getOne(102), 4);

            ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>(Arrays.asList(recipeIngredient1, recipeIngredient2, recipeIngredient3, recipeIngredient4, recipeIngredient5,
                    recipeIngredient6, recipeIngredient7, recipeIngredient8, recipeIngredient9, recipeIngredient10, recipeIngredient11, recipeIngredient12, recipeIngredient13, recipeIngredient14,
                    recipeIngredient15, recipeIngredient16, recipeIngredient17, recipeIngredient18, recipeIngredient19, recipeIngredient20, recipeIngredient21, recipeIngredient22,
                    recipeIngredient23, recipeIngredient24, recipeIngredient25, recipeIngredient26, recipeIngredient27, recipeIngredient28, recipeIngredient29, recipeIngredient30,
                    recipeIngredient31, recipeIngredient32, recipeIngredient33, recipeIngredient34, recipeIngredient35, recipeIngredient36, recipeIngredient37, recipeIngredient38,
                    recipeIngredient39, recipeIngredient40, recipeIngredient41, recipeIngredient42, recipeIngredient43, recipeIngredient44, recipeIngredient45, recipeIngredient46,
                    recipeIngredient47, recipeIngredient48, recipeIngredient49, recipeIngredient50, recipeIngredient51, recipeIngredient52, recipeIngredient53, recipeIngredient54,
                    recipeIngredient55, recipeIngredient56, recipeIngredient57, recipeIngredient58, recipeIngredient59, recipeIngredient60, recipeIngredient61, recipeIngredient62,
                    recipeIngredient63, recipeIngredient64, recipeIngredient65, recipeIngredient66, recipeIngredient67, recipeIngredient68 ,recipeIngredient69, recipeIngredient70,
                    recipeIngredient71, recipeIngredient72, recipeIngredient73, recipeIngredient74, recipeIngredient75, recipeIngredient76, recipeIngredient77, recipeIngredient78,
                    recipeIngredient79, recipeIngredient80, recipeIngredient81, recipeIngredient82, recipeIngredient83, recipeIngredient84, recipeIngredient85, recipeIngredient86,
                    recipeIngredient87, recipeIngredient88, recipeIngredient89, recipeIngredient90, recipeIngredient91, recipeIngredient92, recipeIngredient93, recipeIngredient94,
                    recipeIngredient95, recipeIngredient96, recipeIngredient97, recipeIngredient98, recipeIngredient99, recipeIngredient100, recipeIngredient101, recipeIngredient102,
                    recipeIngredient103, recipeIngredient104, recipeIngredient105, recipeIngredient106, recipeIngredient107, recipeIngredient108, recipeIngredient109, recipeIngredient110,
                    recipeIngredient111, recipeIngredient112, recipeIngredient113, recipeIngredient114, recipeIngredient115, recipeIngredient116, recipeIngredient117, recipeIngredient118,
                    recipeIngredient119, recipeIngredient120, recipeIngredient121, recipeIngredient122, recipeIngredient123 ,recipeIngredient124, recipeIngredient125, recipeIngredient126,
                    recipeIngredient127, recipeIngredient128, recipeIngredient129, recipeIngredient130, recipeIngredient130, recipeIngredient131, recipeIngredient132, recipeIngredient133,
                    recipeIngredient134, recipeIngredient135, recipeIngredient136, recipeIngredient137, recipeIngredient138, recipeIngredient139, recipeIngredient140, recipeIngredient141,
                    recipeIngredient142, recipeIngredient143, recipeIngredient144, recipeIngredient145, recipeIngredient146, recipeIngredient147));

            recipeIngredientRepository.saveAll(recipeIngredients);
        }
    }

    public void seedCookbook() {
        Cookbook cookbook1 = new Cookbook(1, false, "recipes", userRepository.getOne(1),
                Arrays.asList(recipeRepository.getOne(1), recipeRepository.getOne(2)));

        cookbookRepository.save(cookbook1);
    }

    public byte[] imageFromFileToByteArray(String imageFilePath) throws IOException {
        FileInputStream imageInFile = new FileInputStream(new File(imageFilePath));

        return imageInFile.readAllBytes();
    }

}