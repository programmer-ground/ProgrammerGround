package com.pg.programmerground.service;

import com.pg.programmerground.domain.enumerated.ApplyStatus;
import com.pg.programmerground.dto.user.response.UserApplyNoticeListResponse;
import com.pg.programmerground.dto.user.response.UserApplyNoticeResponse;
import com.pg.programmerground.dto.user.response.UserLeaderNoticeListResponse;
import com.pg.programmerground.dto.user.response.UserLeaderNoticeResponse;
import com.pg.programmerground.model.PlaygroundApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final PlaygroundApplyRepository playgroundApplyRepository;

    /**
     * User가 Leader인 Playground 신청 요청 리스트
     * WAIT(대기) 상태만 가져옴
     */
    public UserLeaderNoticeListResponse getUserNoticeList(Long userId) {
        return new UserLeaderNoticeListResponse(
                UserLeaderNoticeResponse.ofList(
                        playgroundApplyRepository.findPlaygroundApplyByLeader(userId)
                ));
    }

    public UserApplyNoticeListResponse getUserStatusNoticeList(Long userId, String status) {
        List<String> statusList;
        switch (status) {
            case "wait":
                statusList = new ArrayList<>(Collections.singletonList(ApplyStatus.WAIT.name()));
                break;
            case "result":
                statusList = new ArrayList<>(Arrays.asList(ApplyStatus.ACCEPT.name(), ApplyStatus.REJECT.name()));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
        return new UserApplyNoticeListResponse(
                UserApplyNoticeResponse.ofList(playgroundApplyRepository.findPlaygroundApplyByOAuthUserAndStatus(userId, statusList))
        );
    }
}
