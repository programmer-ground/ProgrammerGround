package com.pg.programmerground;

import com.pg.programmerground.auth.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest

class ProgrammergroundApplicationTests {
    @Autowired JwtTokenProvider provider;
    @Test
    void contextLoads() throws InterruptedException {
        List<String> role = new ArrayList<>();
        role.add("ROLE_USER");
        String token = provider.createToken("test", 1L, role);
        Thread.sleep(1000);
        assertEquals(1, provider.getOAuthId(token));
        assertEquals("test", provider.getOAuthAccessToken(token));
    }
}
