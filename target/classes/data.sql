INSERT INTO recipes.cuisine VALUES
(1,'Italian'),
(2,'Asian'),
(3,'Dutch'),
(4,'German'),
(5,'Spanish'),
(6,'Oriental');

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
(3, 'a', 'voornaam', 'achternaam', '$2a$10$aPj.8aZIFIlfHThl5mxTKug4egApzZfgVzmNLRpQTTsG6fi8A579O');

INSERT INTO recipes.role VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO recipes.user_roles VALUES
(1, 1),
(2, 2),
(2, 1),
(3, 1);

/* The table next_val has to be updated (nr of users + 1) in order to make it possible to register new users */
# UPDATE hibernate_sequence SET next_val = 4 WHERE next_val = 1;

/* recipe_id, cooktime, image ingredients, prep_time, recipe_prep, recipe_title, servings, category_id, cuisine_id, user_id */
/* Has to be after insertion of the values it references (category, cuisine, user)*/
INSERT INTO recipes.recipe VALUES
(1, 20, null, 30, 'Just do it', 'Strawberry Cake', 8, 1, 3, 1),
(2, 40, null, 50, 'Asian style', 'Broccoli Dish', 4, 2, 3, 1),
(3, 20, null, 30, 'From your local snackbar', 'Hollands glorie', 8, 1, 3, 1),
(4, 20, null, 30, 'Just do it', 'Eierbal', 8, 1, 3, 1),
(5, 40, null, 50, 'Asian style', 'Bubble milk tea', 4, 2, 3, 1),
(6, 20, null, 30, 'From your local snackbar', 'Frikandel speciaal', 8, 1, 3, 1),
(7, 20, null, 30, 'Just do it', 'Appeltaart', 8, 1, 3, 1),
(8, 40, null, 50, 'Asian style', 'Nachos', 4, 2, 3, 1),
(9, 20, null, 5, 'Pel en snijd de ui en knoflook fijn. Verwarm een scheutje olie in een koekenpan. Bak de ui samen met de knoflook. Voeg de komijn toe en een flinke snuf peper en zout. Giet het water uit de pot van de kikkererwten en spoel ze af in een vergiet. Doe de kikkererwten in een keukenmachine samen met het ui-mengsel, de peterselie en de tarwebloem en meng tot een grof mengsel. Maak hiervan vervolgens met je handen 12 balletjes. Verwarm weer een flinke scheut olie in de pan en bak de falafel om en om in 10 minuten mooi goudbruin.',
   'Home made falafel', 12, 4, 6, 3);

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
(10, 'ui'),
(11, 'knoflook'),
(12, 'kikkererwten'),
(13, 'gemalen komijn'),
(14, 'peterselie'),
(15, 'tarwebloem');

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
(9, 15);
