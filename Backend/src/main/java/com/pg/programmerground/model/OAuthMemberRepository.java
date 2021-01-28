package com.pg.programmerground.model;

import com.pg.programmerground.domain.OAuthMember;
import com.pg.programmerground.domain.Oauth2AuthorizedClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthMemberRepository extends JpaRepository<OAuthMember, Long> {
    OAuthMember findByOauth2AuthorizedClient(Oauth2AuthorizedClient oauth2AuthorizedClient);
}
