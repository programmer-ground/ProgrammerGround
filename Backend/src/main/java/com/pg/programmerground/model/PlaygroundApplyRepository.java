package com.pg.programmerground.model;

import com.pg.programmerground.domain.PlaygroundApply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaygroundApplyRepository extends JpaRepository<PlaygroundApply, Long> {
}
