package com.ziletech.ecommerce.controller;

import com.ziletech.ecommerce.service.UserService;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/ecommerce/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user) {
        UserDTO saveUser = userService.saveUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
        try {
            UserDTO user = userService.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Object> getUserByName(@PathVariable("name") String name) {
        try {
            UserDTO user = userService.findByName(name);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<UserDTO>> getAllUser() {
        Iterable<UserDTO> user = userService.findByAll();
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        try {
            userService.update(userDTO);
            return new ResponseEntity<>("User is updated", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>("User Is Deleted", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
