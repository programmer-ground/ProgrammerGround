package com.pg.programmerground.model;


import com.pg.programmerground.domain.MemberGithubInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberGithubInfoRepository extends JpaRepository<MemberGithubInfo, Long> {

}
