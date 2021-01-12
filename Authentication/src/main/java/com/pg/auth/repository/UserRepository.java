package com.pg.auth.repository;

import com.pg.auth.entity.Oauth2AuthorizedClient;
import com.pg.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByOauth2AuthorizedClient(Oauth2AuthorizedClient oauth2AuthorizedClient);
}
