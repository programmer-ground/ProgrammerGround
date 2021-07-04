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
import com.pg.programmerground.config.GithubBotConfig;
import com.pg.programmerground.util.GithubHttpUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaygroundService {
    private static final String PREFIX_GITHUB_ORG_REPO_API = "https://api.github.com/repos/programmer-ground";

    private final PlaygroundRepository playgroundRepository;
    private final PlaygroundApplyRepository playgroundApplyRepository;
    private final OAuthUserRepository oAuthUserRepository;
    private final PlaygroundMainImgStore playgroundMainImgStore;
    private final ModelMapper modelMapper;
    private final GithubBotConfig botConfig;

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
    public Long createPlayground(MultipartFile mainImg, PlaygroundApi playgroundInfo) throws Exception {
        //로그인 유저 가져오기
        OAuthUser leaderUser = oAuthUserRepository.findById(UserAuthenticationService.getUserId()).orElseThrow();
        //Playground 메인 이미지 업로드
        UploadImg uploadMainImg = playgroundMainImgStore.storeFile(mainImg);
        //Playground Position 객체 리스트 만들기
        List<PlaygroundPosition> playgroundPositionList = PlaygroundPosition.createPosition(playgroundInfo);
        //Playground 생성
        Playground playground = Playground.createPlayground(playgroundInfo, uploadMainImg, leaderUser, playgroundPositionList);
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

    // TODO : Playground 도메인 단에서 githubRepo 이름을 업데이트하게끔 처리할 예정
    public ResponseEntity<String> createPlaygroundGithubRepo(Long playgroundId, String repoTitle) {
        RestTemplate restTemplate = new RestTemplate();

        Playground playground = playgroundRepository.findById(playgroundId)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 Playground 입니다."));

        String apiUrl = botConfig.getOrgUrl();
        HttpHeaders headers = GithubHttpUtil.generateGithubApiHeader(botConfig.getToken());

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", repoTitle);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        return restTemplate.postForEntity(apiUrl + "/repos", request, String.class);
    }

    // TODO : 각 유저별 레포지트리 권한 관련 추가 예정
    public ResponseEntity<?> applyCollaboratorPlaygroundGithubRepo(Long playgroundId, String repoTitle) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = botConfig.getOrgRepoUrl();
        Playground playground = playgroundRepository.findById(playgroundId)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 Playground 입니다."));

        List<PlaygroundApply> applyPlaygrounds = playground.getApplyPlaygrounds();
        List<PlaygroundApply> temp = new ArrayList<>();

        for(PlaygroundApply apply : applyPlaygrounds) {
            if(apply.isAcceptApply()) {
                temp.add(apply);
            }
        }

        HttpEntity entity = new HttpEntity<>(GithubHttpUtil.generateGithubApiHeader(botConfig.getToken()));

        for(PlaygroundApply user : temp) {
            String collaboratorUrl = GithubHttpUtil
                .generateCollaboratorUrl(repoTitle, user.getUser().getUserName());

            ResponseEntity<String> exchange = restTemplate
                .exchange(apiUrl + collaboratorUrl, HttpMethod.PUT, entity, String.class);

            if(exchange.getStatusCode() != HttpStatus.CREATED || exchange.getStatusCode() != HttpStatus.NO_CONTENT) {
                throw new IllegalArgumentException("알수 없는 오류가 발생하였습니다.");
            }
        }

        return (ResponseEntity<?>) ResponseEntity.accepted();
    }
}
