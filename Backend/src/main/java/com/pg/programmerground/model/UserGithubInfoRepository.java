package com.pg.programmerground.model;


import com.pg.programmerground.domain.github.UserGithubInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGithubInfoRepository extends JpaRepository<UserGithubInfo, Long> {
}
