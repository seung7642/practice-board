package com.pangtrue.practice.application.service.posts;

import com.pangtrue.practice.application.domain.posts.BoardAttachRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: SeungHo Lee (seung7642@gmail.com)
 * Date: 2020. 2. 11.
 * Time: 오전 9:50
 */
@Slf4j
@AllArgsConstructor
@Service
public class UploadDownloadService {

    private final BoardAttachRepository attachRepository;

    @Transactional(readOnly = true)
    public List<BoardAttachMainResponse> findAllByIdx(Long idx) {
        return attachRepository.findAllByIdx(idx)
                .map(BoardAttachMainResponse::new)
                .collect(Collectors.toList());
    }
}
