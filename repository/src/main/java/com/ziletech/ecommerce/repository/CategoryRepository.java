package com.ziletech.ecommerce.repository;

import com.ziletech.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByIdAndIsEnable(Long id, @Param("is_enable") boolean status);

    List<Category> findByIsEnable( @Param("is_enable") boolean status);
}