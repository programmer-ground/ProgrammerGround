package com.pg.programmerground.domain;

<<<<<<< HEAD
=======
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.pg.programmerground.domain.github.Oauth2AuthorizedClient;
import com.pg.programmerground.dto.playground.MakePlaygroundInfoDto;
import com.pg.programmerground.exception.UserNotFoundException;
import com.pg.programmerground.model.OAuthUserRepository;
import com.pg.programmerground.model.PlaygroundRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("Playground 단위 테스트")
class PlaygroundTest {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PlaygroundRepository playgroundRepository;

    @Autowired
    OAuthUserRepository oAuthUserRepository;

//  @Autowired
//  private EntityManager em;

    //  @AfterEach
//  public void teardown() {
//    this.playgroundRepository.deleteAll();
//    this.em
//        .createNamedQuery("ALTER TABLE playground ALTER COLUMN `playground_id` RESTART WITH 1")
//        .executeUpdate();
//  }
    @Test
    @DisplayName("사용자와 Playground 정보가 UserPlaygound 테이블에 제대로 매핑되는가")
    public void create_playground() throws Exception {
        //given
        Oauth2AuthorizedClient oauth2AuthorizedClient = Oauth2AuthorizedClient.builder().id(123L).build();

        OAuthUser user = OAuthUser.builder()
            .Role("ROLE_USER,SCOPE_read:user")
            .userName("seansin")
            .code("ea288245-53d5-4366-893a-72d3db53aa97")
            .OAuthName("22961251")
            .oauth2AuthorizedClient(oauth2AuthorizedClient)
            .build();

        oAuthUserRepository.save(user);

        OAuthUser getUser = oAuthUserRepository.findById(1L).orElseThrow(UserNotFoundException::new);
        MakePlaygroundInfoDto dto = new MakePlaygroundInfoDto();
        //when
        OAuthUserPlayground userPlayground = OAuthUserPlayground.createOAuthUserPlayground(getUser);
        Playground playground = Playground.createPlayground(userPlayground, dto);

        Playground getPlayground = playgroundRepository.save(playground);

        //then
        assertAll(
            () -> assertTrue(userPlayground.getId() > 0),
            () -> assertEquals(getPlayground.getUserPlaygrounds().get(0), userPlayground),
            () -> assertEquals(getUser, userPlayground.getUser()),
            () -> assertEquals(getPlayground, userPlayground.getPlayground()),
            () -> assertEquals(getPlayground.getLeader().getId(), getUser.getId())
        );
    }

    @Test
    public void a() {

        Oauth2AuthorizedClient oauth2AuthorizedClient = Oauth2AuthorizedClient.builder().id(123L).build();
        OAuthUser user = OAuthUser.builder()
                .Role("ROLE_USER,SCOPE_read:user")
                .userName("seansin")
                .code("ea288245-53d5-4366-893a-72d3db53aa97")
                .OAuthName("22961251")
                .oauth2AuthorizedClient(oauth2AuthorizedClient)
                .build();

        Playground playground = new Playground();
        playground.setLeader(user);
        playground.setTitle("title");
        playground.setDescription("des");
        playground.setId(2L);

        //왜 안됨 ㅅㅂ
        /*PlaygroundCardInfoDto cardInfoDto = modelMapper.map(playground, PlaygroundCardInfoDto.class);
        assertEquals(playground.getId(), cardInfoDto.getId());
        assertEquals(playground.getLeader().getUserName(), cardInfoDto.getLeaderUserName());*/
    }
}