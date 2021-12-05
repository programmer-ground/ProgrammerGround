package com.pg.pgp.model;

import com.pg.pgp.domain.OAuthUser;
import com.pg.pgp.domain.Playground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaygroundRepository extends JpaRepository<Playground, Long> {
    Optional<Playground> findPlaygroundByIdAndLeader(Long id, OAuthUser leader);
    @Query("SELECT p " +
            "FROM PLAYGROUND p " +
            "WHERE p.statusFlag = 'ACTIVE'")
    List<Playground> findPlaygroundsByStatsActive();
}
