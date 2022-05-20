package com.ziletech.ecommerce.controller;

import com.ziletech.ecommerce.service.UserService;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommerce/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user) {
        UserDTO saveUser = userService.saveUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO>getUserById(@PathVariable("id") Long id){
        UserDTO findById = userService.findById(id);
        return new ResponseEntity<>(findById,HttpStatus.OK);
    }

}
