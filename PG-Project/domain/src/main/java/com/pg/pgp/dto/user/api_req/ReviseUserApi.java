package com.pg.pgp.dto.user.api_req;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ReviseUserApi {
    @Length(max = 5)
    @NotBlank
    @NotNull
    private String userName;
}
