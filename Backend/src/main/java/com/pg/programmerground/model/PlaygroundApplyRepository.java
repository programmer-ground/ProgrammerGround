package com.pg.programmerground.model;

import com.pg.programmerground.domain.PlaygroundApply;
import com.pg.programmerground.domain.enumerated.ApplyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaygroundApplyRepository extends JpaRepository<PlaygroundApply, Long> {

    /**
     * 자신이 리더인 Playground에 신청한 User 리스트
     */
    @Query("SELECT pa, pp, p " +
            "FROM PlaygroundApply pa " +
                "JOIN pa.playground p " +
                "JOIN pa.playgroundPosition pp " +
            "WHERE p.leader.id = :leaderUserId " +
                "AND pa.applyStatus = 'WAIT'")
    List<PlaygroundApply> findPlaygroundApplyByLeader(@Param("leaderUserId")Long leaderUserId);

    /**
     * Playground에 신청한 내역(WAIT, (ACCEPT, REJECT) 따로
     * 자기가 Leader인 Playground는 제외
     */
    @Query("SELECT pa, pp, p " +
            "FROM PlaygroundApply pa " +
                "JOIN pa.playground p " +
                "JOIN pa.playgroundPosition pp " +
            "WHERE pa.applyStatus IN :status " +
                "AND p.leader.id <> :userId " +
                "AND pa.user.id = :userId")
    List<PlaygroundApply> findPlaygroundApplyByOAuthUserAndStatus(@Param("userId")Long userId, @Param("status")List<ApplyStatus> status);
}
