package com.pg.programmerground.service;

import com.pg.programmerground.dto.user.response.UserNoticeListResponse;
import com.pg.programmerground.dto.user.response.UserNoticeResponse;
import com.pg.programmerground.model.PlaygroundApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final PlaygroundApplyRepository playgroundApplyRepository;

    public UserNoticeListResponse getUserNoticeList(Long userId) {
        return new UserNoticeListResponse(
                UserNoticeResponse.ofList(
                        playgroundApplyRepository.findPlaygroundApplyByLeader(userId)
                ));
    }
}
