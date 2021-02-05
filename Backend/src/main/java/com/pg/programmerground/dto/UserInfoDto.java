package com.pg.programmerground.dto;

import com.pg.programmerground.auth.MyUserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class UserInfoDto {
    private final String OAuthName;
    private final int commitCnt;
    private final int pullRequestCnt;
    private final String mostLanguage;
    private final int repositoryCnt;
    private final String githubPage;
    private final String profileImg;
    private final String role;

    public static UserInfoDto of(MyUserDetails userDetails) {
        return UserInfoDto.builder()
                .OAuthName(userDetails.getOAuthName())
                .role(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .commitCnt(userDetails.getCommitCnt())
                .pullRequestCnt(userDetails.getPullRequestCnt())
                .mostLanguage(userDetails.getMostLanguage())
                .repositoryCnt(userDetails.getRepositoryCnt())
                .githubPage(userDetails.getGithubPage())
                .profileImg(userDetails.getProfileImg())
                .build();
    }
}
