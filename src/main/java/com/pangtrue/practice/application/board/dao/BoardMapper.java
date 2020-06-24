package com.pangtrue.practice.application.board.dao;

import com.pangtrue.practice.application.board.domain.Board;
import com.pangtrue.practice.application.board.domain.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 1. 10.
 * Time: 오후 8:18
 */
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

    /**
     * 게시글을 삭제한다.
     *
     * @param idx
     * @return
     */
    Integer deleteArticle(Integer idx);
}
