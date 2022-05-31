package com.ziletech.ecommerce.controller;


import com.ziletech.ecommerce.service.RegistrationService;
import com.ziletech.ecommerce.service.userexception.EmailAlreadyExistException;
import dto.MessageDTO;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ecommerce/api/v1/register")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;


    @PostMapping("")
    public ResponseEntity<MessageDTO> register(@RequestBody UserDTO user){
        try{
            registrationService.register(user);
            return new ResponseEntity<>(new MessageDTO("successfully registered"),HttpStatus.OK);
        }catch (EmailAlreadyExistException e){
            return new ResponseEntity<>(new MessageDTO(e.getMessage()),HttpStatus.OK);

        }
    }




}
