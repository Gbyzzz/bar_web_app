package com.gbyzzz.bar_search.service.impl;

import com.gbyzzz.bar_search.entity.Cocktail;
import com.gbyzzz.bar_search.entity.pagination.Pagination;
import com.gbyzzz.bar_search.entity.pagination.RestPage;
import com.gbyzzz.bar_search.repository.jpa.CocktailRepository;
import com.gbyzzz.bar_search.repository.elasticsearch.CocktailSearchRepository;
import com.gbyzzz.bar_search.service.CocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CocktailServiceImpl implements CocktailService {

    private final CocktailRepository cocktailRepository;
    private final CocktailSearchRepository cocktailSearchRepository;

    @Override
    public RestPage searchCocktails(String query, Pagination pagination) {
        Pageable pageable = pagination != null ? PageRequest.of(pagination.getPageNumber(), pagination.getPageSize()):
        PageRequest.of(0, 6);
        Page<Cocktail> cocktails = cocktailSearchRepository.searchByQuery(query.toLowerCase(), pageable);

        return new RestPage(cocktails);
    }

    @KafkaListener(
            topics = "${application.kafka.topic.to_save_to_search}",
            groupId = "groupId",
            containerFactory = "kafkaListenerContainerFactory")
    public void addToSearchDB(Long id) {
        Cocktail cocktail = cocktailRepository.findById(id).get();
        cocktailSearchRepository.save(cocktail);
    }
}
