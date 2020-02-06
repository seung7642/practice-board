package com.practice.board.application.board.dao;

import com.practice.board.application.board.domain.BoardAttach;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardAttachMapper {

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
    List<BoardAttach> findByIdx(Long boardIdx);
}