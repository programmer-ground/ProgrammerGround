package com.pg.programmerground.model;


import com.pg.programmerground.domain.UserExtraInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExtraInfoRepository extends JpaRepository<UserExtraInfo, Long> {

}
