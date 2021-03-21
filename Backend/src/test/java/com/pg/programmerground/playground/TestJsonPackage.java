package com.pg.programmerground.playground;


import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pg.programmerground.domain.Playground;
import com.pg.programmerground.dto.playground.api_req.PlaygroundApi;

public class TestJsonPackage {
    private static String JSON_FILE_PATH = "json/playground.json";
    @JsonProperty("create_playground")
    PlaygroundApi createPlayground;

    public static TestJsonPackage of() throws IOException {
        ClassPathResource resource = new ClassPathResource(JSON_FILE_PATH);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(resource.getInputStream(), TestJsonPackage.class);
    }
}
