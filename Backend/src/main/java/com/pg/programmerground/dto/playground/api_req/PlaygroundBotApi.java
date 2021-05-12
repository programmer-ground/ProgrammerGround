package com.pg.programmerground.dto.playground.api_req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PlaygroundBotApi {
  private String name;
  private String description;
  @JsonProperty("homepage")
  private String homePage;
  @JsonProperty("private")
  private boolean isPrivate;
}
