package com.pangtrue.practice.board.application.dao;

import com.pangtrue.practice.board.application.domain.Board;
import com.pangtrue.practice.board.application.domain.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    /**
     * 게시글 1개를 가져온다.
     *
     * @param idx
     * @return Board
     */
    Board getArticle(Integer idx);

    /**
     * 전체 게시글 리스트를 가져온다.
     *
     * @param criteria
     * @return List<Board>
     */
    List<Board> getArticleList(Criteria criteria);

    /**
     * 전체 게시글의 갯수를 가져온다.
     *
     * @return int
     */
    int getArticleCount();

    /**
     * 게시글 하나를 저장한다.
     *
     * @param board
     */
    int insertArticle(Board board);

    /**
     * 조회수를 +1 시킨다.
     *
     * @param idx
     */
    void updateHits(Integer idx);
}
