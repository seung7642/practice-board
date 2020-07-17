package com.pangtrue.practice.application.board.service;

import com.pangtrue.practice.application.board.domain.BoardRepository;
import com.pangtrue.practice.application.board.dto.BoardMainResponse;
import com.pangtrue.practice.application.board.dto.BoardSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequest request) {
        return boardRepository.save(request.toEntity()).getIdx();
    }

    @Transactional(readOnly = true)
    public List<BoardMainResponse> findAllDesc() {
        return boardRepository.findAllDesc()
                .map(BoardMainResponse::new)
                .collect(Collectors.toList());
    }
}
