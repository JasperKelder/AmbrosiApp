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
(3, 20, 'Chips, Frikandel with onions, curry and mayonaise', 30, 'From your local snackbar', 'Hollands glorie', 8, 1, 3),
(4, 20, 'Eierbal', 30, 'Just do it', 'Eierbal', 8, 1, 3),
(5, 40, 'Bubble milk thea', 50, 'Asian style', 'Bubble milk thea', 4, 2, 3),
(6, 20, 'Frikandel speciaal', 30, 'From your local snackbar', 'Frikandel speciaal', 8, 1, 3),
(7, 20, 'Appeltaart', 30, 'Just do it', 'Appeltaart', 8, 1, 3),
(8, 40, 'Nachos', 50, 'Asian style', 'Nachos', 4, 2, 3),
(9, 40, 'Soep, Vlees, Pudding SVP', 50, 'Asian style', 'Soep, Vlees, Pudding SVP', 4, 2, 3),
(10, 40, 'Broodje kip', 50, 'Asian style', 'Broodje kip', 4, 2, 3),
(11, 40, 'Maaltijdsalade a la Appie', 50, 'Asian style', 'Maaltijdsalade a la Appie', 4, 2, 3),
(12, 40, 'Bubble milk thea', 50, 'Asian style', 'Rooibos thea', 4, 2, 3),
(13, 20, 'Frikandel speciaal', 30, 'From your local snackbar', 'Gehaktbal', 8, 1, 3),
(14, 20, 'Appeltaart', 30, 'Just do it', 'Mokka gebak', 8, 1, 3),
(15, 40, 'Nachos', 50, 'Asian style', 'Nachos', 4, 2, 3),
(16, 40, 'Soep, Vlees, Pudding SVP', 50, 'Asian style', 'Zalmfilet sesam', 4, 2, 3),
(17, 40, 'Broodje kip', 50, 'Asian style', 'Broodje gehakt', 4, 2, 3),
(18, 40, 'Maaltijdsalade a la Appie', 50, 'Asian style', 'Maaltijdsalade a la Jumbo', 4, 2, 3);

/* user_id, email, first_name, last_name, password = test  */
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
UPDATE hibernate_sequence SET next_val = 4 WHERE next_val = 1;