package com.pg.programmerground.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pg.programmerground.domain.OAuthUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {
    @JsonProperty(value = "user_id")
    private final Long userId;

    @JsonProperty(value = "oauth_id")
    private final Long oauthId;

    @JsonProperty(value = "oauth_name")
    private final String oauthName;

    @JsonProperty(value = "commit_cnt")
    private final int commitCnt;

    @JsonProperty(value = "puul_request_cnt")
    private final int pullRequestCnt;

    @JsonProperty(value = "most_language")
    private final String mostLanguage;

    @JsonProperty(value = "repository_cnt")
    private final int repositoryCnt;

    @JsonProperty(value = "github_page")
    private final String githubPage;

    @JsonProperty(value = "profile_img")
    private final String profileImg;

    @JsonProperty(value = "user_playgrounds")
    private final List<UserPlaygroundResponse> userPlaygroundResponseList;

    public static UserResponse of(OAuthUser user) {
        return UserResponse.builder()
                .oauthId(user.getOauth2AuthorizedClient().getId())
                .userId(user.getId())
                .oauthName(user.getOAuthName())
                .commitCnt(user.getUserGithubInfo().getCommitCnt())
                .pullRequestCnt(user.getUserGithubInfo().getPullRequestCnt())
                .mostLanguage(user.getUserGithubInfo().getMostLanguage())
                .repositoryCnt(user.getUserGithubInfo().getRepositoryCnt())
                .githubPage(user.getUserGithubInfo().getGithubPage())
                .profileImg(user.getUserGithubInfo().getProfileImg())
                .userPlaygroundResponseList(UserPlaygroundResponse.ofList(user.getApplyPlaygrounds()))
                .build();
    }
}
