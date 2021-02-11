package com.pg.programmerground.model;

import com.pg.programmerground.domain.Playground;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayGroundRepostiroy extends JpaRepository<Playground, Long> {

}
