package com.pg.pgp.model;

import com.pg.pgp.domain.OAuthUser;
import com.pg.pgp.domain.github.Oauth2AuthorizedClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthUserRepository extends JpaRepository<OAuthUser, Long> {
    OAuthUser findByOauth2AuthorizedClient(Oauth2AuthorizedClient oauth2AuthorizedClient);
    OAuthUser findByCodeAndOauth2AuthorizedClient(String code, Oauth2AuthorizedClient oauth2AuthorizedClient);
}
