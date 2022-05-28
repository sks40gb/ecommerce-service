package com.ziletech.ecommerce.repository;

import com.ziletech.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(@Param("name") String name);

    List<Product> findBySubCategoryId(Long subCategoryId);

    Product findByCode(@Param("code") String code);

    @Query(value = "SELECT\n" +
            "  p.*\n" +
            " FROM product p\n" +
            "  LEFT JOIN sub_category sc\n" +
            "    ON sc.id = p.sub_category_id\n" +
            "  LEFT JOIN category c\n" +
            "    ON c.id = sc.category_id\n" +
            " WHERE CONCAT(p.name,' ',p.short_desc,' ',p.long_desc,sc.name,sc.name) LIKE %:name%\n" +
            "    AND p.unit_price>= :min AND p.unit_price <= :max "

    ,nativeQuery = true)
    List<Product> search(@Param("name")String productName, @Param("min")Integer min,
                         @Param("max")Integer max);
}