package com.gbyzzz.bar_spring.service;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.repository.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Anton Pinchuk
 */
@Service
public class CocktailServiceImpl implements CocktailService {

    @Autowired
    private CocktailRepository cocktailRepository;

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

}
