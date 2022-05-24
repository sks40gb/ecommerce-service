package com.ziletech.ecommerce.service;

import dto.CategoryDTO;
import dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO save(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    void delete(Long id);

    ProductDTO findById(Long id);

    List<ProductDTO> findByProductName(String name);

    List<ProductDTO> findAll();

}
