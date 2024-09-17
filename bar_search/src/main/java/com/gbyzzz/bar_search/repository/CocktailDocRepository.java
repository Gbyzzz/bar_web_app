package com.gbyzzz.bar_search.repository;

import com.gbyzzz.bar_search.entity.Cocktail;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CocktailDocRepository extends ElasticsearchRepository<Cocktail, Long> {
}
