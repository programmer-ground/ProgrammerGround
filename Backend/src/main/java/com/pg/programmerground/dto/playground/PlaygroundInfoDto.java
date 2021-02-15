package com.pg.programmerground.dto.playground;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class PlaygroundInfoDto {
    private String name;
    private String description;
    private Integer maxUserNum;
    private Integer currentUserNum;
}
