package com.pg.programmerground.dto.playground;

import com.pg.programmerground.domain.Playground;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

public class PlaygroundCardInfoDto {
    private Long id;
    private String title;
    private String description;
    private String leaderUserName;

    @Builder
    public PlaygroundCardInfoDto(Long id, String title, String description, String leaderUserName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.leaderUserName = leaderUserName;
    }

    /**
     * playground 목록에 출력될 데이터
     */
    public static List<PlaygroundCardInfoDto> makePlaygroundCardList(List<Playground> playgrounds) {
        return playgrounds.stream().map(playground -> PlaygroundCardInfoDto.builder()
                .id(playground.getId())
                .title(playground.getTitle())
                .description(playground.getDescription())
                .leaderUserName(playground.getLeader().getUserName()).build()).collect(Collectors.toList());
    }
}
