package com.pg.pgp.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Github 유저의 각 Repository 정보를 담을 dto
 * 추후 필요한 정보 추가
 */
@Getter
@Setter
public class GithubRepoDto {
    @JsonProperty(value = "total_count")
    private Integer totalCount;

    @JsonProperty(value = "items")
    List<RepositoryItem> repositoryItems;
}
