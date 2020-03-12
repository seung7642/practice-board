package com.pangtrue.practice.application.management.dao;

import com.pangtrue.practice.application.management.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 11:50
 */
@Repository
@Mapper
public interface MemberMapper {

    List<Member> getMemberList();
}
