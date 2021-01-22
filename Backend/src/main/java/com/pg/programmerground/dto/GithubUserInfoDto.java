package com.pg.programmerground.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 예시 데이터
 * 만약 필요한 데이터가 있으면 필드 추가하면 됨
 {
 "login": "CJW23",
 "id": 32676275,
 "node_id": "MDQ6VXNlcjMyNjc2Mjc1",
 "avatar_url": "https://avatars1.githubusercontent.com/u/32676275?v=4",
 "gravatar_id": "",
 "url": "https://api.github.com/users/CJW23",
 "html_url": "https://github.com/CJW23",
 "followers_url": "https://api.github.com/users/CJW23/followers",
 "following_url": "https://api.github.com/users/CJW23/following{/other_user}",
 "gists_url": "https://api.github.com/users/CJW23/gists{/gist_id}",
 "starred_url": "https://api.github.com/users/CJW23/starred{/owner}{/repo}",
 "subscriptions_url": "https://api.github.com/users/CJW23/subscriptions",
 "organizations_url": "https://api.github.com/users/CJW23/orgs",
 "repos_url": "https://api.github.com/users/CJW23/repos",
 "events_url": "https://api.github.com/users/CJW23/events{/privacy}",
 "received_events_url": "https://api.github.com/users/CJW23/received_events",
 "type": "User",
 "site_admin": false,
 "name": "Choi Jae Woo",
 "company": "SaraminHR",
 "blog": "https://cjw-awdsd.tistory.com/",
 "location": "SouthKorea",
 "email": null,
 "hireable": null,
 "bio": "cjw7242@gmail.com\r\n",
 "twitter_username": null,
 "public_repos": 33,
 "public_gists": 0,
 "followers": 7,
 "following": 8,
 "created_at": "2017-10-10T13:44:52Z",
 "updated_at": "2021-01-14T13:16:11Z",
 "private_gists": 0,
 "total_private_repos": 0,
 "owned_private_repos": 0,
 "disk_usage": 1676520,
 "collaborators": 0,
 "two_factor_authentication": false,
 "plan": {
 "name": "free",
 "space": 976562499,
 "collaborators": 0,
 "private_repos": 10000
 }
 }
 */
@Getter
@Setter
public class  GithubUserInfoDto {

    private String login;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    private String url;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("public_repos")
    private Long publicRepos;

    private int followers;

    private int following;
}

