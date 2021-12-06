package com.pg.pgp.model;


import com.pg.pgp.domain.github.UserGithubInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGithubInfoRepository extends JpaRepository<UserGithubInfo, Long> {
}
