package com.ziletech.ecommerce.service.impl;

import com.ziletech.ecommerce.entity.Category;
import com.ziletech.ecommerce.entity.SubCategory;
import com.ziletech.ecommerce.repository.CategoryRepository;
import com.ziletech.ecommerce.service.CategoryService;
import dto.CategoryDTO;
import dto.SubCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
    public void update(CategoryDTO categoryDTO) {
        Category category = getCategory(categoryDTO.getId());
        categoryDTO.copyToEntity(category);
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category category = getCategory(id);
        categoryRepository.delete(category);

    }

    @Override
    public CategoryDTO findById(Long id) {
        Category category =  getCategory(id);
        return getCategoryDTO(category);
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> categoryList = new ArrayList<>();
        for(Category category:categoryRepository.findAll()){
            categoryList.add(getCategoryDTO(category));
        }
        return categoryList;
    }


    private CategoryDTO getCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.copyFromEntity(category);
        List<SubCategoryDTO> subCategoryList = new ArrayList<>();
        for(SubCategory subCategory:category.getSubCategoryList()){
            SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
            subCategoryDTO.copyFromEntity(subCategory);
            subCategoryList.add(subCategoryDTO);
        }
        categoryDTO.setSubCategoryList(subCategoryList);
        return categoryDTO;
    }

    private Category getCategory(Long id) {
        Category category = categoryRepository.findById(
                id).orElse(null);
        if (category == null) {
            throw new EntityNotFoundException(
                    "category not found for given id " + id);
        }
        return category;
    }
}
