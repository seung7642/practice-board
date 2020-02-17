package com.practice.board.application.board.service;

import com.practice.board.application.board.domain.BoardAttach;

import java.util.List;

/**
 * User: SeungHo Lee (seung7642@neowiz.com)
 * Date: 2020. 2. 11.
 * Time: 오전 9:50
 */
public interface UploadDownloadService {

    /**
     * 등록한 업로드 파일 목록을 가져온다.
     *
     * @return List<BoardAttach>
     */
    List<BoardAttach> getAttachList(int boardIdx);


}
