package com.ziletech.ecommerce.controller;

import com.ziletech.ecommerce.service.CartService;
import dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/ecommerce/api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/add/{userId}/{productId}/{quantity}")
    public ResponseEntity<Object> createCart(@PathVariable("userId") Long userId,
                                             @PathVariable("productId") Long productId,
                                             @PathVariable("quantity") Integer quantity) {
        try {
            cartService.create(userId, productId, quantity);
            return new ResponseEntity<>(new MessageDTO("product added in cart"), HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            cartService.deleteCartItem(id);
            return new ResponseEntity<>("Item remove from cart", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
