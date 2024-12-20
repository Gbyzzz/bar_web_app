package com.gbyzzz.bar_web_app.bar_backend.service;

import com.gbyzzz.bar_web_app.bar_backend.dto.CocktailDTO;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.Pagination;
import com.gbyzzz.bar_web_app.bar_backend.entity.pagination.RestPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Anton Pinchuk
 */
public interface CocktailService {
    List<CocktailDTO> findAll();

    CocktailDTO findById(long id) throws Exception;

    CocktailDTO addOrUpdate(CocktailDTO cocktail, MultipartFile image) throws Exception;

    RestPage findAllWithPages(Pagination pagination);

    List<CocktailDTO> findForMainPage();

    RestPage findAllWithPagesAdmin(Pagination pagination);
}
