package com.practice.board.application.board.service.impl;

import com.practice.board.application.board.dao.BoardMapper;
import com.practice.board.application.board.domain.Board;
import com.practice.board.application.board.domain.Criteria;
import com.practice.board.application.board.domain.PageMaker;
import com.practice.board.application.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public PageMaker<Board> getArticleList(Pageable pageable) {
        // 서비스단의 로직에서 매퍼에서 가져온 List<Board>를 PageImpl로 변환 -> PageImpl을 상속받아
        // [이전] [다음] 탭을 위한 PageMaker를 만들기
        PageMaker<Board> pageMaker = new PageMaker<Board>(
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
    public int insertArticle(Board board) {
        board.setRegDate(new Date());  // 왜 setter 에러나지?
        boardMapper.insertArticle(board);
        return board.getIdx();
    }

    @Override
    public void updateHits(Integer idx) {
        boardMapper.updateHits(idx);
    }
}
