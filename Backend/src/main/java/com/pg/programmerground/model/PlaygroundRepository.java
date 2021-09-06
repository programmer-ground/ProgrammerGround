package com.pg.programmerground.model;

import com.pg.programmerground.domain.OAuthUser;
import com.pg.programmerground.domain.Playground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaygroundRepository extends JpaRepository<Playground, Long> {
    Optional<Playground> findPlaygroundByIdAndLeader(Long id, OAuthUser leader);
    @Query("SELECT p " +
            "FROM PLAYGROUND p " +
            "WHERE p.statusFlag = 'ACTIVE'")
    List<Playground> findPlaygroundsByStatsActive();
}
