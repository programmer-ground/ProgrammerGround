package com.pg.programmerground.service;

import com.pg.programmerground.domain.OAuthUser;
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
    private final PlaygroundRepository playGroundRepository;
    private final OAuthUserRepository oAuthUserRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public PlayGroundInfoDto findById(Long id) {
        Playground playGround = playGroundRepository.findById(id)
                .orElseThrow(PlaygroundNotFoundException::new);
        return modelMapper.map(playGround, PlayGroundInfoDto.class);
    }

    /**
     * 메인 페이지 playground card 목록 가져오기
     */
    @Transactional(readOnly = true)
    public List<PlaygroundCardInfoDto> getPlaygroundCardList() {
        return PlaygroundCardInfoDto.makePlaygroundCardList(playGroundRepository.findAll());
    }

    @Transactional
    public Integer createPlayground(MakePlaygroundInfoDto playgroundInfo) {
        Long userId = UserAuthenticationService.getUserId();
        OAuthUser oAuthUser = oAuthUserRepository.findById(userId).orElseThrow();
        return null;
    }


}
