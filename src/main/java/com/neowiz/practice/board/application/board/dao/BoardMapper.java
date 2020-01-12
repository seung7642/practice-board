package com.neowiz.practice.board.application.board.dao;

import com.neowiz.practice.board.application.board.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("Board")
@Mapper
public interface BoardMapper {
    
    Board getArticle();
    List<Board> getArticleList();
    int getArticleCount();
}
