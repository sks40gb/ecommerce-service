package com.ziletech.ecommerce.repository;

import com.ziletech.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(@Param("name") String name);

    List<Product> findBySubCategoryId(Long subCategoryId);

    Product findByCode(@Param("code") String code);
}