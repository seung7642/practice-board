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
    public Board getArticle() {
        return new Board();
    }

    @Override
    public List<Board> getArticleList() {
        return boardMapper.getArticleList();
    }

    @Override
    public int getArticleCount() {
        return boardMapper.getArticleCount();
    }

    @Override
    public void insertArticle(Board board) {
        board.setRegDate(new Date());
        boardMapper.insertArticle(board);
    }
}
