package com.pg.programmerground.model;

import com.pg.programmerground.domain.OAuthUser;
import com.pg.programmerground.domain.Playground;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaygroundRepository extends JpaRepository<Playground, Long> {
    Optional<Playground> findPlaygroundByIdAndLeader(Long id, OAuthUser leader);
}
