package com.pg.programmerground.service;

import com.pg.programmerground.domain.OAuthUser;
import com.pg.programmerground.domain.Playground;
import com.pg.programmerground.domain.PlaygroundApply;
import com.pg.programmerground.domain.PlaygroundPosition;
import com.pg.programmerground.dto.playground.api_req.ApplyPlaygroundApi;
import com.pg.programmerground.dto.playground.api_req.PlaygroundApi;
import com.pg.programmerground.dto.playground.response.PlaygroundCardResponse;
import com.pg.programmerground.dto.playground.response.PlaygroundResponse;
import com.pg.programmerground.model.OAuthUserRepository;
import com.pg.programmerground.model.PlaygroundApplyRepository;
import com.pg.programmerground.model.PlaygroundRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaygroundService {
    private final PlaygroundRepository playgroundRepository;
    private final PlaygroundApplyRepository playgroundApplyRepository;
    private final OAuthUserRepository oAuthUserRepository;
    private final ModelMapper modelMapper;

    /**
     * 메인 페이지 playground card 목록 가져오기
     */
    public List<PlaygroundCardResponse> getPlaygroundCardList() {
        return PlaygroundCardResponse.ofList(playgroundRepository.findAll());
    }

    /**
     * Playground 생성
     */
    @Transactional
    public Long createPlayground(PlaygroundApi playgroundInfo) throws Exception {
        //로그인 유저 가져오기
        OAuthUser leaderUser = oAuthUserRepository.findById(UserAuthenticationService.getUserId()).orElseThrow();
        //Playground Position 객체 리스트 만들기
        List<PlaygroundPosition> playgroundPositionList = PlaygroundPosition.createPosition(playgroundInfo);
        //Playground 생성
        Playground playground = Playground.createPlayground(playgroundInfo, leaderUser, playgroundPositionList);
        //리더 포지션 검색
        PlaygroundPosition leaderPosition = PlaygroundPosition.searchLeaderPosition(playgroundPositionList, playgroundInfo.getLeaderPosition());
        //리더도 Position에 포함되야하므로 PlaygroundApply 객체를 만든다.
        PlaygroundApply.createLeaderApply(leaderUser, playground, leaderPosition);
        return playgroundRepository.save(playground).getId();
    }


    /**
     * User가 Playground Member신청
     */
    @Transactional
    public Boolean applyPlayground(Long playgroundId, ApplyPlaygroundApi applyPlayground) {
        //유저 정보
        OAuthUser user = oAuthUserRepository.findById(UserAuthenticationService.getUserId()).orElseThrow();
        //Playground 정보
        Playground playground = playgroundRepository.findById(playgroundId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 Playground"));
        //유저가 신청한 Position 탐색
        PlaygroundPosition userPosition = playground.searchPosition(applyPlayground.getPositionId());
        PlaygroundApply.createUserApply(user, playground, userPosition);
        return true;
    }

    /**
     * Playground 요청 수락
     */
    @Transactional
    public Boolean acceptPlayground(Long playgroundApplyId) {
        PlaygroundApply playgroundApply = playgroundApplyRepository.findById(playgroundApplyId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 Playground 요청입니다"));
        OAuthUser user = oAuthUserRepository.findById(UserAuthenticationService.getUserId()).orElseThrow();
        playgroundApply.acceptApply(user);
        return true;
    }

    @Transactional
    public Boolean rejectPlayground(Long playgroundApplyId) {
        PlaygroundApply playgroundApply = playgroundApplyRepository.findById(playgroundApplyId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 Playground 요청입니다"));
        OAuthUser user = oAuthUserRepository.findById(UserAuthenticationService.getUserId()).orElseThrow();
        playgroundApply.rejectApply(user);
        return true;
    }

    public PlaygroundResponse getPlaygroundDetailInfo(Long playgroundId) {
        return PlaygroundResponse.of(
                playgroundRepository.findById(playgroundId).orElseThrow(() -> new NoSuchElementException("playground 존재 안함")));
    }
}
