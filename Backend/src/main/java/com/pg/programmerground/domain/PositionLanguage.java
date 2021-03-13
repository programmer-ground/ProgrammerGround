package com.pg.programmerground.domain;

import com.pg.programmerground.dto.playground.api_req.PositionLanguageApi;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
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

    @Builder
    private PositionLanguage(String languageName) {
        this.languageName = languageName;
    }

    public void setPlaygroundPosition(PlaygroundPosition playgroundPosition) {
        this.playgroundPosition = playgroundPosition;
    }

    /**
     * 각 Position의 Language객체 List 추출
     */
    public static List<PositionLanguage> createPositionLanguage(List<PositionLanguageApi> languages) {
        return languages.stream()
                .map(positionLanguageApi ->
                        PositionLanguage.builder().languageName(positionLanguageApi.getLanguageName())
                                .build())
                .collect(Collectors.toList());
    }
}
