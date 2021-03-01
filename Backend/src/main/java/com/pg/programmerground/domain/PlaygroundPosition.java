package com.pg.programmerground.domain;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "PLAYGROUND_POSITION")
public class PlaygroundPosition extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "PLAYGROUND_POSITION_ID")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "POSITION")
    private Position position;

    @Column(name = "MAX_POSITION_NUM")
    private int maxUserNum;

    @Column(name = "CURRENT_POSITION_NUM")
    private int currentUserNum;

    @OneToOne(mappedBy = "playgroundPosition")
    private PlaygroundApply playgroundApply;

    @OneToOne
    @JoinColumn(name = "OAUTH_USER_ID")
    private OAuthUser oAuthUser;
}
