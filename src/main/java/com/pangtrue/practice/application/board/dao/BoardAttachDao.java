package com.pangtrue.practice.application.board.dao;

import com.pangtrue.practice.application.board.domain.BoardAttach;
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
public interface BoardAttachDao {

    /**
     * 업로드 테이블에 insert한다.
     *
     * @param boardAttach
     * @return
     */
    void insert(BoardAttach boardAttach);

    /**
     * uuid에 해당하는 레코드를 업로드 테이블에서 삭제한다.
     *
     * @param uuid
     * @return
     */
    void delete(String uuid);

    /**
     * 파라미터로 들어온 idx 값을 가지는 게시글의 첨부파일 목록을 가져온다.
     * @param boardIdx
     * @return List<BoardAttach>
     */
    List<BoardAttach> findByIdx(int boardIdx);
}