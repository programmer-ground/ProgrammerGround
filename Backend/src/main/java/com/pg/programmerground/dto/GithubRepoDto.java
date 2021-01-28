package com.pg.programmerground.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Github 유저의 각 Repository 정보를 담을 dto
 * 추후 필요한 정보 추가
 */
@Getter
@Setter
public class GithubRepoDto {
    @JsonProperty(value = "stargazers_count")
    private String stargazersCount;
    private String language;
}
