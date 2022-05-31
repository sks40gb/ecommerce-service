package com.ziletech.ecommerce.controller;

import com.ziletech.ecommerce.service.CategoryService;
import dto.CategoryDTO;
import dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/ecommerce/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

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
            return new ResponseEntity<>(new MessageDTO("category is updated"), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageDTO> delete(@PathVariable("id") Long id) {
        try {
            categoryService.delete(id);
            return new ResponseEntity<>(new MessageDTO("category is deleted"), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long id) {
        try {
            CategoryDTO category = categoryService.findById(id);
            return new ResponseEntity<>(category, HttpStatus.FOUND);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
