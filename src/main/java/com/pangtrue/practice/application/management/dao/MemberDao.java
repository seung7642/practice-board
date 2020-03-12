package com.pangtrue.practice.application.management.dao;

import com.pangtrue.practice.application.management.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 3. 12.
 * Time: 오후 11:50
 */
@Repository
public interface MemberDao {

    List<Member> getMemberList();
}
