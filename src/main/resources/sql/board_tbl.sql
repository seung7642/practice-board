CREATE TABLE PRACTICE_BOARD.BOARD_TBL (
    idx INT(11) AUTO_INCREMENT,
    title varchar(255) NOT NULL,
    content longtext() NOT NULL,
    writer varchar(45) NOT NULL,
    reg_date datetime NOT NULL, DEFAULT CURRENT_TIMESTAMP,
    upd_date datetime NOT NULL, DEFAULT CURRENT_TIMESTAMP,
    hits int(11) NOT NULL, DEFAULT 0,

    PRIMARY KEY(idx)
)