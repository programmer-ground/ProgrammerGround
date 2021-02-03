package com.pg.programmerground.model;

import com.pg.programmerground.domain.OAuthUser;
import com.pg.programmerground.domain.github.Oauth2AuthorizedClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthUserRepository extends JpaRepository<OAuthUser, Long> {
    OAuthUser findByOauth2AuthorizedClient(Oauth2AuthorizedClient oauth2AuthorizedClient);
}
