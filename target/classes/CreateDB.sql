DROP SCHEMA IF EXISTS `recipes`;
CREATE SCHEMA IF NOT EXISTS `recipes`;
DROP USER IF EXISTS 'userRecipes'@'localhost';
CREATE USER 'userRecipes'@'localhost' IDENTIFIED BY 'pwRecipes';
GRANT ALL PRIVILEGES ON recipes.* TO 'userRecipes'@'localhost';
USE `recipes`;