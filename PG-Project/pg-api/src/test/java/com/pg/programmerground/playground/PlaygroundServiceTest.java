package com.pg.programmerground.playground;

import com.pg.programmerground.TestUserManagement;
import com.pg.programmerground.auth.jwt.JwtAuthenticationToken;
import com.pg.pgp.domain.Playground;
import com.pg.pgp.domain.PlaygroundApply;
import com.pg.pgp.domain.enumerated.ApplyStatus;
import com.pg.pgp.domain.enumerated.PlaygroundStatus;
import com.pg.pgp.dto.playground.api_req.ApplyPlaygroundApi;
import com.pg.pgp.dto.playground.api_req.PlaygroundApi;
import com.pg.pgp.exceptions.FileExtractException;
import com.pg.pgp.exceptions.WrongRequestException;
import com.pg.pgp.model.PlaygroundApplyRepository;
import com.pg.pgp.model.PlaygroundRepository;
import com.pg.programmerground.service.OAuthUserService;
import com.pg.programmerground.service.PlaygroundService;
import org.junit.jupiter.api.*;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("jeawoo-local")     //테스트시 profile을 자기껄로 설정
public class PlaygroundServiceTest {
    private static final Long GITHUB_ID = 1234L;
    private static final Long GITHUB_ID2 = 12345L;
    TestJsonPackage dtoList;
    @Autowired
    private OAuthUserService oAuthUserService;
    @Autowired
    private PlaygroundService playgroundService;
    @Autowired
    private PlaygroundRepository playgroundRepository;
    @Autowired
    private PlaygroundApplyRepository playgroundApplyRepository;
    //private MockMvc mvc;
    @Autowired
    TestUserManagement management;


    @BeforeEach
    void authenticationSetUp() {
        //Github 로그인을 한번 시도하고
        UserDetails userDetails = oAuthUserService.loadUserByOAuthId(GITHUB_ID);
        Authentication authentication = new JwtAuthenticationToken(userDetails, "토큰 값 필요없음", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @BeforeAll
    void dataSetUp() throws IOException {
        dtoList = TestJsonPackage.of();
        management.saveTestUser();
    }

    @AfterAll
    void deleteTestUser() {
        management.deleteTestUser();
    }

    @Test
    @Transactional
    void Playground_생성() throws Exception {
        //given
        PlaygroundApi playgroundApi = dtoList.createPlayground;
        //when
        Long playgroundId = playgroundService.createPlayground(null, playgroundApi);
        Playground playground = playgroundRepository.findById(playgroundId).orElseThrow();
        //then
        assertEquals(playgroundApi.getMaxUserNum(), playground.getMaxMemberCount(), "최대 인원이 다름");
        //...등등
    }

    @Test
    void Playground_Position_합_예외() {
        //given
        PlaygroundApi playgroundApi = dtoList.positionSum;
        //when then
        assertThrows(WrongRequestException.class, () -> {
            Long playgroundId = playgroundService.createPlayground(null, playgroundApi);
        });
    }

    @Test
    void Playground_Position_Enum_예외() {
        PlaygroundApi playgroundApi = dtoList.positionEnum;
        assertThrows(IllegalArgumentException.class, () -> {
            Long playgroundId = playgroundService.createPlayground(null, playgroundApi);
        });
    }

    @Test
    void Playground_Leader_Position_존재안함_예외() {
        PlaygroundApi playgroundApi = dtoList.leaderPosition;
        assertThrows(NoSuchElementException.class, () -> {
            Long playgroundId = playgroundService.createPlayground(null, playgroundApi);
        });
    }

    @Test
    @Transactional
    void Playground_Member_신청() throws Exception {
        //playground 생성
        PlaygroundApi playgroundApi = dtoList.applyPosition;
        Long playgroundId = playgroundService.createPlayground(null, playgroundApi);

        //playground position 가져오기
        Playground playground = playgroundRepository.findById(playgroundId).orElseThrow();
        Long positionId = playground.getPlaygroundPositionList().get(0).getId();
        ApplyPlaygroundApi applyPlaygroundApi = new ApplyPlaygroundApi();
        Whitebox.setInternalState(applyPlaygroundApi, "positionId", positionId);

        //유저 전환
        UserDetails userDetails = oAuthUserService.loadUserByOAuthId(GITHUB_ID2);
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(userDetails, "토큰 값 필요없음", userDetails.getAuthorities()));
        //when
        Long applyId = playgroundService.applyPlayground(playgroundId, applyPlaygroundApi);
        PlaygroundApply playgroundApply = playgroundApplyRepository.findById(applyId).orElseThrow();

        //then
        assertEquals(playground.getId(), playgroundApply.getPlayground().getId());
    }

    @Test
    @Transactional
    void Playground_Member_신청_취소() throws Exception {
        //given
        //playground 생성
        PlaygroundApi playgroundApi = dtoList.applyPosition;
        Long playgroundId = playgroundService.createPlayground(null, playgroundApi);

        //playground position 가져오기
        Playground playground = playgroundRepository.findById(playgroundId).orElseThrow();
        Long positionId = playground.getPlaygroundPositionList().get(0).getId();
        ApplyPlaygroundApi applyPlaygroundApi = new ApplyPlaygroundApi();
        Whitebox.setInternalState(applyPlaygroundApi, "positionId", positionId);

        //유저 전환
        UserDetails userDetails = oAuthUserService.loadUserByOAuthId(GITHUB_ID2);
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(userDetails, "토큰 값 필요없음", userDetails.getAuthorities()));
        Long applyId = playgroundService.applyPlayground(playgroundId, applyPlaygroundApi);

        //신청 내역 가져오기
        PlaygroundApply playgroundApply = playgroundApplyRepository.findById(applyId).orElseThrow();

        //when
        playgroundService.cancelPlayground(playgroundApply.getId());

        //then
        assertEquals(playgroundApply.getApplyStatus(), ApplyStatus.CANCEL);

    }

