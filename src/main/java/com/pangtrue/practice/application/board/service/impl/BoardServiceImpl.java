package com.pangtrue.practice.application.board.service.impl;

import com.pangtrue.practice.application.board.dao.BoardMapper;
import com.pangtrue.practice.application.board.domain.Board;
import com.pangtrue.practice.application.board.domain.Criteria;
import com.pangtrue.practice.application.board.domain.PageMaker;
import com.pangtrue.practice.application.board.service.BoardService;
import com.pangtrue.practice.commons.exception.NotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) { this.boardMapper = boardMapper; }

    @Override
    public Board getArticle(Integer idx) {
        return boardMapper.getArticle(idx);
    }

    @Override
    public PageMaker getArticleList(Pageable pageable) {
        PageMaker<Board> pageMaker = new PageMaker<>(
                boardMapper.getArticleList(new Criteria(pageable.getPageNumber(), pageable.getPageSize())),
                pageable,
                boardMapper.getArticleCount()
        );
        return pageMaker;
    }

    @Override
    public int getArticleCount() {
        return boardMapper.getArticleCount();
    }

    @Override
    public int insertArticle(Board board) throws NotValidException {
        // Service단에서 파라미터로 넘어온 Board 객체에 대한 유효성 검증 처리 로직
        if (!isValid(board)) {
            throw new NotValidException();
        }

        return boardMapper.insertArticle(board);
    }

    @Override
    public void updateHits(Integer idx) {
        boardMapper.updateHits(idx);
    }

    private boolean isValid(Board board) {
        if (board.getTitle().isEmpty() || board.getContent().isEmpty()) {
            return false;
        }
        return true;
    }
}
