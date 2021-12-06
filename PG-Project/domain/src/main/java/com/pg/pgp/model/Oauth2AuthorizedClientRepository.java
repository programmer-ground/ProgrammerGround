package com.pg.pgp.model;

import com.pg.pgp.domain.github.Oauth2AuthorizedClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Oauth2AuthorizedClientRepository extends JpaRepository<Oauth2AuthorizedClient, Long> {
    Optional<Oauth2AuthorizedClient> findById(Long id);
}
