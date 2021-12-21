package com.pg.pgp.dto.playground.api_req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * playground 수정 dto
 */
@Getter
@AllArgsConstructor
@Builder
public class RevisePlaygroundApi {
    @NotNull
    @Min(value = 20, message = "제목은 최대 20글자")
    private final String title;

    @NotNull
    @NotBlank
    private final String description;

    @NotNull
    private final String leader;

}
