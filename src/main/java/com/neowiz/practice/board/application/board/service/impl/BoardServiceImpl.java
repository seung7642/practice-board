package com.neowiz.practice.board.application.board.service.impl;

import com.neowiz.practice.board.application.board.domain.Board;
import com.neowiz.practice.board.application.board.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Override
    public List<Board> getArticleList() {
        List<Board> list = new ArrayList<>();

        return list;
    }
}
