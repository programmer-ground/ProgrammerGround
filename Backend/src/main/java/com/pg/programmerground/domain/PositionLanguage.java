package com.pg.programmerground.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "POSITION_LANGUAGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PositionLanguage {
    @Id
    @Column(name = "POSITION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LANGUAGE_NAME")
    private String languageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYGROUND_POSITION_ID")
    private PlaygroundPosition playgroundPosition;
}
