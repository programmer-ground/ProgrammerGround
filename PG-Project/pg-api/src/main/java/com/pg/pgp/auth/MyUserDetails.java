package com.pg.pgp.auth;

import com.pg.pgp.domain.OAuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
    private final Long id;

    private final Long oAuthId;

    private final String oAuthName;

    private final String userName;

    private final List<? extends GrantedAuthority> authorities;

    private final int commitCnt;

    private final int pullRequestCnt;

    private final String mostLanguage;

    private final int repositoryCnt;

    private final String githubPage;

    private final String profileImg;

    public MyUserDetails(OAuthUser oAuthUser) {
        this.id = oAuthUser.getId();
        this.oAuthId = oAuthUser.getOauth2AuthorizedClient().getId();
        this.oAuthName = oAuthUser.getOAuthName();
        this.userName = oAuthUser.getUserName();
        this.authorities = makeAuthority(oAuthUser.getRole());
        this.commitCnt = oAuthUser.getUserGithubInfo().getCommitCnt();
        this.pullRequestCnt = oAuthUser.getUserGithubInfo().getPullRequestCnt();
        this.mostLanguage = oAuthUser.getUserGithubInfo().getMostLanguage();
        this.repositoryCnt = oAuthUser.getUserGithubInfo().getRepositoryCnt();
        this.githubPage = oAuthUser.getUserGithubInfo().getGithubPage();
        this.profileImg = oAuthUser.getUserGithubInfo().getProfileImg();
    }

    public long getId() {
        return this.id;
    }

    public long getOAuthId() {
        return this.oAuthId;
    }

    public String getOAuthName() {
        return this.oAuthName;
    }

    public String getUserName() {
        return this.userName;
    }

    public int getCommitCnt() {
        return commitCnt;
    }

    public int getPullRequestCnt() {
        return pullRequestCnt;
    }

    public String getMostLanguage() {
        return mostLanguage;
    }

    public int getRepositoryCnt() {
        return repositoryCnt;
    }

    public String getGithubPage() {
        return githubPage;
    }

    public String getProfileImg() {
        return profileImg;
    }

    private List<? extends GrantedAuthority> makeAuthority(String role) {
        //"," 구분해서 리스트에 넣음
        return Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
}
