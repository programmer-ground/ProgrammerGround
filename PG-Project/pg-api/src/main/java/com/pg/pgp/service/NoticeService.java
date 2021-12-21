package com.pg.pgp.service;

import com.pg.pgp.domain.enumerated.ApplyStatus;
import com.pg.pgp.dto.user.response.UserApplyNoticeListResponse;
import com.pg.pgp.dto.user.response.UserApplyNoticeResponse;
import com.pg.pgp.dto.user.response.UserLeaderNoticeListResponse;
import com.pg.pgp.dto.user.response.UserLeaderNoticeResponse;
import com.pg.pgp.model.PlaygroundApplyRepository;
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
        List<ApplyStatus> statusList;
        switch (status) {
            case "wait":
                statusList = new ArrayList<>(Collections.singletonList(ApplyStatus.WAIT));
                break;
            case "result":
                statusList = new ArrayList<>(Arrays.asList(ApplyStatus.ACCEPT, ApplyStatus.REJECT));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
        return new UserApplyNoticeListResponse(
                UserApplyNoticeResponse.ofList(playgroundApplyRepository.findPlaygroundApplyByOAuthUserAndStatus(userId, statusList))
        );
    }
}
