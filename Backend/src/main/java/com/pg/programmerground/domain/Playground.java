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

    @OneToMany(mappedBy = "members", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OAuthUser> members = new ArrayList<>();

    @Column(name = "MAX_MEMBER_COUNT")
    private int maxMemberCount;

    @Column(name = "CURRENT_MEMBER_COUNT")
    private int currentMemberCount;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;


    public void addUserPlayground(OAuthUserPlayground userPlayground) {
        userPlaygrounds.add(userPlayground);
        userPlayground.addPlayground(this);
    }

    public void addMember(OAuthUser member) {
        this.members.add(member);
    }

    public Boolean isLeader() {
        return this.members.size() == 0;
    }

    public void increaseCurrentMemberCount() {
        int current = this.maxMemberCount - currentMemberCount;

        if(current < 0 || current > this.maxMemberCount) {
            //TODO : Exception 처리해야함
        }
        this.currentMemberCount = currentMemberCount++;
    }
}
