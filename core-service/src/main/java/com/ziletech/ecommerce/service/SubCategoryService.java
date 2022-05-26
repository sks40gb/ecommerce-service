package com.ziletech.ecommerce.service;

import dto.CategoryDTO;
import dto.SubCategoryDTO;

import java.util.List;

public interface SubCategoryService {
    SubCategoryDTO save(SubCategoryDTO subCategoryDTO);

    void update(SubCategoryDTO subCategoryDTO);

    void delete(Long id);

    SubCategoryDTO findById(Long id);

    List<SubCategoryDTO> findAll();
}
