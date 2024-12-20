package com.gbyzzz.bar_web_app.bar_backend.service.impl;

import com.gbyzzz.bar_web_app.bar_backend.config.NotificationWebSocketHandler;
import com.gbyzzz.bar_web_app.bar_backend.dto.CocktailDTO;
import com.gbyzzz.bar_web_app.bar_backend.dto.IngredientDTO;
import com.gbyzzz.bar_web_app.bar_backend.dto.RecipeDTO;
import com.gbyzzz.bar_web_app.bar_backend.dto.mapper.CocktailDTOMapper;
import com.gbyzzz.bar_web_app.bar_backend.dto.mapper.RecipeDTOMapper;
import com.gbyzzz.bar_web_app.bar_backend.entity.Cocktail;
import com.gbyzzz.bar_web_app.bar_backend.entity.notification.Notification;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.RestPage;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.repository.CocktailRepository;
import com.gbyzzz.bar_web_app.bar_backend.service.CocktailService;
import com.gbyzzz.bar_web_app.bar_backend.service.ImageStorageService;
import com.gbyzzz.bar_web_app.bar_backend.service.KafkaService;
import com.gbyzzz.bar_web_app.bar_backend.service.RecipeService;
import com.gbyzzz.bar_web_app.bar_backend.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
@RequiredArgsConstructor
public class CocktailServiceImpl implements CocktailService {

    @Value("${application.kafka.topic.to_save_to_search}")
    private String topic;

    @Value("${app.minio.cocktailImage}")
    private String cocktailImage;

    @Value("${app.minio.cocktailThumbnail}")
    private String cocktailThumbnail;

    private final CocktailRepository cocktailRepository;
    private final RecipeService recipeService;
    private final ImageStorageService imageStorageService;
    private final NotificationWebSocketHandler webSocketHandler;
    private final KafkaService kafkaService;
    private final CocktailDTOMapper cocktailDTOMapper;
    private final RecipeDTOMapper recipeMapper;

    private static final int MAX_COCKTAIL_IMAGE_SIZE = 635;
    private static final int MAX_COCKTAIL_IMAGE_THUMBNAIL_SIZE = 150;

    @Override
    public List<CocktailDTO> findAll() {
        return cocktailRepository.findAll().stream()
                .map(cocktailDTOMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Cacheable(key = "#id")
    public CocktailDTO findById(long id) throws Exception {
        return cocktailDTOMapper.toDTO(cocktailRepository.findById(id)
                .orElseThrow(() -> new Exception("No cocktail with id " + id + " found")));
    }

    @Override
    @CacheEvict(cacheNames = {"cs_pages", "cs"}, allEntries = true)
    public CocktailDTO addOrUpdate(CocktailDTO cocktailDTO, MultipartFile image) throws Exception {
        if(checkRecipes(cocktailDTO.recipes())) {
            StringBuilder message = new StringBuilder();
            Cocktail cocktail = cocktailDTOMapper.toEntity(cocktailDTO);

            if(image != null) {
                if (cocktail.getCocktailImage() != null) {
                    imageStorageService.removeImage(cocktail.getCocktailImage());
                    imageStorageService.removeImage(cocktail.getCocktailImageThumbnail());
                }
                cocktail.setCocktailImage(imageStorageService.saveImage(image, cocktailImage, MAX_COCKTAIL_IMAGE_SIZE));
                cocktail.setCocktailImageThumbnail(imageStorageService.saveImage(image, cocktailThumbnail, MAX_COCKTAIL_IMAGE_THUMBNAIL_SIZE));
            }

            if (cocktail.getPublicationDate() == null) {
                cocktail.setPublicationDate(new Date(new java.util.Date().getTime()));
                message.append("New cocktail was just added by ").append(cocktail.getCocktailAuthor().getUsername());
            }

            cocktail = cocktailRepository.save(cocktail);
            kafkaService.sendMessage(topic, cocktail.getCocktailId());
            if(!message.isEmpty()){
                webSocketHandler.sendNotification(new Notification(message.toString(), cocktail.getCocktailId()));
            }
            return cocktailDTOMapper.toDTO(cocktail);
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
        List <Cocktail> cocktails = cocktailRepository.findTop3ByOrderByCocktailIdDesc();
        return cocktails.stream()
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

        return new RestPage(new PageImpl<>(cocktails.getContent().stream()
                .map(cocktailDTOMapper::toDTO).collect(Collectors.toList())));
    }

}
