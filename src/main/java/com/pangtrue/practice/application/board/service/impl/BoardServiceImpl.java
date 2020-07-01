package com.pangtrue.practice.application.board.service.impl;

import com.pangtrue.practice.application.board.dao.BoardDao;
import com.pangtrue.practice.application.board.domain.Board;
import com.pangtrue.practice.application.board.domain.Criteria;
import com.pangtrue.practice.application.board.domain.PageMaker;
import com.pangtrue.practice.application.board.service.BoardService;
import com.pangtrue.practice.commons.exception.NotValidException;
import com.pangtrue.practice.commons.utils.PreconditionUtils;
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

    private final BoardDao boardDao;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao) { this.boardDao = boardDao; }

    @Override
    public Board getArticle(Integer idx) {
        return boardDao.getArticle(idx);
    }

    @Override
    public PageMaker getArticleList(Pageable pageable) {
        PageMaker<Board> pageMaker = new PageMaker<>(
                boardDao.getArticleList(new Criteria(pageable.getPageNumber(), pageable.getPageSize())),
                pageable,
                boardDao.getArticleCount()
        );
        return pageMaker;
    }

    @Override
    public int getArticleCount() {
        return boardDao.getArticleCount();
    }

    @Override
    public int insertArticle(Board board) throws NotValidException {
        PreconditionUtils.invalidCondition((board.getTitle().isEmpty() || board.getContent().isEmpty()), "Invalid parameter.");

        return boardDao.insertArticle(board);
    }

    @Override
    public void updateHits(Integer idx) {
        boardDao.updateHits(idx);
    }

    @Override
    public Integer deleteArticle(Integer idx) {
        return boardDao.deleteArticle(idx);
    }
}
