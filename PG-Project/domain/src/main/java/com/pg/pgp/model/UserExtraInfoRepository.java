package com.pg.pgp.model;


import com.pg.pgp.domain.UserExtraInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExtraInfoRepository extends JpaRepository<UserExtraInfo, Long> {

}
