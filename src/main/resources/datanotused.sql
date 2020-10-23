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
(1, 60, null, 30, 'Salted caramel pie', 10, 5, 3, 4),
(2, 25, null, 5, 'Cod with chorizo from the oven', 2, 3, 5, 6),
(3, 40, null, 10, 'Simple asparagus quiche', 1, 2, 3, 5),
(4, 60, null, 20, 'Cookie dough truffels', 16, 4, 3, 4),
(5, 15, null, 5, 'Orzo with bacon and goat cheese', 2, 3, 1, 7),
(6, 10, null, 0, 'Salad with burrata and olives', 2, 2, 1, 6),
(7, 15, null, 5, 'Creamy gnocchi with peas', 2, 3, 1, 5),
(8, 15, null, 15, 'Greek savory yogurt dumplings', 4, 1, 7, 7),
(9, 20, null, 5, 'Home made falafel', 12, 4, 6, 4),
(10, 45, null, 10, 'Banana bread with chocolate and raspberries', 10, 1, 3, 7);

/* cookbook_id, cookbook_name, is_private user_id */
INSERT INTO recipes.cookbook VALUES
(1, 'Jasmijn her favorite cookbook ', 0, 4),
(2, 'Jasper his favorite cookbook', 0, 5),
(3, 'Reinout his favorite cookbook', 0, 6),
(4, 'Nathalie her favorite cookbook', 0, 7),
(5, 'Jasper likes snacks', 0, 5),
(6, 'Recipes my girlfriend loves', 1, 6),
(7, 'Test', 0, 2),
(8, 'Test', 0, 3);

INSERT INTO recipes.measuring_unit  (`measuring_unit_id`, `measuring_unit_name`, `measuring_unit_abbreviation`)  VALUES
(1, '', ''),
(2, 'gram', 'gr'),
(3, 'milliliter', 'ml'),
(4, 'pieces', 'pcs'),
(5, 'liter', 'l');

INSERT INTO `recipes`.`ingredient` VALUES
(1, 'egg', 1, 1),
(2, 'flower', 1, 2),
(3, 'butter', 1, 2),
(4, 'strawberries', 1, 2),
(5, 'milk', 1, 3),
(6, 'vanilla pod', 1, 1),
(7, 'salt', 1, 2),
(8, 'sugar', 1, 2),
(9, 'water', 1, 3),
(10, 'onion', 1, 4),
(11, 'garlic', 1, 4),
(12, 'chickpeas', 1, 2),
(13, 'ground cumin', 1, 2),
(14, 'parsley', 1, 2),
(15, 'wheat flour', 1, 2),
(16, 'Greek yoghurt', 1, 3),
(17, 'baking powder', 1, 2),
(18, 'Greek halloumi', 1, 2),
(19, 'pumpkin seeds', 1, 2),
(20, 'gnocchi', 1, 2),
(21, 'chicken thighs', 1, 2),
(22, 'lemon', 1, 1),
(23, 'peas', 1, 2),
(24, 'cooking cream', 1, 3),
(25, 'black olives', 1, 2),
(26, 'cherry tomatoes', 1, 2),
(27, 'basil', 1, 2),
(28, 'dandelion lettuce', 1, 2),
(29, 'burrata', 1, 2),
(30, 'orzo', 1, 2),
(31, 'bacon', 1, 2),
(32, 'red pepper', 1, 4),
(33, 'goat cheese', 1, 2),
(34, 'red pesto', 1, 2),
(35, 'chocolate cookies', 1, 4),
(36, 'light brown caster sugar', 1, 2),
(37, 'milk chocolate', 1, 2),
(38, 'pepper', 1, 2),
(39, 'zucchini', 1, 4),
(40, 'spring onions', 1, 4),
(41, 'crème fraîche', 1, 3),
(42, 'grana padano', 1, 2),
(43, 'puff pastry', 1, 2),
(44, 'chorizo', 1, 2),
(45, 'passata (sifted tomatoes)', 1, 2),
(46, 'codfish', 1, 2),
(47, 'ciabatta bread', 1, 4),
(48, 'butter cookies', 1, 4),
(49, 'unsalted butter', 1, 2),
(50, 'condensed milk', 1, 3),
(51, 'sea salt', 1, 2),
(52, 'fudge', 1, 2),
(53, 'bananas', 1, 4),
(54, 'honey', 1, 3),
(55, 'coconut oil', 1, 3),
(56, 'dark chocolate', 1, 2),
(57, 'raspberries', 1, 2);

INSERT INTO recipes.recipe_ingredient (`recipe_id`, `ingredient_id`) VALUES
(10, 1),
(10, 2),
(10, 17),
(10, 53),
(10, 54),
(10, 55),
(10, 56),
(10, 57),
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
(5, 34),
(4, 3),
(4, 5),
(4, 2),
(4, 8),
(4, 35),
(4, 36),
(4, 37),
(3, 38),
(3, 7),
(3, 39),
(3, 40),
(3, 41),
(3, 42),
(3, 43),
(2, 10),
(2, 11),
(2, 26),
(2, 44),
(2, 45),
(2, 46),
(2, 47),
(1, 36),
(1, 37),
(1, 5),
(1, 48),
(1, 49),
(1, 50),
(1, 51),
(1, 52);

UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '1' WHERE (`ingredient_id` = '1') and (`recipe_id` = '8');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '2' WHERE (`ingredient_id` = '1') and (`recipe_id` = '10');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '3' WHERE (`ingredient_id` = '2') and (`recipe_id` = '4');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '4' WHERE (`ingredient_id` = '2') and (`recipe_id` = '8');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '5' WHERE (`ingredient_id` = '2') and (`recipe_id` = '10');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '6' WHERE (`ingredient_id` = '3') and (`recipe_id` = '4');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '7' WHERE (`ingredient_id` = '5') and (`recipe_id` = '1');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '8' WHERE (`ingredient_id` = '5') and (`recipe_id` = '4');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '9' WHERE (`ingredient_id` = '5') and (`recipe_id` = '8');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '7') and (`recipe_id` = '3');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '111' WHERE (`ingredient_id` = '8') and (`recipe_id` = '4');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '10') and (`recipe_id` = '2');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '1' WHERE (`ingredient_id` = '10') and (`recipe_id` = '9');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '1' WHERE (`ingredient_id` = '11') and (`recipe_id` = '2');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '1' WHERE (`ingredient_id` = '11') and (`recipe_id` = '9');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '12') and (`recipe_id` = '9');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '1' WHERE (`ingredient_id` = '13') and (`recipe_id` = '9');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '14') and (`recipe_id` = '9');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '15') and (`recipe_id` = '9');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '16') and (`recipe_id` = '8');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '17') and (`recipe_id` = '8');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '17') and (`recipe_id` = '10');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '18') and (`recipe_id` = '8');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '19') and (`recipe_id` = '8');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '20') and (`recipe_id` = '7');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '21') and (`recipe_id` = '7');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '1' WHERE (`ingredient_id` = '22') and (`recipe_id` = '7');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '11' WHERE (`ingredient_id` = '23') and (`recipe_id` = '7');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '112' WHERE (`ingredient_id` = '24') and (`recipe_id` = '7');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '245' WHERE (`ingredient_id` = '25') and (`recipe_id` = '6');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '125' WHERE (`ingredient_id` = '26') and (`recipe_id` = '2');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '326' WHERE (`ingredient_id` = '26') and (`recipe_id` = '6');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '13' WHERE (`ingredient_id` = '27') and (`recipe_id` = '6');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '15' WHERE (`ingredient_id` = '28') and (`recipe_id` = '6');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '145' WHERE (`ingredient_id` = '29') and (`recipe_id` = '6');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '21315' WHERE (`ingredient_id` = '30') and (`recipe_id` = '5');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '13' WHERE (`ingredient_id` = '32') and (`recipe_id` = '5');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '3' WHERE (`ingredient_id` = '33') and (`recipe_id` = '5');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '32' WHERE (`ingredient_id` = '31') and (`recipe_id` = '5');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '23' WHERE (`ingredient_id` = '34') and (`recipe_id` = '5');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '23' WHERE (`ingredient_id` = '35') and (`recipe_id` = '4');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '235432' WHERE (`ingredient_id` = '36') and (`recipe_id` = '1');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '1' WHERE (`ingredient_id` = '36') and (`recipe_id` = '4');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '3' WHERE (`ingredient_id` = '37') and (`recipe_id` = '1');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '2' WHERE (`ingredient_id` = '37') and (`recipe_id` = '4');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '214' WHERE (`ingredient_id` = '38') and (`recipe_id` = '3');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '41' WHERE (`ingredient_id` = '39') and (`recipe_id` = '3');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '4' WHERE (`ingredient_id` = '40') and (`recipe_id` = '3');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '1' WHERE (`ingredient_id` = '41') and (`recipe_id` = '3');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '4' WHERE (`ingredient_id` = '42') and (`recipe_id` = '3');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '13' WHERE (`ingredient_id` = '43') and (`recipe_id` = '3');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '4346' WHERE (`ingredient_id` = '44') and (`recipe_id` = '2');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '67' WHERE (`ingredient_id` = '45') and (`recipe_id` = '2');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '76' WHERE (`ingredient_id` = '46') and (`recipe_id` = '2');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '7' WHERE (`ingredient_id` = '47') and (`recipe_id` = '2');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '768' WHERE (`ingredient_id` = '48') and (`recipe_id` = '1');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '7' WHERE (`ingredient_id` = '49') and (`recipe_id` = '1');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '8' WHERE (`ingredient_id` = '50') and (`recipe_id` = '1');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '52' WHERE (`ingredient_id` = '51') and (`recipe_id` = '1');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '2' WHERE (`ingredient_id` = '52') and (`recipe_id` = '1');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '5' WHERE (`ingredient_id` = '53') and (`recipe_id` = '10');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '54' WHERE (`ingredient_id` = '54') and (`recipe_id` = '10');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '242' WHERE (`ingredient_id` = '55') and (`recipe_id` = '10');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '4556' WHERE (`ingredient_id` = '56') and (`recipe_id` = '10');
UPDATE `recipes`.`recipe_ingredient` SET `quantity` = '56' WHERE (`ingredient_id` = '57') and (`recipe_id` = '10');


INSERT INTO `recipes`.`preparation_step` (`preparation_step_id`, `preparation_step`) VALUES (1, 'halloooo');
INSERT INTO `recipes`.`preparation_step` (`preparation_step_id`, `preparation_step`) VALUES (2, 'boee');

INSERT INTO recipe_preparation_step VALUES
(1, 1),
(1, 2);