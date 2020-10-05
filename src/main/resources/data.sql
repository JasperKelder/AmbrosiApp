INSERT INTO recipes.cuisine VALUES
(1,'Italian'),
(2,'Asian'),
(3,'Dutch'),
(4,'German'),
(5,'Spanish'),
(6,'Oriental'),
(7,'Greek');

INSERT INTO recipes.category VALUES
(1,'Breakfast'),
(2,'Lunch'),
(3,'Dinner'),
(4,'Snacks'),
(5,'Cheating');

/* user_id, email, first_name, last_name, password (test)  */
/* user 3 has username a and password a */

INSERT INTO recipes.user VALUES
(1, '42@gmail.com', 'Make IT Work', 'Elmo', '$2a$10$VRCtIl4CVgV5n9CspNQhkOMpz8KrfND5fiGUwlXTKsWSO99zRboqm'),
(2, 'admin', 'Admin', 'Admin', '$2a$10$Zp4Y6iXdx26oxeq8AxH.cOFEtuGZ2IVS9X6GVIoBwDfPxBuY/XKg6'),
(3, 'a', 'Elmo', 'Elmo', '$2a$10$aPj.8aZIFIlfHThl5mxTKug4egApzZfgVzmNLRpQTTsG6fi8A579O'),
/* password for our personal accounts is your first name */
(4, 'jasmijn@jasmijn.nl', 'Jasmijn', 'van der Veen', '$2a$10$V3OOltwWvODq7.JWjS0Y4OOF0y5Vwp7r/ri5FJaXbDc6CE73On1WO'),
(5, 'jasper@jasper.nl', 'Jasper', 'Kelder', '$2a$10$caT/JIhuqmpvxO2g7zPXcesD5fo5BcVVvbe5VZGxLwHen/u57ihHm'),
(6, 'reinout@reinout.nl', 'Reinout', 'Smit', '$2a$10$BXHtcGy20x1f3/pbo8W3COmyBo4zSUczxEqgkTEsoCzNbKbmX86pm'),
(7, 'nathalie@nathalie.nl', 'Nathalie', 'Antoine', '$2a$10$ANB4kIxr6WK1Zv39TjYPr.s86Hx5BDKE8VORSowA8VMzZBqcQRQAa');

INSERT INTO recipes.role VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO recipes.user_roles VALUES
(1, 1),
(2, 2),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1);

/* The table next_val has to be updated (nr of users + 1) in order to make it possible to register new users */
# UPDATE hibernate_sequence SET next_val = 4 WHERE next_val = 1;

/* recipe_id, cooktime, image ingredients, prep_time, recipe_prep, recipe_title, servings, category_id, cuisine_id, user_id */
/* Has to be after insertion of the values it references (category, cuisine, user)*/
INSERT INTO recipes.recipe VALUES
(1, 20, null, 30, 'Just do it', 'Strawberry Cake', 8, 1, 3, 1),
(2, 40, null, 50, 'Asian style', 'Broccoli Dish', 4, 2, 3, 1),
(3, 20, null, 30, 'From your local snackbar', 'Hollands glorie', 8, 1, 3, 1),
(4, 20, null, 30, 'Just do it', 'Eierbal', 8, 1, 3, 1),
(5, 15, null, 5, 'Prepare the orzo according to the directions on the package. Heat a frying pan and fry the bacon in a dry frying pan for 5 minutes until crispy. Remove the seeds from the pepper and cut into cubes. Fry the bell pepper for the last 2 minutes with the bacon. Stir the bacon, bell pepper, pesto and 75 grams of goat cheese through the orzo and divide between 2 plates. Finish with the rest of the goat cheese.',
    'Orzo with bacon and goat cheese', 2, 3, 1, 7),
(6, 10, null, 0, 'Slice the olives and quarter the cherry tomatoes. Place half of the basil in a bowl with 3 tablespoons of olive oil and a pinch of pepper and salt and mix well with a hand blender. Mix the lamb''s lettuce with the olives, cherry tomatoes, basil oil and the rest of the basil leaves and finish with the burrata on top.',
    'Salad with burrata and olives', 2, 2, 1, 6),
