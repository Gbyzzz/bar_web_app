package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.pagination.Pagination;
import com.gbyzzz.bar_spring.repository.CocktailRepository;
import com.gbyzzz.bar_spring.service.CocktailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Anton Pinchuk
 */
@Service
public class CocktailServiceImpl implements CocktailService {


    private CocktailRepository cocktailRepository;

    public CocktailServiceImpl(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    @Override
    public List<Cocktail> findAll() {
        return cocktailRepository.findAll();
    }

    @Override
    public Cocktail findById(long id) throws Exception {
        Cocktail cocktail = null;
        Optional<Cocktail> optionalCocktail = cocktailRepository.findById(id);
        if(optionalCocktail.isPresent()){
            cocktail = optionalCocktail.get();
        } else {
            throw new Exception("No cocktail with id " + id + " found");
        }
        return cocktail;
    }

    @Override
    public Cocktail addOrUpdate(Cocktail cocktail) {
        return cocktailRepository.save(cocktail);
    }

    @Override
    public Page findAllWithPages(Pagination pagination) {
        Integer pageNumber = pagination.getPageNumber() != null ? pagination.getPageNumber() : null;
        Integer pageSize = pagination.getPageSize() != null ? pagination.getPageSize() : null;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return cocktailRepository.findAll(pageRequest);
    }

}
