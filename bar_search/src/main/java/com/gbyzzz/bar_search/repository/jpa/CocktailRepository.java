package com.gbyzzz.bar_search.repository.jpa;

import com.gbyzzz.bar_search.entity.Cocktail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends CrudRepository<Cocktail, Long> {
}
