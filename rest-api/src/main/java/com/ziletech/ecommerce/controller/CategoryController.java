package com.ziletech.ecommerce.controller;

import com.ziletech.ecommerce.service.CategoryService;
import dto.CategoryDTO;
import dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/ecommerce/api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO category = categoryService.save(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);

    }

    @PutMapping("{id}")
    public ResponseEntity<MessageDTO> update(@PathVariable("id") Long id,
                                         @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setId(id);
        try {
            categoryService.update(categoryDTO);
            return new ResponseEntity<>(
                    new MessageDTO("category is updated"), HttpStatus.OK
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    new MessageDTO(e.getMessage()), HttpStatus.NOT_FOUND
            );
        }


    }

}
