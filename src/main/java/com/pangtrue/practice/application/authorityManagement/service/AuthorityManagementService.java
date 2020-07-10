package com.pangtrue.practice.application.authorityManagement.service;

public interface AuthorityManagementService {

    /**
     * 그룹을 생성한다. (예를 들어, 관리자 그룹 또는 사용자 그룹)
     * @param groupName
     * @return boolean
     */
    boolean createGroup(String groupName);

    /**
     * 그룹을 삭제한다.
     * @param groupName
     * @param id
     * @return boolean
     */
    boolean deleteGroup(String groupName, String id);
}
