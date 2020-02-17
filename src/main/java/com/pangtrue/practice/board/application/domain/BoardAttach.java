package com.pangtrue.practice.board.application.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("BoardAttach")
@Data
public class BoardAttach {

    private String uuid;
    private String uploadPath;
    private String fileName;
    private boolean fileType;

    private Long boardIdx;
}
