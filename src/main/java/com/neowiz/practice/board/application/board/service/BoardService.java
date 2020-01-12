package com.neowiz.practice.board.application.board.service;

import com.neowiz.practice.board.application.board.domain.Board;

import java.util.List;

public interface BoardService {

    Board getArticle();

    List<Board> getArticleList();
}
