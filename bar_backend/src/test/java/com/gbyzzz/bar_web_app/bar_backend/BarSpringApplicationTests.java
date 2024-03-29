package com.gbyzzz.bar_web_app.bar_backend;

import com.gbyzzz.bar_web_app.bar_backend.initializer.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("tests")
@ContextConfiguration(initializers = {
        Postgres.Initializer.class
})
public abstract class BarSpringApplicationTests {

    @BeforeAll
    static void init(){
        Postgres.container.start();
    }
}
