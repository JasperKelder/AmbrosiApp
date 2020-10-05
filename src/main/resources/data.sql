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
(5, 40, null, 50, 'Asian style', 'Bubble milk tea', 4, 2, 3, 1),
(6, 10, null, 0, 'Snijd de olijven in plakjes en de cherrytomaatjes in vieren. Doe de helft van de basilicum samen met 3 eetlepels olijfolie en een snuf peper en zout in een kommetje en mix met een staafmixer goed door elkaar. Meng de veldsla met de olijven, de cherrytomaten, de basilicumolie en de rest van de basilicumblaadjes en maak af met de burrata on top.',
    'Salade met burrata en olijven', 2, 2, 1, 6),
(7, 15, null, 5, 'Verhit een scheutje olie in een koekenpan en bak hierin de gnocchi in 20 minuten gaar. Snijd de kipdijfilet in stukjes, rasp de schil van een halve citroen en pers het sap eruit. Verhit een scheutje olie in een andere koekenpan en bak de kip in zo''n 10 minuten gaar. Breng op smaak met peper en zout. Voeg de laatste 3 minuten de doperwten toe aan de kip. Voeg vervolgens het citroensap en de kookroom toe en breng het geheel aan de kook. Laat dit nog zo''n 3 minuten koken tot de room iets is ingedikt. Verdeel de gnocchi en de saus over twee borden en maak af met wat citroenrasp on top!',
    'Romige gnocchi met doperwten', 2, 3, 1, 5),
(8, 15, null, 15, 'Verwarm de oven voor op 200 graden. Bekleed een bakplaat met bakpapier. Klop de Griekse yoghurt met de eieren en melk en voeg een flinke snuf peper en zout toe. Meng de bloem met het bakpoeder en voeg dit beetje bij beetje toe. Rasp 100 gram van de halloumi. Snijd de overige 50 gram van de halloumi in kleine stukjes (dit gebruiken we straks voor de topping). Voeg de halloumi-rasp toe aan het mengsel en roer nog even goed door. Leg met een grote lepel of ijsschep 8 bollen op de met bakpapier beklede bakplaat. Strooi on top van de bollen de halloumi-stukjes en pompoenpitten. Bak de broodjes 15 minuten in de oven. Even laten afkoelen en smullen maar.',
    'Griekse hartige yoghurtbollen', 4, 1, 7, 7),
(9, 20, null, 5, 'Pel en snijd de ui en knoflook fijn. Verwarm een scheutje olie in een koekenpan. Bak de ui samen met de knoflook. Voeg de komijn toe en een flinke snuf peper en zout. Giet het water uit de pot van de kikkererwten en spoel ze af in een vergiet. Doe de kikkererwten in een keukenmachine samen met het ui-mengsel, de peterselie en de tarwebloem en meng tot een grof mengsel. Maak hiervan vervolgens met je handen 12 balletjes. Verwarm weer een flinke scheut olie in de pan en bak de falafel om en om in 10 minuten mooi goudbruin.',
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
(10, 'ui'),
(11, 'knoflook'),
(12, 'kikkererwten'),
(13, 'gemalen komijn'),
(14, 'peterselie'),
(15, 'tarwebloem'),
(16, 'Griekse yoghurt'),
(17, 'bakpoeder'),
(18, 'Griekse halloumi'),
(19, 'pompoenpitjes'),
(20, 'gnocchi'),
(21, 'kippendijen'),
(22, 'citroen'),
(23, 'doperwten'),
(24, 'kookroom'),
(25, 'zwarte olijven'),
(26, 'cherry tomaatjes'),
(27, 'basilicum'),
(28, 'veldsla'),
(29, 'burrata');


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
(6, 29);


