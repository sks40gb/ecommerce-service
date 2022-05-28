package com.ziletech.ecommerce.service.impl;

import com.ziletech.ecommerce.entity.Product;
import com.ziletech.ecommerce.entity.ProductDetail;
import com.ziletech.ecommerce.entity.SubCategory;
import com.ziletech.ecommerce.repository.ProductDetailRepository;
import com.ziletech.ecommerce.repository.ProductRepository;
import com.ziletech.ecommerce.repository.SubCategoryRepository;
import com.ziletech.ecommerce.service.ProductService;
import dto.ProductDTO;
import dto.SubCategoryDTO;
import dto.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = new Product();
        //check product code if already exist
        Product existingProduct = getProductByCode(productDTO.getCode());
        if (existingProduct != null) {
            throw new EntityNotFoundException("product already exist with given code " + productDTO.getCode());
        }
        //get category from  given category id
        SubCategory subCategory = getSubCategory(
                productDTO.getSubCategory().getId()
        );
        productDTO.copyToEntity(product);
        product.setSubCategory(subCategory);
        productRepository.saveAndFlush(product);
        //save product details
        saveProductDetails(productDTO, product);
        return getProductDTO(product);
    }

    private void saveProductDetails(ProductDTO productDTO, Product product) {
        List<ProductDetail> productDetailSet = new LinkedList<>();
        if (productDTO.getSizes() != null && productDTO.getSizes().size() > 0) {
            setProductDetailsList(product, Type.SIZE.name(),
                    productDTO.getSizes(),
                    productDetailSet);
        }
        if (productDTO.getColors() != null && productDTO.getColors().size() > 0) {
            setProductDetailsList(product, Type.COLOR.name(),
                    productDTO.getColors(),
                    productDetailSet);
        }
        if (productDetailSet.size() > 0) {
            productDetailRepository.saveAllAndFlush(productDetailSet);
            product.setProductDetails(productDetailSet);
        }
    }

    private void setProductDetailsList(Product product,
                                       String type,
                                       Set<String> detailsList,
                                       List<ProductDetail> productDetailsList) {

        for (String sizeName : detailsList) {
            ProductDetail productDetail = new ProductDetail();
            productDetail.setType(type);
            productDetail.setName(sizeName);
            productDetail.setProduct(product);
            productDetailsList.add(productDetail);
        }
    }


    @Override
    public void update(ProductDTO productDTO) {
        Product product = getProduct(productDTO.getId());
        if(!productDTO.getCode().equals(product.getCode())){
            Product existingProduct = getProductByCode(productDTO.getCode());
            if (existingProduct != null) {
                throw new EntityNotFoundException("product already exist with given code " + productDTO.getCode());
            }
        }
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
        for (Product product : productRepository.findByNameContaining(name)) {
            productList.add(getProductDTO(product));
        }
        return productList;
    }

    @Override
    public List<ProductDTO> search(String name, Integer min, Integer max) {
        List<ProductDTO> productList = new ArrayList<>();
        for (Product product : productRepository.search(name,min,max)) {
            productList.add(getProductDTO(product));
        }
        return productList;
    }
    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productList = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            productList.add(getProductDTO(product));
        }
        return productList;
    }

    public List<ProductDTO> findProductsByCategoryId(Long categoryId) {
        List<ProductDTO> productList = new ArrayList<>();
        for (Product product : productRepository.findBySubCategoryId(categoryId)) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.copyFromEntity(product);
            productList.add(productDTO);
        }
        return productList;
    }

    @Override
    public ProductDTO findProductsByCode(String code) {
      Product product = getProductByCode(code);
        if (product == null) {
            throw new EntityNotFoundException("Product is not found for given code " + code);
        }
        return getProductDTO(product);
    }

    private Product getProductByCode(String code) {
        return productRepository.findByCode(code);
    }


    private ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.copyFromEntity(product);
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.copyFromEntity(product.getSubCategory());
        productDTO.setSubCategory(subCategoryDTO);

        Set<String> colors = new HashSet<>();
        Set<String> sizes = new HashSet<>();
        if (product.getProductDetails() != null) {
            for (ProductDetail productDetail : product.getProductDetails()) {
                if (productDetail.getType().equals(Type.COLOR.name())) {
                    colors.add(productDetail.getName());
                }
                if (productDetail.getType().equals(Type.SIZE.name())) {
                    sizes.add(productDetail.getName());
                }
            }
        }

        productDTO.setColors(colors);
        productDTO.setSizes(sizes);
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
