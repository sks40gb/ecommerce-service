package com.ziletech.ecommerce.repository;

import com.ziletech.ecommerce.entity.Category;
import com.ziletech.ecommerce.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findByIdAndIsEnable(Long id, @Param("is_enable") boolean status);
    List<SubCategory> findByIsEnable(@Param("is_enable") boolean status);
}