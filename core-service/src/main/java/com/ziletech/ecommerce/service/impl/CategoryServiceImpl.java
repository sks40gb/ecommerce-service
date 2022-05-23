package com.ziletech.ecommerce.service.impl;

import com.ziletech.ecommerce.entity.Category;
import com.ziletech.ecommerce.repository.CategoryRepository;
import com.ziletech.ecommerce.service.CategoryService;
import dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.parser.Entity;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = new Category();
        categoryDTO.copyToEntity(category);
        categoryRepository.save(category);
        categoryDTO.copyFromEntity(category);
        return categoryDTO;
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
       Category category= categoryRepository.findById(
                categoryDTO.getId()).orElse(null);
        if(category==null){
            throw new EntityNotFoundException(
                    "category not found for given id "+categoryDTO.getId());
        }
        categoryDTO.copyToEntity(category);
        categoryRepository.save(category);
        categoryDTO.copyFromEntity(category);
        return categoryDTO;
    }
}
