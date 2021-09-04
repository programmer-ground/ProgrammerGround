package com.pg.programmerground.domain;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import com.pg.programmerground.domain.enumerated.PlaygroundStatus;
import com.pg.programmerground.dto.UploadImg;
import com.pg.programmerground.dto.playground.api_req.PlaygroundApi;
import com.pg.programmerground.exception.FullMemberException;
import com.pg.programmerground.exception.IncorrectUserException;
import com.pg.programmerground.exception.WrongRequestException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Entity(name = "PLAYGROUND")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playground extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYGROUND_ID")
    private Long id;

    @Column(name = "MAX_MEMBER_COUNT")
    private int maxMemberCount;

    @Column(name = "CURRENT_MEMBER_COUNT")
    private int currentMemberCount = 1;     //Playground를 생성하면 Leader가 포함되기 때문에 기본값 1

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MAIN_IMG_NAME")
    private String mainImgName;

    @Column(name = "MAIN_IMG_UPLOAD_NAME")
    private String mainImgUploadName;

    @OneToMany(mappedBy = "playground", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PlaygroundApply> applyPlaygrounds = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEADER_USER_ID")
    private OAuthUser leader;

    @OneToMany(mappedBy = "playground", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaygroundPosition> playgroundPositionList = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATUS_FLAG")
    private PlaygroundStatus statusFlag = PlaygroundStatus.ACTIVE;

    @Builder
    private Playground(int maxMemberCount, String title, String description, String mainImgName, String mainImgUploadName) {
        this.maxMemberCount = maxMemberCount;
        this.title = title;
        this.description = description;
        this.mainImgName = mainImgName;
        this.mainImgUploadName = mainImgUploadName;
    }

    /**
     * playground 객체 생성
     * playground 정보 builer로 생성
     * playground 객체에 oAuthUser가 등록된 연관 객체(oAuthUserPlayground) 등록
     */
    public static Playground createPlayground(PlaygroundApi playgroundApi, UploadImg uploadMainImg, OAuthUser leader, List<PlaygroundPosition> playgroundPositionList) {
        Playground.checkMaxMemberNumWithPosition(playgroundApi, playgroundPositionList);
        Playground playground = Playground.builder()
                .title(playgroundApi.getTitle())
                .description(playgroundApi.getDescription())
                .maxMemberCount(playgroundApi.getMaxUserNum())
                .mainImgName(uploadMainImg.getOriginalFileName())
                .mainImgUploadName(uploadMainImg.getStoreFileName())
                .build();
        playgroundPositionList.forEach(playground::addPosition);
        playground.addLeader(leader);
        return playground;
    }

    /**
     * Position ID로 탐색
     */
    public PlaygroundPosition searchPosition(Long positionId) {
        return this.playgroundPositionList.stream()
                .filter(playgroundPosition -> playgroundPosition.getId().equals(positionId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("포지션이 존재하지 않음"));
    }

    /**
     * 이미 해당 Playground에 참여중이거나 수락 대기중인 유저 체크
     * !!거절 당했던 유저는 다시 신청 가능
     */
    public boolean checkAlreadyMember(OAuthUser user) {
        return applyPlaygrounds.stream().anyMatch(playgroundApply -> playgroundApply.isAlreadyMember(user));
    }

    /**
     * Playground Member수 증가
     */
    public void increaseMemberNum() {
        if(isFullMember()) {
            throw new FullMemberException("해당 Playground 멤버가 가득참");
        }
        currentMemberCount++;
    }

    /**
     * 해당 playground의 leader인지 확인
     */
    public void isLeaderUser(OAuthUser user) {
        if(user != this.leader) {
            throw new IncorrectUserException("해당 playground 리더가 아님");
        }
    }

    public boolean isRemovePlayground() {
        return this.statusFlag == PlaygroundStatus.REMOVE;
    }

    /**
     * playground 삭제처리
     * 실제 삭제가 아닌 flag remove 설정
     */
    public void removePlayground() {
        this.statusFlag = PlaygroundStatus.REMOVE;
    }

    /**
     * playground의 전체 인원수와 각 position의 인원수의 합이 같은지 체크
     */
    private static void checkMaxMemberNumWithPosition(PlaygroundApi playgroundApi, List<PlaygroundPosition> playgroundPositionList) {
        if(playgroundPositionList.stream().mapToInt(PlaygroundPosition::getMaxPositionNum).sum() != playgroundApi.getMaxUserNum()) {
            throw new WrongRequestException("playground 인원과 position 합산 인원이 다름");
        }
    }

    /**
     * Playground의 Position 등록
     */
    private void addPosition(PlaygroundPosition position) {
        this.playgroundPositionList.add(position);
        position.setPlayground(this);
    }

    /**
     * Playground의 Leader 설정
     */
    private void addLeader(OAuthUser leader) {
        this.leader = leader;
        leader.getLeaderPlaygrounds().add(this);
    }

    /**
     * Playground 멤버 가득찼는지 확인
     */
    private boolean isFullMember() {
        return maxMemberCount <= currentMemberCount;
    }
}
