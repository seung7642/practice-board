package com.pangtrue.practice.application.board.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.ibatis.type.Alias;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Alias("BoardAttach")
public class BoardAttach {

    private String uuid;
    private String uploadPath;
    private String fileName;
    private boolean fileType;
    private Long boardIdx;
}
