package com.ziletech.ecommerce.service.impl;

import com.ziletech.ecommerce.entity.Category;
import com.ziletech.ecommerce.entity.Product;
import com.ziletech.ecommerce.entity.SubCategory;
import com.ziletech.ecommerce.repository.ProductRepository;
import com.ziletech.ecommerce.repository.SubCategoryRepository;
import com.ziletech.ecommerce.service.CategoryService;
import com.ziletech.ecommerce.service.ProductService;
import com.ziletech.ecommerce.service.SubCategoryService;
import dto.CategoryDTO;
import dto.ProductDTO;
import dto.SubCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = new Product();
        //get category from  given category id
        SubCategory subCategory = getSubCategory(
                productDTO.getSubCategory().getId()
        );
        productDTO.copyToEntity(product);
        product.setSubCategory(subCategory);
        productRepository.save(product);
        return getProductDTO(product);
    }

    @Override
    public void update(ProductDTO productDTO) {
        Product product = getProduct(productDTO.getId());
        //get category from  given category id
        SubCategory subCategory = getSubCategory(
                productDTO.getSubCategory().getId()
        );
        productDTO.copyToEntity(product);
        product.setSubCategory(subCategory);
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(getProduct(id));
    }

    @Override
    public ProductDTO findById(Long id) {
        return getProductDTO(getProduct(id));
    }

    @Override
    public List<ProductDTO> findByProductName(String name) {
        List<ProductDTO> productList = new ArrayList<>();
        for(Product product:productRepository.findByNameContaining(name)){
            productList.add(getProductDTO(product));
        }
        return productList;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productList = new ArrayList<>();
        for(Product product:productRepository.findAll()){
            productList.add(getProductDTO(product));
        }
        return productList;
    }

    private ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.copyFromEntity(product);
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.copyFromEntity(product.getSubCategory());
        productDTO.setSubCategory(subCategoryDTO);
        return productDTO;
    }


    private SubCategory getSubCategory(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(
                id).orElse(null);
        if (subCategory == null) {
            throw new EntityNotFoundException(
                    "sub category not found for given id " + id);
        }
        return subCategory;
    }



    private Product getProduct(Long id) {
        Product product = productRepository.findById(
                id).orElse(null);
        if (product == null) {
            throw new EntityNotFoundException(
                    "product not found for given id " + id);
        }
        return product;
    }
}
