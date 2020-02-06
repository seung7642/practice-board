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