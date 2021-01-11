package com.pg.programmerground;

import com.pg.programmerground.jwt.JwtTokenProvider;
import com.pg.programmerground.util.RestApiManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest

class ProgrammergroundApplicationTests {
    @Autowired JwtTokenProvider provider;
    @Test
    void contextLoads() {
        List<String> role = new ArrayList<>();
        role.add("ROLE_USER");
        String token = provider.createToken("test", 1L, role);
        assertEquals(1, provider.getOAuthId(token));
        assertEquals("test", provider.getOAuthAccessToken(token));
    }

}
