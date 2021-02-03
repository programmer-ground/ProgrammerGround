package com.pg.programmerground.model;

import com.pg.programmerground.domain.github.Oauth2AuthorizedClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Oauth2AuthorizedClientRepository extends JpaRepository<Oauth2AuthorizedClient, Long> {
    Optional<Oauth2AuthorizedClient> findById(Long id);
}
