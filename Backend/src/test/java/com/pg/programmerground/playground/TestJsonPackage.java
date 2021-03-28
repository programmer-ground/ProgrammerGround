package com.pg.programmerground.playground;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pg.programmerground.dto.playground.api_req.PlaygroundApi;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 각 테스트 전용 DTO 클래스 선언
 * /resources/json/*.json 불러와서 mapper를 통해 값을 집어넣어 사용
 */
public class TestJsonPackage {
    private static String JSON_FILE_PATH = "json/playground.json";
    /**
     * playground 생성 성공 Json
     */
    @JsonProperty("create_playground")
    PlaygroundApi createPlayground;
    @JsonProperty("position_sum")
    PlaygroundApi positionSum;
    @JsonProperty("position_enum")
    PlaygroundApi positionEnum;
    @JsonProperty("leader_position")
    PlaygroundApi leaderPosition;
    @JsonProperty("apply_position")
    PlaygroundApi applyPosition;
    /**
     * Json To DTO
     */
    public static TestJsonPackage of() throws IOException {
        ClassPathResource resource = new ClassPathResource(JSON_FILE_PATH);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(resource.getInputStream(), TestJsonPackage.class);
    }
}
