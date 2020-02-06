CREATE TABLE attach_tbl (
    uuid varchar(100) not null,
    uploadPath varchar(200) not null,
    fileName varchar(100) not null,
    fileType char(1) default 'I',
    board_idx int(11)
);

ALTER TABLE attach_tbl ADD CONSTRAINT pk_attach primary key (uuid);

ALTER TABLE attach_tbl ADD CONSTRAINT fk_board_attach foreign key (board_idx)
    REFERENCES board_tbl(idx);

-- MySQL WorkBench에서 GUI로 테이블을 생성했을 때, 아래와 같은 쿼리문이 실행되었다.
CREATE TABLE `practice_board`.`board_attach_tbl` (
  `uuid` VARCHAR(100) NOT NULL,
  `upload_path` VARCHAR(200) NOT NULL,
  `file_name` VARCHAR(100) NOT NULL,
  `file_type` CHAR(1) NOT NULL DEFAULT 'I',
  `board_idx` INT NOT NULL,
  PRIMARY KEY (`uuid`),
  INDEX `board_idx_idx` (`board_idx` ASC),
  CONSTRAINT `board_idx`
    FOREIGN KEY (`board_idx`)
    REFERENCES `practice_board`.`board_tbl` (`idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);