(7, 15, null, 5, 'Heat a dash of oil in a frying pan and fry the gnocchi for 20 minutes. Cut the chicken thigh into pieces, grate the zest of half a lemon and squeeze out the juice. Heat a dash of oil in another frying pan and fry the chicken for 10 minutes. Season with salt and pepper. Add the peas to the chicken for the last 3 minutes. Then add the lemon juice and cream and bring to the boil. Let this boil for another 3 minutes until the cream has thickened slightly. Divide the gnocchi and the sauce over two plates and finish with some lemon zest on top!',
    'Creamy gnocchi with peas', 2, 3, 1, 5),
(8, 15, null, 15, 'Preheat the oven to 200 degrees. Cover a baking tray with parchment paper. Beat the Greek yogurt with the eggs and milk and add a good pinch of salt and pepper. Mix the flour with the baking powder and add this little by little. Grate 100 grams of the halloumi. Cut the remaining 50 grams of the halloumi into small pieces (we will use this later for the topping). Add the halloumi grater to the mixture and stir well. Using a large spoon or ice cream scoop, place 8 scoops on the baking tray lined with parchment paper. Sprinkle the halloumi pieces and pumpkin seeds on top of the bulbs. Bake the buns in the oven for 15 minutes. Let it cool down and enjoy.',
    'Greek savory yogurt dumplings', 4, 1, 7, 7),
(9, 20, null, 5, 'Peel and finely chop the onion and garlic. Heat a dash of oil in a frying pan. Fry the onion with the garlic. Add the cumin and a good pinch of salt and pepper. Drain the water from the chickpea pot and rinse in a colander. Place the chickpeas in a food processor with the onion mixture, parsley and wheat flour and mix until coarsely. Then make 12 balls with your hands. Heat a good splash of oil in the pan and fry the falafel in 10 minutes until golden brown.',
   'Home made falafel', 12, 4, 6, 4);

/* cookbook_id, cookbook_name, is_private user_id */
INSERT INTO recipes.cookbook VALUES
(1, 'My first cookbook', 1, 3),
(2, 'My first cookbook', 0, 2),
(3, 'My first cookbook', 1, 1),
(4, 'My second cookbook', 1, 3),
(5, 'My second cookbook', 0, 2),
(6, 'My second cookbook', 1, 1);

INSERT INTO `recipes`.`ingredient` (`ingredient_id`, `ingredient_name`) VALUES
(1, 'egg'),
(2, 'flower'),
(3, 'butter'),
(4, 'strawberries'),
(5, 'milk'),
(6, 'vanilla pod'),
(7, 'salt'),
(8, 'sugar'),
(9, 'water'),
(10, 'onion'),
(11, 'garlic'),
(12, 'chickpeas'),
(13, 'ground cumin'),
(14, 'parsley'),
(15, 'wheat flour'),
(16, 'Greek  yoghurt'),
(17, 'baking powder'),
(18, 'Greek  halloumi'),
(19, 'pumpkin seeds'),
(20, 'gnocchi'),
(21, 'chicken thighs'),
(22, 'lemon'),
(23, 'peas'),
(24, 'cooking cream'),
(25, 'black olives'),
(26, 'cherry tomatoes'),
(27, 'basil'),
(28, 'dandelion lettuce'),
(29, 'burrata'),
(30, 'orzo'),
(31, 'bacon'),
(32, 'red pepper'),
(33, 'goat cheese'),
(34, 'red pesto');


INSERT INTO recipes.recipe_ingredients VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 5),
(3, 9),
(9, 10),
(9, 11),
(9, 12),
(9, 13),
(9, 14),
(9, 15),
(8, 1),
(8, 5),
(8, 2),
(8, 16),
(8, 17),
(8, 18),
(8, 19),
(7, 20),
(7, 21),
(7, 22),
(7, 23),
(7, 24),
(6, 25),
(6, 26),
(6, 27),
(6, 28),
(6, 29),
(5, 30),
(5, 31),
(5, 32),
(5, 33),
(5, 34);


