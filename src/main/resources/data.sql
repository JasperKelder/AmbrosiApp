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
(1, 20, null, 'Flower, Butter, Strawberrys', 30, 'Just do it', 'Strawberry Cake', 8, 1, 3),
(2, 40, null, 'Broccoli, Rice, Chicken', 50, 'Asian style', 'Broccoli Dish', 4, 2, 3),
(3, 20, null, 'Chips, Frikandel with onions, curry and mayonaise', 30, 'From your local snackbar', 'Hollands glorie', 8, 1, 3),
(4, 20, null, 'Eierbal', 30, 'Just do it', 'Eierbal', 8, 1, 3);

/* user_id, email, first_name, last_name, password (test)  */
INSERT INTO recipes.user VALUES
(1, '42@gmail.com', 'Make IT Work', 'Elmo', '$2a$10$VRCtIl4CVgV5n9CspNQhkOMpz8KrfND5fiGUwlXTKsWSO99zRboqm'),
(2, 'b@', 'b', 'b', '$2a$10$Zp4Y6iXdx26oxeq8AxH.cOFEtuGZ2IVS9X6GVIoBwDfPxBuY/XKg6');

INSERT INTO recipes.role VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_USER');

INSERT INTO recipes.users_roles VALUES
(1, 1),
(2, 2);

/* The table next_val has to be updated (nr of users + 1) in order to make it possible to register new users */
UPDATE hibernate_sequence SET next_val = 3 WHERE next_val = 1;