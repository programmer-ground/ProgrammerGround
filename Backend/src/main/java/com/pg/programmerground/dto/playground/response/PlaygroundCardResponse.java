package com.pg.programmerground.dto.playground.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pg.programmerground.domain.Playground;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 메인 페이지에 노출될 playground 리스트에 각각 표현될 정보들
 * 제목
 * 설명
 * 리더
 * 추후 추가...
 */
@Getter
@NoArgsConstructor
public class PlaygroundCardResponse {
    @JsonProperty("playground_id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("max_member_num")
    private int maxMemberNum;
    @JsonProperty("current_member_num")
    private int currentMemberNum;
    @JsonProperty("leader_user_name")
    private String leaderUserName;
    @JsonProperty("position_list")
    private List<PlaygroundCardPositionResponse> positionList;
    @JsonProperty("logo_img_name")
    private String logoImgName;
    @JsonProperty("created_date")
    private LocalDateTime createdDate;

    @Builder
    public PlaygroundCardResponse(Long id, String title, int maxMemberNum, int currentMemberNum, String leaderUserName, List<PlaygroundCardPositionResponse> positionList, String logoImgName, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.maxMemberNum = maxMemberNum;
        this.currentMemberNum = currentMemberNum;
        this.leaderUserName = leaderUserName;
        this.positionList = positionList;
        this.logoImgName = logoImgName;
        this.createdDate = createdDate;
    }

    /**
     * playground 목록에 출력될 데이터
     */
    public static List<PlaygroundCardResponse> ofList(List<Playground> playgrounds) {
        return playgrounds.stream().map(playground -> PlaygroundCardResponse.builder()
                .id(playground.getId())
                .title(playground.getTitle())
                .maxMemberNum(playground.getMaxMemberCount())
                .currentMemberNum(playground.getCurrentMemberCount())
                .positionList(PlaygroundCardPositionResponse.ofList(playground.getPlaygroundPositionList()))
                .leaderUserName(playground.getLeader().getUserName())
                .logoImgName(playground.getMainImgUploadName())
                .createdDate(playground.getCreatedAt())
                .build()).collect(Collectors.toList());
    }
}
