package com.ziletech.ecommerce.controller;

import com.ziletech.ecommerce.service.OrderService;
import dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/ecommerce/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/place/{userId}")
    public ResponseEntity<Object> createOrder(@PathVariable("userId") Long userId) {

        try {
            orderService.placeOrder(userId);
            return new ResponseEntity<>(new MessageDTO("Order is placed"), HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
