package com.practice.board.application.board.service.impl;

import com.practice.board.application.board.dao.BoardMapper;
import com.practice.board.application.board.domain.Board;
import com.practice.board.application.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) { this.boardMapper = boardMapper; }

    @Override
    public Board getArticle() {

        return new Board();
    }

    @Override
    public List<Board> getArticleList() {
        List<Board> list = new ArrayList<>();

        return list;
    }

    @Override
    public int getArticleCount() {
        return boardMapper.getArticleCount();
    }

    @Override
    public void insertArticle(Board board) {
        boardMapper.insertArticle(board);
    }
}
