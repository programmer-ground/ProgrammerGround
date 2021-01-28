package com.pg.programmerground.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GithubTotalDto {
    private String owner;

    @JsonProperty("total_repository")
    private Long totalRepo;

    @JsonProperty("total_commit")
    private Integer totalCommit;

    @JsonProperty("total_pull_request")
    private Integer totalPullRequest;

    @JsonProperty("total_star")
    private Long totalStar;

    @JsonProperty("total_language")
    private List<String> totalLanguage;
    //Follwer, Following...
}
