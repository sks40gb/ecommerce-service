package com.ziletech.ecommerce.controller;

import com.ziletech.ecommerce.service.SubCategoryService;
import dto.SubCategoryDTO;
import dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/ecommerce/api/v1/subcategories")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody SubCategoryDTO subCategoryDTO) {
        try {
            SubCategoryDTO subCategory = subCategoryService.save(subCategoryDTO);
            return new ResponseEntity<>(subCategory, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageDTO(e.getMessage()), HttpStatus.CREATED);

        }
    }

    @PutMapping("{id}")
    public ResponseEntity<MessageDTO> update(@PathVariable("id") Long id,
                                             @RequestBody SubCategoryDTO subCategoryDTO) {
        subCategoryDTO.setId(id);
        try {
            subCategoryService.update(subCategoryDTO);
            return new ResponseEntity<>(new MessageDTO("sub-category is updated"), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageDTO> delete(@PathVariable("id") Long id) {
        try {
            subCategoryService.delete(id);
            return new ResponseEntity<>(
                    new MessageDTO("category is deleted"), HttpStatus.OK
            );
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    new MessageDTO(e.getMessage()), HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubCategoryDTO>> findAll() {
        return new ResponseEntity<>(subCategoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getSubCategoryById(@PathVariable Long id) {
        try {
            SubCategoryDTO subCategory = subCategoryService.findById(id);
            return new ResponseEntity<>(subCategory, HttpStatus.FOUND);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    new MessageDTO(e.getMessage()), HttpStatus.NOT_FOUND
            );
        }
    }

}
