package com.pg.auth.repository;

import com.pg.auth.domain.OAuthMember;
import com.pg.auth.domain.github.Oauth2AuthorizedClient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthMemberRepository extends JpaRepository<OAuthMember, Long> {
    OAuthMember findByOauth2AuthorizedClient(Oauth2AuthorizedClient oauth2AuthorizedClient);
    OAuthMember findByCodeAndOauth2AuthorizedClient(String code, Oauth2AuthorizedClient oauth2AuthorizedClient);
}
