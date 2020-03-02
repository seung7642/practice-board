CREATE TABLE `practice_board`.`board_tbl` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `title` LONGTEXT NOT NULL,
  `content` LONGTEXT NOT NULL,
  `writer` VARCHAR(45) NOT NULL,
  `reg_date` DATETIME NOT NULL DEFAULT now(),
  `upd_date` DATETIME NOT NULL DEFAULT now(),
  `hits` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idx`),
  INDEX `INDEX_REG_DATE` (`reg_date` ASC) COMMENT '페이징 처리를 위한 인덱스.' VISIBLE
);
