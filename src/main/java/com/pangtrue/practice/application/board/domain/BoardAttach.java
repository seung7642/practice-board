package com.pangtrue.practice.application.board.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.ibatis.type.Alias;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class BoardAttach {

    @Id
    private String uuid;

    private String uploadPath;

    private String fileName;

    private boolean fileType;

    private Long idx;

    @Builder
    public BoardAttach(String uuid, String uploadPath, String fileName, boolean fileType) {
        this.uuid = uuid;
        this.uploadPath = uploadPath;
        this.fileName = fileName;
        this.fileType = fileType;
    }
}
