package com.pg.programmerground.model;

import com.pg.programmerground.domain.PlaygroundApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaygroundApplyRepository extends JpaRepository<PlaygroundApply, Long> {
    @Query("SELECT pa, pp, p " +
            "FROM PlaygroundApply pa " +
                "JOIN pa.playground p " +
                "JOIN pa.playgroundPosition pp " +
            "WHERE p.leader.id = :leaderUserId " +
                "AND pa.applyYn = 'WAIT'")
    List<PlaygroundApply> findPlaygroundApplyByLeader(@Param("leaderUserId")Long leaderUserId);
}
