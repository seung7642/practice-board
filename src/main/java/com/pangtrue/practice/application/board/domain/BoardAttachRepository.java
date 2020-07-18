package com.pangtrue.practice.application.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface BoardAttachRepository extends JpaRepository<BoardAttach, String> {

    @Query("SELECT ba FROM BoardAttach ba WHERE ba.idx = idx")
    Stream<BoardAttach> findAllByIdx(Long idx);
}
