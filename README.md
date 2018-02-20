## JAVA관련 공부
`Spring STS3.8.2`
`TomCat8.0`
`MySql 5.7`
`JDK1.8`
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

CREATE TABLE IF NOT EXISTS `toyDB`.`message` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NOT NULL,
  `opendate` TIMESTAMP NULL,
  `senddate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `targetid` VARCHAR(50) NOT NULL,
  `sender` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`no`),
  INDEX `fk_user_target` (`targetid` ASC),
  INDEX `fk_user_sender` (`sender` ASC),
  CONSTRAINT `fk_user_target`
    FOREIGN KEY (`targetid`)
    REFERENCES `toyDB`.`member` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_sender`
    FOREIGN KEY (`sender`)
    REFERENCES `toyDB`.`member` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `toyDB`.`message` 
CHANGE COLUMN `targetid` `targetid` VARCHAR(50) NOT NULL AFTER `no`,
CHANGE COLUMN `sender` `sender` VARCHAR(50) NOT NULL AFTER `targetid`;
		ON UPDATE CASCADE)
ENGINE = InnoDB;

ALTER TABLE `toyDB`.`member` 
ADD COLUMN `point` INT NOT NULL DEFAULT 0 AFTER `updatedate`;


ALTER TABLE `toyDB`.`board` 
ADD COLUMN `replycnt` INT NOT NULL DEFAULT 0 AFTER `viewcnt`;

CREATE TABLE IF NOT EXISTS `toyDB`.`attach` (
                `fullName` VARCHAR(150) NOT NULL,
                `bno` INT(11) NOT NULL,
                `regdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                PRIMARY KEY (`fullName`),
                INDEX `fk_attach_board_idx` (`bno` ASC),
                CONSTRAINT `fk_attach_board`
                FOREIGN KEY (`bno`)
                REFERENCES `toyDB`.`board` (`no`)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
```


1. MVC: JAVA와 DataBase(MySQL) 연결
2. Toy: Member 관련
3. ToyBoard: 게시판
4. ToyBoardReply: 게시판-댓글추가(페이징추가)
5. ToyBoardReplyAOP: 트랜젝션, AOP추가
6. FileUploadTest: File업로드 테스트
7. ToyBoardReplyAOP_FILE: 파일업로드 추가
8. ToyBoardReplyLogin: 로그인기능 추가중..
