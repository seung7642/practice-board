package com.pangtrue.practice.application.board.service;

import com.pangtrue.practice.application.board.domain.Board;
import com.pangtrue.practice.application.board.domain.BoardRepository;
import com.pangtrue.practice.application.board.web.dto.BoardMainResponse;
import com.pangtrue.practice.application.board.web.dto.BoardResponse;
import com.pangtrue.practice.application.board.web.dto.BoardSaveRequest;
import com.pangtrue.practice.application.board.web.dto.BoardUpdateRequest;
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

    @Transactional
    public Long update(Long id, BoardUpdateRequest requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        board.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public Long delete (Long idx) {
        Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. idx=" + idx));

        boardRepository.delete(board);
        return idx;
    }

    @Transactional(readOnly = true)
    public BoardResponse findById(Long idx) {
        Board entity = boardRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. idx=" + idx));

        return new BoardResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<BoardMainResponse> findAllDesc() {
        return boardRepository.findAllDesc()
                .map(BoardMainResponse::new)
                .collect(Collectors.toList());
    }
}
