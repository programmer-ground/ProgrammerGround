package com.pg.programmerground.dto.user.api_req;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class ReviseUserApi {
    @Length(max = 5)
    @NotBlank
    private String userName;
}
