-- MySQL Workbench Synchronization
-- Generated: 2015-01-27 19:30
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Sergey

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `store` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

CREATE TABLE IF NOT EXISTS `store`.`add_prod_properties` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` INT(10) UNSIGNED NOT NULL,
  `property_name` VARCHAR(20) NULL DEFAULT NULL,
  `property_value` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_add_prod_properties` (`product_id` ASC),
  CONSTRAINT `FK_add_prod_properties`
    FOREIGN KEY (`product_id`)
    REFERENCES `store`.`products` (`product_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `store`.`order_details` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` INT(11) NOT NULL,
  `product_id` INT(10) UNSIGNED NOT NULL,
  `quantity_each` INT(10) UNSIGNED NOT NULL,
  `price_each` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_order_details` (`order_id` ASC),
  INDEX `FK2_order_details` (`product_id` ASC),
  CONSTRAINT `FK2_order_details`
    FOREIGN KEY (`product_id`)
    REFERENCES `store`.`products` (`product_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_order_details`
    FOREIGN KEY (`order_id`)
    REFERENCES `store`.`orders` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `store`.`orders` (
  `order_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `amount` DOUBLE NOT NULL,
  `date` DATETIME NOT NULL,
  `status` VARCHAR(20) NOT NULL,
  `comments` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `FK_orders` (`user_id` ASC),
  CONSTRAINT `FK_orders`
    FOREIGN KEY (`user_id`)
    REFERENCES `store`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `store`.`payments` (
  `payment_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `order_id` INT(10) UNSIGNED NOT NULL,
  `date` DATETIME NOT NULL,
  `amount` DOUBLE NOT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE INDEX `payment_id` (`payment_id` ASC),
  INDEX `user_id` (`user_id` ASC),
  CONSTRAINT `FK_payments`
    FOREIGN KEY (`user_id`)
    REFERENCES `store`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `store`.`products` (
  `product_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `type` VARCHAR(20) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `price` DOUBLE UNSIGNED NULL DEFAULT NULL,
  `quantity_in_stock` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `name` (`name` ASC),
  UNIQUE INDEX `name_2` (`name` ASC),
  UNIQUE INDEX `name_3` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `store`.`user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(30) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `email` VARCHAR(60) NULL DEFAULT NULL,
  `type` VARCHAR(20) NOT NULL,
  `in_black_list` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_name` (`user_name` ASC),
  UNIQUE INDEX `email` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `store`.`user_data` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `address` TINYTEXT NULL DEFAULT NULL,
  `phone` VARCHAR(20) NULL DEFAULT NULL,
  `comments` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_user_data` (`user_id` ASC),
  CONSTRAINT `FK_user_data`
    FOREIGN KEY (`user_id`)
    REFERENCES `store`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
