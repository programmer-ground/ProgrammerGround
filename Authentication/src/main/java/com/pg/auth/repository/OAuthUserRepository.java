package com.pg.auth.repository;

import com.pg.auth.domain.OAuthUser;
import com.pg.auth.domain.github.Oauth2AuthorizedClient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthUserRepository extends JpaRepository<OAuthUser, Long> {
    OAuthUser findByOauth2AuthorizedClient(Oauth2AuthorizedClient oauth2AuthorizedClient);
    OAuthUser findByCodeAndOauth2AuthorizedClient(String code, Oauth2AuthorizedClient oauth2AuthorizedClient);
}
