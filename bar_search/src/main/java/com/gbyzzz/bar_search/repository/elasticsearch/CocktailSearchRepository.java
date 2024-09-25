package com.gbyzzz.bar_search.repository.elasticsearch;

import com.gbyzzz.bar_search.entity.Cocktail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailSearchRepository extends ElasticsearchRepository<Cocktail, Long> {
    @Query("""
            {
               "bool": {
                 "should": [
                   {
                     "multi_match": {
                       "query": "?0",
                       "fields": ["cocktailName^4", "cocktailRecipe^3", "ingredients.ingredientName^3", "cocktailAuthor"],
                       "type": "best_fields",
                       "operator": "or",
                       "fuzziness": "AUTO"
                     }
                   },
                   {
                     "wildcard": {
                       "cocktailName": {
                         "value": "?0*",
                         "boost": 3
                       }
                     }
                   },
                   {
                     "prefix": {
                       "cocktailName": {
                         "value": "?0",
                         "boost": 3
                       }
                     }
                   },
                   {
                     "wildcard": {
                       "cocktailRecipe": {
                         "value": "?0*",
                         "boost": 2
                       }
                     }
                   },
                   {
                     "prefix": {
                       "cocktailRecipe": {
                         "value": "?0",
                         "boost": 2
                       }
                     }
                   },
                   {
                     "wildcard": {
                       "ingredients.ingredientName": {
                         "value": "?0*",
                         "boost": 2
                       }
                     }
                   },
                   {
                     "prefix": {
                       "ingredients.ingredientName": {
                         "value": "?0",
                         "boost": 2
                       }
                     }
                   },
                   {
                     "wildcard": {
                       "cocktailAuthor": {
                         "value": "?0*",
                         "boost": 1
                       }
                     }
                   },
                   {
                     "prefix": {
                       "cocktailAuthor": {
                         "value": "?0",
                         "boost": 1
                       }
                     }
                   }
                 ],
                 "minimum_should_match": 1
               }
             }
            """)
    Page<Cocktail> searchByQuery(String query, Pageable pageable);
}