    @Test
    @Transactional
    void Playground_Member_신청_취소된_신청_재취소_예외() throws Exception {
        //given
        //playground 생성
        PlaygroundApi playgroundApi = dtoList.applyPosition;
        Long playgroundId = playgroundService.createPlayground(null, playgroundApi);

        //playground position 가져오기
        Playground playground = playgroundRepository.findById(playgroundId).orElseThrow();
        Long positionId = playground.getPlaygroundPositionList().get(0).getId();
        ApplyPlaygroundApi applyPlaygroundApi = new ApplyPlaygroundApi();
        Whitebox.setInternalState(applyPlaygroundApi, "positionId", positionId);

        //유저 전환
        UserDetails userDetails = oAuthUserService.loadUserByOAuthId(GITHUB_ID2);
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(userDetails, "토큰 값 필요없음", userDetails.getAuthorities()));
        Long applyId = playgroundService.applyPlayground(playgroundId, applyPlaygroundApi);

        //신청 내역 가져오기
        PlaygroundApply playgroundApply = playgroundApplyRepository.findById(applyId).orElseThrow();
        playgroundService.cancelPlayground(playgroundApply.getId());

        //then
        assertThrows(NoSuchElementException.class, () -> {
            //when
            playgroundService.cancelPlayground(playgroundApply.getId());
        });
    }

    @Test
    @Transactional
    void Playground_삭제() throws Exception {
        //playground 생성
        PlaygroundApi playgroundApi = dtoList.applyPosition;
        Long playgroundId = playgroundService.createPlayground(null, playgroundApi);

        //when
        //playground 삭제
        playgroundService.removePlayground(playgroundId);
        Playground playground = playgroundRepository.findById(playgroundId).orElseThrow();

        //then
        assertEquals(PlaygroundStatus.REMOVE, playground.getStatusFlag());
    }

    @Test
    @Transactional
    void Playground_Member_같은_유저_신청_예외() throws Exception {
        //playground 생성
        PlaygroundApi playgroundApi = dtoList.applyPosition;
        Long playgroundId = playgroundService.createPlayground(null, playgroundApi);

        //playground position 가져오기
        Playground playground = playgroundRepository.findById(playgroundId).orElseThrow();
        Long positionId = playground.getPlaygroundPositionList().get(0).getId();
        ApplyPlaygroundApi applyPlaygroundApi = new ApplyPlaygroundApi();
        Whitebox.setInternalState(applyPlaygroundApi, "positionId", positionId);

        //리더가 자기 playground에 참가 신청
        assertThrows(WrongRequestException.class, () -> {
            Long id = playgroundService.applyPlayground(playgroundId, applyPlaygroundApi);
        });
    }

    /**
     * 이미지 확장자 예외처리 테스트
     * PNG, JPG만 업로드 가능
     */
    @Test
    void Playground_Img_Upload_확장자_예외() throws Exception {
        ClassPathResource resource = new ClassPathResource("test-file/testpdf.pdf");
        File files = new File(resource.getURI());
        InputStream input = new FileInputStream(files);
        MockMultipartFile file = new MockMultipartFile("test", "testpdf.pdf", "application/pdf", input);
        PlaygroundApi playgroundApi = dtoList.createPlayground;

        assertThrows(FileExtractException.class, () -> playgroundService.createPlayground(file,playgroundApi));
    }
}
