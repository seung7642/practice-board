package com.pangtrue.practice.application.authorityManagement.service.impl;

import com.pangtrue.practice.application.authorityManagement.dao.AuthorityManagementDao;
import com.pangtrue.practice.application.authorityManagement.service.AuthorityManagementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * User: Seungho Lee (seung7642@gmail.com)
 * Date: 2020.07.10
 * Time: 18:40
 */
@Slf4j
@AllArgsConstructor
@Service
public class AuthorityManagementServiceImpl implements AuthorityManagementService {

    private final AuthorityManagementDao authorityManagementDao;

    @Override
    public boolean createGroup(String groupName) {
        if (0 > authorityManagementDao.createGroup(groupName)) {
            return false;
        }

        return false;
    }

    @Override
    public boolean deleteGroup(String groupName, String id) {
        return false;
    }
}
