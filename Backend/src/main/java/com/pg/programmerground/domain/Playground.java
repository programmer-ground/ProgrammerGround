package com.pg.programmerground.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PLAYGROUND")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playground extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYGROUND_ID")
    private Long id;

    @OneToMany(mappedBy = "playground", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OAuthUserPlayground> userPlaygrounds = new ArrayList<>();

    @Column(name = "MAX_MEMBER_COUNT")
    private int maxMemberCount;

    @Column(name = "CURRENT_MEMBER_COUNT")
    private int currentMemberCount = 0;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToOne
    private OAuthUser leader;

    public static Playground createPlayground(OAuthUserPlayground userPlayground) {
        Playground playground = new Playground();
        addUserPlayground(playground, userPlayground);
        return playground;
    }

    public static void addUserPlayground(Playground playground, OAuthUserPlayground userPlayground) {
        playground.addUserPlayground(userPlayground);
    }

    public void addUserPlayground(OAuthUserPlayground userPlayground) {
        userPlaygrounds.add(userPlayground);
        userPlayground.addPlayground(this);
    }

    public void updateLeader(OAuthUser user) {
        leader = user;
    }

    public void increaseCurrentMemberCount() {
        int current = this.maxMemberCount - currentMemberCount;

        if(current < 0 || current > this.maxMemberCount) {
            //TODO : Exception 처리해야함
        }
        this.currentMemberCount = currentMemberCount++;
    }
}
