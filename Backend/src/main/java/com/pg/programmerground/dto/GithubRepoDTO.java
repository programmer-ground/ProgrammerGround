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
public class GithubRepoDTO {
    private Long id;
    private String name;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("html_url")
    private String htmlUrl;

    private String description;

    @JsonProperty("languages_url")
    private String languagesUrl;

    @JsonProperty("commits_url")
    private String commitsUrl;

    private String language;
}
