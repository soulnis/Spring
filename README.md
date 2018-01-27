## JAVA관련 공부

`MySql 5.7`
```
-- Toy Project in Spring
CREATE SCHEMA `toyDB` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `toyDB`.`member` (
		`userid` VARCHAR(50) NOT NULL,
		`userpw` VARCHAR(50) NOT NULL,
		`username` VARCHAR(50) NOT NULL,
		`email` VARCHAR(100) NOT NULL,
		`regdate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
		`updatedate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
		PRIMARY KEY (`userid`));

CREATE TABLE `toyDB`.`board` (
		`no` INT NOT NULL AUTO_INCREMENT,
		`title` VARCHAR(200) NOT NULL,
		`content` TEXT NOT NULL,
		`writer` VARCHAR(50) NOT NULL,
		`regdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
		`viewcnt` INT(11) NULL DEFAULT 0,
		PRIMARY KEY (`no`));

CREATE TABLE IF NOT EXISTS `toyDB`.`reply` (
		`no` INT NOT NULL AUTO_INCREMENT,
		`bno` INT(11) NOT NULL,
		`replytext` VARCHAR(1000) NOT NULL,
		`replyer` VARCHAR(45) NOT NULL,
		`regdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
		`updatedate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
		PRIMARY KEY (`no`),
		INDEX `reply_idx` (`bno` ASC),
		CONSTRAINT `fk_reply_board`
		FOREIGN KEY (`bno`)
		REFERENCES `toyDB`.`board` (`no`)
		ON DELETE CASCADE
		ON UPDATE CASCADE)
ENGINE = InnoDB;

```


1. MVC: JAVA와 DataBase(MySQL) 연결
2. Toy: Member 관련
3. ToyBoard: 게시판
4. ToyBoardReply: 게시판-댓글추가
