package com.ziletech.ecommerce.controller;

import com.ziletech.ecommerce.service.RoleService;
import dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/ecommerce/api/v1/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(" ")
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO saveRole = roleService.save(roleDTO);
        return new ResponseEntity<>(saveRole, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable("id") Long id,
                                             @RequestBody RoleDTO roleDTO) {
        try {
            roleDTO.setId(id);
            roleService.update(roleDTO);
            return new ResponseEntity<>("Role is updated", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            roleService.delete(id);
            return new ResponseEntity<>("User Is Deleted", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRoleById(@PathVariable("id") Long id) {
        try {
            RoleDTO role = roleService.findById(id);
            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<RoleDTO>> getAllRole() {
        Iterable<RoleDTO> role = roleService.findByAll();
        return new ResponseEntity<>(role, HttpStatus.OK);

    }

}
