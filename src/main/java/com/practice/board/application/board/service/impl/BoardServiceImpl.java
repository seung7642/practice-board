package com.practice.board.application.board.service.impl;

import com.practice.board.application.board.dao.BoardMapper;
import com.practice.board.application.board.domain.Board;
import com.practice.board.application.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) { this.boardMapper = boardMapper; }

    @Override
    public Board getArticle(Integer idx) throws Exception {
        return new Board();
    }

    @Override
    public List<Board> getArticleList() throws Exception {
        return boardMapper.getArticleList();
    }

    @Override
    public int getArticleCount() throws Exception {
        return boardMapper.getArticleCount();
    }

    @Override
    public void insertArticle(Board board) throws Exception {
        board.setRegDate(new Date());  // 왜 setter 에러나지?
        boardMapper.insertArticle(board);
    }

    @Override
    public void updateHits(Integer idx) throws Exception {
        boardMapper.updateHits(idx);
    }
}
