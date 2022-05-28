package com.ziletech.ecommerce.service;

import dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO save(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    void delete(Long id);

    ProductDTO findById(Long id);

    List<ProductDTO> findByProductName(String name);

    List<ProductDTO> findAll();

    List<ProductDTO> search(String name, Integer min, Integer max);

    List<ProductDTO> findProductsByCategoryId(Long id);

    ProductDTO findProductsByCode(String code);
}
