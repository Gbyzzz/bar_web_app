package com.gbyzzz.bar_spring.service.impl;

import com.gbyzzz.bar_spring.entity.Cocktail;
import com.gbyzzz.bar_spring.entity.pagination.Pagination;
import com.gbyzzz.bar_spring.entity.pagination.RestPage;
import com.gbyzzz.bar_spring.repository.CocktailRepository;
import com.gbyzzz.bar_spring.service.CocktailService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Anton Pinchuk
 */
@Service
@CacheConfig(cacheNames = "cs")
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
    @Cacheable(key = "#id")
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
    @CacheEvict(cacheNames = {"cs_pages", "cs"}, allEntries = true)
    public Cocktail addOrUpdate(Cocktail cocktail) {
        cocktail.setPublicationDate(new Date(new java.util.Date().getTime()));
        Cocktail savedCocktail = cocktailRepository.save(cocktail);
        return savedCocktail;
    }

    @Override
    @Cacheable(cacheNames = "cs_pages", key = "#pagination.pageNumber.toString().concat('-').concat(#pagination.pageSize).toString().concat('-').concat(#pagination.sortDirection)")
    public RestPage findAllWithPages(Pagination pagination) {
        Integer pageNumber = pagination.getPageNumber() != null ? pagination.getPageNumber() : null;
        Integer pageSize = pagination.getPageSize() != null ? pagination.getPageSize() : null;
        Sort sort = pagination.getSortDirection().equals(Pagination.SortDirection.DESC) ?
                Sort.by(Sort.Direction.DESC, "cocktailId") :
                Sort.by(Sort.Direction.ASC, "cocktailId");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return new RestPage(cocktailRepository.findAll(pageRequest));
    }

    @Override
    @Cacheable(cacheNames = "cs_pages")
    public List<Cocktail> findForMainPage() {
        return cocktailRepository.findTop3ByOrderByCocktailIdDesc();
    }

}
