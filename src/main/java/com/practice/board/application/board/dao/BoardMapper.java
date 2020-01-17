package com.practice.board.application.board.dao;

import com.practice.board.application.board.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    /**
     * 게시글 1개를 가져온다.
     *
     * @return Board
     */
    Board getArticle();

    /**
     * 전체 게시글 리스트를 가져온다.
     *
     * @return List<Board>
     */
    List<Board> getArticleList();

    /**
     * 전체 게시글의 갯수를 가져온다.
     *
     * @return
     */
    int getArticleCount();
}
