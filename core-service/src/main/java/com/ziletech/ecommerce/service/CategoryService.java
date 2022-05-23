package com.ziletech.ecommerce.service;

import dto.CategoryDTO;

public interface CategoryService {
    CategoryDTO save(CategoryDTO categoryDTO);
    CategoryDTO update(CategoryDTO categoryDTO);
}
