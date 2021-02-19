package com.pg.programmerground.service;

import com.pg.programmerground.domain.OAuthUser;
import com.pg.programmerground.domain.OAuthUserPlayground;
import com.pg.programmerground.domain.Playground;
import com.pg.programmerground.dto.PlayGroundInfoDto;
import com.pg.programmerground.dto.playground.MakePlaygroundInfoDto;
import com.pg.programmerground.dto.playground.PlaygroundCardInfoDto;
import com.pg.programmerground.exception.PlaygroundNotFoundException;
import com.pg.programmerground.model.OAuthUserRepository;
import com.pg.programmerground.model.PlaygroundRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaygroundService {
    private final PlaygroundRepository playgroundRepository;
    private final OAuthUserRepository oAuthUserRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public PlayGroundInfoDto findById(Long id) {
        Playground playGround = playgroundRepository.findById(id)
                .orElseThrow(PlaygroundNotFoundException::new);
        return modelMapper.map(playGround, PlayGroundInfoDto.class);
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
    public Long createPlayground(MakePlaygroundInfoDto playgroundInfo) {
        Long userId = UserAuthenticationService.getUserId();
        //로그인 유저 가져오기
        OAuthUser oAuthUser = oAuthUserRepository.findById(userId).orElseThrow();
        //OAuthUser 테이블과 Playground 테이블의 연관 테이블 생성
        OAuthUserPlayground oAuthUserPlayground = OAuthUserPlayground.createOAuthUserPlayground(oAuthUser);
        //Playground 생성
        Playground playground = Playground.createPlayground(oAuthUserPlayground, playgroundInfo);
        return playgroundRepository.save(playground).getId();
    }


}
