package com.pg.programmerground.dto.user.api_req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ReviseUserApi {
    @NotNull
    @Min(value = 1, message = "최소 1글자")
    @Max(value = 5, message = "최대 5글자")
    private String userName;

}
