package com.pg.programmerground.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "PLAYGROUND_POSITION")
public class PlaygroundPosition {
    @Id
    @GeneratedValue
    @Column(name = "PLAYGROUND_POSITION_ID")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "POSITION")
    private Position position;

    @Column(name = "MAX_USER_NUM")
    private int maxUserNum;

    @Column(name = "CURRENT_USER_NUM")
    private int currentUserNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYGROUND_ID")
    private Playground playground;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OAuthUserPlayground> oAuthUserPlaygroundList = new ArrayList<>();
}
