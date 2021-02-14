package com.pg.programmerground.model;

import com.pg.programmerground.domain.Playground;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaygroundRepository extends JpaRepository<Playground, Long> {

}
