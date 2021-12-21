package com.pg.pgp.model;

import com.pg.pgp.domain.OAuthUser;
import com.pg.pgp.domain.PlaygroundApply;
import com.pg.pgp.domain.enumerated.ApplyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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

    Optional<PlaygroundApply> findPlaygroundApplyByIdAndUserAndApplyStatus(Long id, OAuthUser user, ApplyStatus wait);
}
