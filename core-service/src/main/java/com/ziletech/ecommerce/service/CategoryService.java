package com.ziletech.ecommerce.service;

import dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO save(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    void delete(Long id);

    CategoryDTO findById(Long id);

    List<CategoryDTO> findAll();
}
