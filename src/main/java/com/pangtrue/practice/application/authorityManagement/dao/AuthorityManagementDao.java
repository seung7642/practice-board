package com.pangtrue.practice.application.authorityManagement.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.07.10
 * Time: 18:45
 */
@Repository
public interface AuthorityManagementDao {

    /**
     * 그룹을 생성한다. (예를 들어, 관리자 그룹 또는 사용자 그룹)
     * @param groupName
     * @return boolean
     */
    boolean createGroup(@Param("groupName") String groupName);

    /**
     * 그룹을 삭제한다.
     * @param groupName
     * @return boolean
     */
    boolean deleteGroup(@Param("groupName") String groupName);
}
