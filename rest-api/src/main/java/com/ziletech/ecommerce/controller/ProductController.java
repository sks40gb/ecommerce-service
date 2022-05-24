package com.ziletech.ecommerce.controller;

import com.ziletech.ecommerce.service.ProductService;
import dto.MessageDTO;
import dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/ecommerce/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ProductDTO productDTO) {
        try {
            ProductDTO product = productService.save(productDTO);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<MessageDTO> update(@PathVariable("id") Long id,
                                             @RequestBody ProductDTO productDTO) {
        productDTO.setId(id);
        try {
            productService.update(productDTO);
            return new ResponseEntity<>(
                    new MessageDTO("product is updated"), HttpStatus.OK
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    new MessageDTO(e.getMessage()), HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageDTO> delete(@PathVariable("id") Long id) {
        try {
            productService.delete(id);
            return new ResponseEntity<>(
                    new MessageDTO("product is deleted"), HttpStatus.OK
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    new MessageDTO(e.getMessage()), HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> findAll(){
        return new ResponseEntity<>(productService.findAll(),HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<ProductDTO>> findByName(@PathVariable("name") String name){
        return new ResponseEntity<>(productService.findByProductName(name),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long id){
        try {
            ProductDTO product= productService.findById(id);
            return new ResponseEntity<>(product, HttpStatus.FOUND);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    new MessageDTO(e.getMessage()), HttpStatus.NOT_FOUND
            );
        }
    }


}
