INSERT INTO recipes.cuisine VALUES
(1,'Italian'),
(2,'Asian'),
(3,'Dutch'),
(4,'German'),
(5,'Spanish');

INSERT INTO recipes.category VALUES
(1,'Breakfast'),
(2,'Lunch'),
(3,'Dinner'),
(4,'Snacks'),
(5,'Cheating');

/* recipe_id, cooktime, ingredients, prep_time, recipe_prep, recipe_title, servings, category_id, cuisine_id */
INSERT INTO recipes.recipe VALUES
(1, 20, 'Flower, Butter, Strawberrys', 30, 'Just do it', 'Strawberry Cake', 8, 1, 3),
(2, 40, 'Broccoli, Rice, Chicken', 50, 'Asian style', 'Broccoli Dish', 4, 2, 3),
(3, 20, 'Chips, Frikandel with onions, curry and mayonaise', 30, 'From your local snackbar', 'Hollands glorie', 8, 1, 3);