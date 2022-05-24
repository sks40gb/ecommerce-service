package com.ziletech.ecommerce.service.impl;

import com.ziletech.ecommerce.entity.Category;
import com.ziletech.ecommerce.entity.SubCategory;
import com.ziletech.ecommerce.repository.CategoryRepository;
import com.ziletech.ecommerce.repository.SubCategoryRepository;
import com.ziletech.ecommerce.service.SubCategoryService;
import dto.CategoryDTO;
import dto.SubCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public SubCategoryDTO save(SubCategoryDTO subCategoryDTO) {
        SubCategory subCategory = new SubCategory();
        subCategoryDTO.copyToEntity(subCategory);
        subCategory.setCategory(getCategory(
                subCategoryDTO.getCategory().getId()));
        subCategoryRepository.save(subCategory);
        return getSubCategoryDTO(subCategory);
    }


    @Override
    public void update(SubCategoryDTO subCategoryDTO) {
        SubCategory subCategory = getSubCategory(subCategoryDTO.getId());
        subCategoryDTO.copyToEntity(subCategory);
        subCategory.setCategory(getCategory(
                subCategoryDTO.getCategory().getId()));
        subCategoryRepository.save(subCategory);
    }

    @Override
    public void delete(Long id) {
        SubCategory subCategory = getSubCategory(id);
        subCategoryRepository.delete(subCategory);

    }

    @Override
    public SubCategoryDTO findById(Long id) {
        SubCategory subCategory = getSubCategory(id);
        return getSubCategoryDTO(subCategory);
    }

    @Override
    public List<SubCategoryDTO> findAll() {
        List<SubCategoryDTO> categoryList = new ArrayList<>();
        for (SubCategory subCategory : subCategoryRepository.findAll()) {
            categoryList.add(getSubCategoryDTO(subCategory));
        }
        return categoryList;
    }


    private SubCategoryDTO getSubCategoryDTO(SubCategory subCategory) {
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.copyFromEntity(subCategory);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.copyFromEntity(subCategory.getCategory());
        subCategoryDTO.setCategory(categoryDTO);
        return subCategoryDTO;
    }

    private SubCategory getSubCategory(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(
                id).orElse(null);
        if (subCategory == null) {
            throw new EntityNotFoundException(
                    "sub-category not found for given id " + id);
        }
        return subCategory;
    }

    private Category getCategory(Long categoryId) {
        Category category = categoryRepository.findById(
                categoryId).orElse(null);
        if (category == null) {
            throw new EntityNotFoundException(
                    "category not found for given id " + categoryId);
        }
        return category;
    }


}
