package com.pangtrue.practice.application.authorityManagement.service;

import com.pangtrue.practice.application.authorityManagement.domain.AuthorityRepository;
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
public class AuthorityManagementService {

    private final AuthorityRepository authorityRepository;

    public boolean createGroup(String groupName) {
//        if (0 > authorityRepository.createGroup(groupName)) {
//            return false;
//        }

        return false;
    }

    public boolean deleteGroup(String groupName, String id) {
        return false;
    }
}
