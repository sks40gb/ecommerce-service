package com.ziletech.ecommerce.repository;

import com.ziletech.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingAndIsEnable(@Param("name") String name,@Param("is_enable") boolean status);

    List<Product> findBySubCategoryIdAndIsEnable(Long subCategoryId,@Param("is_enable") boolean status);

    Product findByCodeAndIsEnable(@Param("code") String code,@Param("is_enable") boolean status);

    @Query(value = "SELECT\n" +
            "  p.*\n" +
            " FROM product p\n" +
            "  LEFT JOIN sub_category sc\n" +
            "    ON sc.id = p.sub_category_id\n" +
            "  LEFT JOIN category c\n" +
            "    ON c.id = sc.category_id\n" +
            " WHERE CONCAT(p.name,' ',p.short_desc,' ',p.long_desc,sc.name,sc.name) LIKE %:name%\n" +
            "    AND (:min is null or p.unit_price>= :min)  AND (:max is null or p.unit_price <= :max) AND p.is_enable = true"

    ,nativeQuery = true)
    List<Product> search(@Param("name")String productName, @Param("min")Integer min,
                         @Param("max")Integer max);

    List<Product> findByIsEnable(@Param("is_enable") boolean status);

    Product findByIdAndIsEnable(Long id, @Param("is_enable") boolean status);
}