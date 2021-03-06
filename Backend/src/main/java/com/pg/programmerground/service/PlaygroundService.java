package com.pg.programmerground.service;

import com.pg.programmerground.domain.OAuthUser;
import com.pg.programmerground.domain.Playground;
import com.pg.programmerground.domain.PlaygroundApply;
import com.pg.programmerground.domain.PlaygroundPosition;
import com.pg.programmerground.dto.playground.ApplyPlaygroundDto;
import com.pg.programmerground.dto.playground.MakePlaygroundInfoDto;
import com.pg.programmerground.dto.playground.PlaygroundCardInfoDto;
import com.pg.programmerground.dto.playground.PlaygroundInfoDto;
import com.pg.programmerground.exception.PlaygroundNotFoundException;
import com.pg.programmerground.model.OAuthUserRepository;
import com.pg.programmerground.model.PlaygroundRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaygroundService {
    private final PlaygroundRepository playgroundRepository;
    private final OAuthUserRepository oAuthUserRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public PlaygroundInfoDto findById(Long id) {
        Playground playGround = playgroundRepository.findById(id)
                .orElseThrow(PlaygroundNotFoundException::new);
        return modelMapper.map(playGround, PlaygroundInfoDto.class);
    }

    /**
     * 메인 페이지 playground card 목록 가져오기
     */
    @Transactional(readOnly = true)
    public List<PlaygroundCardInfoDto> getPlaygroundCardList() {
        return PlaygroundCardInfoDto.makePlaygroundCardList(playgroundRepository.findAll());
    }

    /**
     * Playground 생성
     */
    @Transactional
    public Long createPlayground(MakePlaygroundInfoDto playgroundInfo) throws Exception {
        //로그인 유저 가져오기
        OAuthUser leaderUser = oAuthUserRepository.findById(UserAuthenticationService.getUserId()).orElseThrow();
        //Playground Position 객체 리스트 만들기
        List<PlaygroundPosition> playgroundPositionList = PlaygroundPosition.createPosition(playgroundInfo.getPositionInfo());
        //Playground 생성
        Playground playground = Playground.createPlayground(playgroundInfo, leaderUser, playgroundPositionList);
        //리더 포지션 검색
        PlaygroundPosition leaderPosition = searchLeaderPosition(playgroundPositionList, playgroundInfo.getLeaderPosition());
        //리더도 Position에 포함되야하므로 PlaygroundApply 객체를 만든다.
        PlaygroundApply.createLeaderApply(leaderUser, playground, leaderPosition);
        return playgroundRepository.save(playground).getId();
    }

    /**
     * Leader Position을 생성시 입력받은 Position 중에 탐색
     */
    private PlaygroundPosition searchLeaderPosition(List<PlaygroundPosition> playgroundPositions, String positionName) {
        //입력받은 Position중에 Leader가 신청한 Position 탐색
        return playgroundPositions.stream()
                .filter(playgroundPosition -> {
                    return playgroundPosition.getPosition().name().equals(positionName);
                }).findFirst()
                .orElseThrow(() -> new NoSuchElementException("입력되지 않은 포지션입니다"));
    }

    /**
     * User가 Playground 신청
     */
    @Transactional
    public Boolean applyPlayground(Long playgroundId, ApplyPlaygroundDto applyPlayground) throws Exception {
        //유저 정보
        OAuthUser user = oAuthUserRepository.findById(UserAuthenticationService.getUserId()).orElseThrow();
        //Playground 정보
        Playground playground = playgroundRepository.findById(playgroundId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 Playground"));
        //유저가 신청한 Position 탐색
        PlaygroundPosition userPosition = playground.searchPosition(applyPlayground.getPositionId());
        PlaygroundApply.createUserApply(user, playground, userPosition);
        return true;
    }

    @Transactional(readOnly = true)
    public PlaygroundInfoDto getPlaygroundDetailInfo(Long playgroundId) {
        return PlaygroundInfoDto.of(
                playgroundRepository.findById(playgroundId).orElseThrow(() -> new NoSuchElementException("playground 존재 안함")));
    }
}
