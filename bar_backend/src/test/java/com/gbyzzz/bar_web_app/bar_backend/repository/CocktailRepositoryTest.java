package com.gbyzzz.bar_web_app.bar_backend.repository;

import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.initializer.PostgreSQLContainerInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CocktailRepositoryTest implements PostgreSQLContainerInitializer {

    @Autowired
    CocktailRepository repository;

    @Test
    void findAll() {

    }

    @Test
    void findTop3ByOrderByCocktailIdDesc() {
        repository.findTop3ByOrderByCocktailIdDesc();
    }

    @Test
    void updateRating() {
    }
}