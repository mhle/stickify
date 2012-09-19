SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `stickify` ;
CREATE SCHEMA IF NOT EXISTS `stickify` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `stickify` ;

-- -----------------------------------------------------
-- Table `stickify`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stickify`.`user` ;

CREATE  TABLE IF NOT EXISTS `stickify`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(255) NOT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  `firstName` VARCHAR(255) NOT NULL ,
  `lastName` VARCHAR(255) NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  `modificationTime` TIMESTAMP NULL DEFAULT NULL ,
  `creationTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `username_UNIQUE` ON `stickify`.`user` (`username` ASC) ;

CREATE UNIQUE INDEX `email_UNIQUE` ON `stickify`.`user` (`email` ASC) ;


-- -----------------------------------------------------
-- Table `stickify`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stickify`.`role` ;

CREATE  TABLE IF NOT EXISTS `stickify`.`role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `stickify`.`role` (`name` ASC) ;


-- -----------------------------------------------------
-- Table `stickify`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stickify`.`user_role` ;

CREATE  TABLE IF NOT EXISTS `stickify`.`user_role` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `userId` INT NULL ,
  `roleId` INT(11) NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_user_role_user1`
    FOREIGN KEY (`userId` )
    REFERENCES `stickify`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_role1`
    FOREIGN KEY (`roleId` )
    REFERENCES `stickify`.`role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `userId` ON `stickify`.`user_role` (`userId` ASC) ;

CREATE INDEX `roleId` ON `stickify`.`user_role` (`roleId` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `stickify`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `stickify`;
INSERT INTO `stickify`.`user` (`id`, `username`, `email`, `firstName`, `lastName`, `password`, `modificationTime`, `creationTime`) VALUES (NULL, 'stickify', 'admin@stickify.com', 'admin', 'admin', '2ecacca4acf1df8ac8d16f82f7ecff5d15c6ae93156c0c53296d3f6f4c0ff5be63318571181e4f13', NULL, NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `stickify`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `stickify`;
INSERT INTO `stickify`.`role` (`id`, `name`) VALUES (1, 'ADMIN');

COMMIT;

-- -----------------------------------------------------
-- Data for table `stickify`.`user_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `stickify`;
INSERT INTO `stickify`.`user_role` (`id`, `userId`, `roleId`) VALUES (1, 1, 1);

COMMIT;
