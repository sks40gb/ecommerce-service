package com.ziletech.ecommerce.service;

import org.springframework.stereotype.Service;

@Service
public interface CartService {

    void create(Long userId, Long productId, Integer quantity);


    void deleteCartItem(Long id);
}
