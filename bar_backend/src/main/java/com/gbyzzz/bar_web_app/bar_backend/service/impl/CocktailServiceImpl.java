package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.config.NotificationWebSocketHandler;
import com.gbyzzz.bar_web_app.bar_backend.dto.CocktailDTO;
import com.gbyzzz.bar_web_app.bar_backend.dto.CocktailRecipeDTO;
import com.gbyzzz.bar_web_app.bar_backend.dto.IngredientDTO;
import com.gbyzzz.bar_web_app.bar_backend.dto.RecipeDTO;
import com.gbyzzz.bar_web_app.bar_backend.dto.mapper.CocktailDTOMapper;
import com.gbyzzz.bar_web_app.bar_backend.dto.mapper.RecipeDTOMapper;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.Recipe;
import com.gbyzzz.bar_web_app.bar_backend.entity.notification.Notification;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.RestPage;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.repository.CocktailRepository;
import com.gbyzzz.bar_web_app.bar_backend.service.CocktailService;
import com.gbyzzz.bar_web_app.bar_backend.service.RecipeService;
import com.gbyzzz.bar_web_app.bar_backend.service.exception.ServiceException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Anton Pinchuk
 */
@Service
@CacheConfig(cacheNames = "cs")
public class CocktailServiceImpl implements CocktailService {

    CocktailDTOMapper cocktailDTOMapper = CocktailDTOMapper.INSTANCE;
    RecipeDTOMapper recipeMapper = RecipeDTOMapper.INSTANCE;


    private final CocktailRepository cocktailRepository;
    private final RecipeService recipeService;
    private final NotificationWebSocketHandler webSocketHandler;

    public CocktailServiceImpl(CocktailRepository cocktailRepository, RecipeService recipeService, NotificationWebSocketHandler webSocketHandler) {
        this.cocktailRepository = cocktailRepository;
        this.recipeService = recipeService;
        this.webSocketHandler = webSocketHandler;
    }

    @Override
    public List<CocktailDTO> findAll() {
        return cocktailRepository.findAll().stream()
                .map(cocktailDTOMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Cacheable(key = "#id")
    public CocktailRecipeDTO findById(long id) throws Exception {
        Cocktail cocktail = cocktailRepository.findById(id)
                .orElseThrow(() -> new Exception("No cocktail with id " + id + " found"));
        return new CocktailRecipeDTO(cocktailDTOMapper.toDTO(cocktail),
                recipeService.findRecipesByCocktail(cocktail)
                );
    }

    @Override
    @CacheEvict(cacheNames = {"cs_pages", "cs"}, allEntries = true)
    public CocktailRecipeDTO addOrUpdate(CocktailRecipeDTO cocktailRecipeDTO) throws ServiceException, IOException {
        if(checkRecipes(cocktailRecipeDTO.recipesDTO())) {
            StringBuilder message = new StringBuilder();
            Cocktail cocktail = cocktailDTOMapper.toEntity(cocktailRecipeDTO.cocktailDTO());
            List<Recipe> recipes = cocktailRecipeDTO.recipesDTO().stream().map(recipeMapper::toEntity)
                    .toList();
            if (cocktail.getPublicationDate() == null) {
                cocktail.setPublicationDate(new Date(new java.util.Date().getTime()));
                message.append("New cocktail was just added by ").append(cocktail.getCocktailAuthor().getUsername());
            }
            cocktail = cocktailRepository.save(cocktail);
            if(!message.isEmpty()){
                webSocketHandler.sendNotification(new Notification(message.toString(), cocktail.getCocktailId()));
            }
            recipeService.addAll(recipes, cocktail);
            return new CocktailRecipeDTO(cocktailDTOMapper.toDTO(cocktailRepository.save(cocktail)),
                    recipes.stream().map(recipeMapper::toDTO).collect(Collectors.toList()));
        } else {
            throw new ServiceException("Duplicates in ingredients");
        }
    }

    private boolean checkRecipes(List<RecipeDTO> recipeDTOS) {
        List<IngredientDTO> ingredients = recipeDTOS.stream().map(recipeDTO ->
                recipeDTO.ingredient()).collect(Collectors.toList());
        Set<IngredientDTO> ingredientDTOSet = new HashSet<>(ingredients);
        return ingredients.size() == ingredientDTOSet.size();
    }

    @Override
    @Cacheable(cacheNames = "cs_pages", key = "#pagination.pageNumber.toString().concat('-')" +
            ".concat(#pagination.pageSize).toString().concat('-').concat(#pagination.sortDirection)")
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
    public List<CocktailDTO> findForMainPage() {
        return cocktailRepository.findTop3ByOrderByCocktailIdDesc().stream()
                .map(cocktailDTOMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public RestPage findAllWithPagesAdmin(Pagination pagination) {
        Integer pageNumber = pagination.getPageNumber() != null ? pagination.getPageNumber() : null;
        Integer pageSize = pagination.getPageSize() != null ? pagination.getPageSize() : null;
        Sort sort = pagination.getSortDirection().equals(Pagination.SortDirection.DESC) ?
                Sort.by(Sort.Direction.DESC, "cocktailId") :
                Sort.by(Sort.Direction.ASC, "cocktailId");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<Cocktail> cocktails = cocktailRepository.findAll(pageRequest);
        List<CocktailRecipeDTO> cocktailDTOS = new ArrayList<>();

            for (Cocktail cocktail : cocktails.getContent()) {
                cocktailDTOS.add(new CocktailRecipeDTO(cocktailDTOMapper.toDTO(cocktail),
                        recipeService.findRecipesByCocktail(cocktail)));
        }
        return new RestPage(new PageImpl<>(cocktailDTOS));
    }

}
