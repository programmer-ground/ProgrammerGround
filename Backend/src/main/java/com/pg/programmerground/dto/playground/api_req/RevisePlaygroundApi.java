package com.pg.programmerground.dto.playground.api_req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * playground 수정 dto
 */
@Getter
@AllArgsConstructor
@Builder
public class RevisePlaygroundApi {
    private final String title;
    private final String description;
    private final String leader;

}
