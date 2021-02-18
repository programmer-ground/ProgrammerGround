package com.pg.programmerground.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.pg.programmerground.domain.github.Oauth2AuthorizedClient;
import com.pg.programmerground.exception.UserNotFoundException;
import com.pg.programmerground.model.OAuthUserRepository;
import com.pg.programmerground.model.PlaygroundRepository;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("Playground 단위 테스트")
class PlaygroundTest {

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

      OAuthUser oAuthUser = oAuthUserRepository.findById(1L).orElseThrow(UserNotFoundException::new);
      
      //when
      OAuthUserPlayground userPlayground = OAuthUserPlayground.createOAuthUserPlayground(oAuthUser);
      Playground playground = Playground.createPlayground(userPlayground);

      Playground getPlayground = playgroundRepository.save(playground);
      
      //then
      assertAll(
          () -> assertEquals(oAuthUser.getUserName(), getPlayground.getLeader().getUserName())
      );
  }
}