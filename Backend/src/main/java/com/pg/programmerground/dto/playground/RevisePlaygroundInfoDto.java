package com.pg.programmerground.dto.playground;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * playground 수정 dto
 */
@Getter
@AllArgsConstructor
@Builder
public class RevisePlaygroundInfoDto{
    private final String title;
    private final String description;
    private final String leader;

}
