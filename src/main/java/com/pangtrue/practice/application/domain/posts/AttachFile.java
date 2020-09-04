package com.pangtrue.practice.application.domain.posts;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachFile {

    private String fileName;
    private String uploadPath;
    private String uuid;
    private boolean image;
}
