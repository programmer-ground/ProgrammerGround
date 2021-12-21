package com.pg.pgp.dto.github;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepositoryItem {
    @JsonProperty(value = "stargazers_count")
    private String stargazersCount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String language;
}
