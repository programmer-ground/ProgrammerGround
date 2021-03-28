package com.pg.programmerground.playground;

import com.pg.programmerground.auth.jwt.JwtAuthenticationToken;
import com.pg.programmerground.domain.Playground;
import com.pg.programmerground.dto.playground.api_req.ApplyPlaygroundApi;
import com.pg.programmerground.dto.playground.api_req.PlaygroundApi;
import com.pg.programmerground.exception.WrongRequestException;
import com.pg.programmerground.model.PlaygroundRepository;
import com.pg.programmerground.service.OAuthUserService;
import com.pg.programmerground.service.PlaygroundService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlaygroundServiceTest {
    private static final Long GITHUB_ID = 32676275L;
    private static final Long GITHUB_ID2 = 80170559L;
    TestJsonPackage dtoList;
    @Autowired
    private OAuthUserService oAuthUserService;
    @Autowired
    private PlaygroundService playgroundService;
    @Autowired
    private PlaygroundRepository playgroundRepository;

    //private MockMvc mvc;

    @BeforeEach
    void init() {
        //Github 로그인을 한번 시도하고
        UserDetails userDetails = oAuthUserService.loadUserByOAuthId(GITHUB_ID);
        Authentication authentication = new JwtAuthenticationToken(userDetails, "토큰 값 필요없음", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @BeforeAll
    void jsonInit() throws IOException {
        dtoList = TestJsonPackage.of();
    }

    @Test
    @Transactional
    void Playground_생성() throws Exception {
        //given
        PlaygroundApi playgroundApi = dtoList.createPlayground;
        //when
        Long playgroundId = playgroundService.createPlayground(playgroundApi);
        Playground playground = playgroundRepository.findById(playgroundId).orElseThrow();
        //then
        assertEquals(playgroundApi.getMaxUserNum(), playground.getMaxMemberCount(), "최대 인원이 다름");
        //...등등
    }

    @Test
    void Playground_Position_합_예외() throws Exception {
        //given
        PlaygroundApi playgroundApi = dtoList.positionSum;
        //when then
        assertThrows(WrongRequestException.class, () -> {
            Long playgroundId = playgroundService.createPlayground(playgroundApi);
        });
    }

    @Test
    void Playground_Position_Enum_예외() throws Exception {
        PlaygroundApi playgroundApi = dtoList.positionEnum;
        assertThrows(IllegalArgumentException.class, () -> {
            Long playgroundId = playgroundService.createPlayground(playgroundApi);
        });
    }

    @Test
    void Playground_Leader_Position_존재안함_예외() throws Exception {
        PlaygroundApi playgroundApi = dtoList.leaderPosition;
        assertThrows(NoSuchElementException.class, () -> {
            Long playgroundId = playgroundService.createPlayground(playgroundApi);
        });
    }

    @Test
    @Transactional
    void Playground_Member_신청() throws Exception {
        //playground 생성
        PlaygroundApi playgroundApi = dtoList.applyPosition;
        Long playgroundId = playgroundService.createPlayground(playgroundApi);
        //playground position 가져오기
        Playground playground = playgroundRepository.findById(playgroundId).orElseThrow();
        Long positionId = playground.getPlaygroundPositionList().get(0).getId();
        ApplyPlaygroundApi applyPlaygroundApi = new ApplyPlaygroundApi();
        Whitebox.setInternalState(applyPlaygroundApi, "positionId", positionId);

        //유저 전환
        UserDetails userDetails = oAuthUserService.loadUserByOAuthId(GITHUB_ID2);
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(userDetails, "토큰 값 필요없음", userDetails.getAuthorities()));
        //when
        boolean rst = playgroundService.applyPlayground(playgroundId, applyPlaygroundApi);
        //then
        assertTrue(rst);
    }

    @Test
    @Transactional
    void Playground_Member_같은_유저_신청_예외() throws Exception {
        //playground 생성
        PlaygroundApi playgroundApi = dtoList.applyPosition;
        Long playgroundId = playgroundService.createPlayground(playgroundApi);
        //playground position 가져오기
        Playground playground = playgroundRepository.findById(playgroundId).orElseThrow();
        Long positionId = playground.getPlaygroundPositionList().get(0).getId();
        ApplyPlaygroundApi applyPlaygroundApi = new ApplyPlaygroundApi();
        Whitebox.setInternalState(applyPlaygroundApi, "positionId", positionId);
        //리더가 자기 playground를 신청
        assertThrows(WrongRequestException.class, () -> {
            boolean rst = playgroundService.applyPlayground(playgroundId, applyPlaygroundApi);
        });
    }
}
