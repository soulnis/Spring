## Create DB
```
CREATE SCHEMA IF NOT EXISTS `SpringSecurity` DEFAULT CHARACTER SET utf8 ;
USE `SpringSecurity` ;

-- -----------------------------------------------------
-- Table `SpringSecurity`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SpringSecurity`.`user` (
  `idx` INT(11) NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(45) NOT NULL,
  `pw` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `SpringSecurity`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SpringSecurity`.`roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  `u_idx` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_roles_user_idx` (`u_idx` ASC),
  CONSTRAINT `fk_roles_user`
    FOREIGN KEY (`u_idx`)
    REFERENCES `SpringSecurity`.`user` (`idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;
```
