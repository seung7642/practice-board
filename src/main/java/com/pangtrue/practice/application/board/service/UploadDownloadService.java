package com.pangtrue.practice.application.board.service;

import com.pangtrue.practice.application.board.dao.BoardAttachDao;
import com.pangtrue.practice.application.board.domain.BoardAttach;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 2. 11.
 * Time: 오전 9:50
 */
@Slf4j
@Service
public class UploadDownloadService implements UploadDownloadService {

    private final BoardAttachDao attachMapper;

    @Autowired
    public UploadDownloadService(BoardAttachDao attachMapper) {
        this.attachMapper = attachMapper;
    }

    public List<BoardAttach> getAttachList(int boardIdx) {
        return attachMapper.findByIdx(boardIdx);
    }
}
