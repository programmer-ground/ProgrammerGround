package com.pg.programmerground;

import com.pg.programmerground.util.RestApiManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProgrammergroundApplicationTests {
    @Autowired
    RestApiManager manager;

    @Test
    void contextLoads() {
    }

}
