package com.ziletech.ecommerce.controller;


import com.ziletech.ecommerce.service.AddressService;
import com.ziletech.ecommerce.service.UserService;
import dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("ecommerce/api/v1/addresses")
public class AddressController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @PostMapping("/{id}")
    public ResponseEntity<Object> saveAddress(@PathVariable("id") Long userId,
                                              @RequestBody AddressDTO addressDTO) {
        try {
            AddressDTO saveAddress = addressService.saveUserAddress(addressDTO, userId);
            return new ResponseEntity<>(saveAddress, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAddress(@PathVariable("id") Long id,
                                                @RequestBody AddressDTO addressDTO) {
        try {
            addressDTO.setId(id);
            addressService.updateUserAddress(addressDTO);
            return new ResponseEntity<>("Address is updated", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<AddressDTO>> findAllAddresses(
            @PathVariable("userId") Long userId
    ) {
        List<AddressDTO> addressDTOList = addressService.findAllAddress(userId);
        return new ResponseEntity<>(addressDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            addressService.delete(id);
            return new ResponseEntity<>("Address Is Deleted", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
        try {
            AddressDTO address = addressService.findById(id);
            return new ResponseEntity<>(address, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
