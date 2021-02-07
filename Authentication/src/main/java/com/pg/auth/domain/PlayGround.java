package com.pg.auth.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "PLAYGROUND")
@Getter
@Setter
public class PlayGround {
    @Id
    @GeneratedValue
    private Long id;


}
