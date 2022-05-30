package com.ziletech.ecommerce.service.impl;

import com.ziletech.ecommerce.entity.Cart;
import com.ziletech.ecommerce.entity.CartItem;
import com.ziletech.ecommerce.entity.Product;
import com.ziletech.ecommerce.entity.User;
import com.ziletech.ecommerce.repository.CartItemRepository;
import com.ziletech.ecommerce.repository.CartRepository;
import com.ziletech.ecommerce.repository.ProductRepository;
import com.ziletech.ecommerce.repository.UserRepository;
import com.ziletech.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public void create(Long userId, Long productId, Integer quantity) {

        User user = getUser(userId);
        Cart cart = cartRepository.findByUserId(userId);
        Product product = getProduct(productId);

        if (cart != null) {
            if (!isProductAvailableInCart(cart,product,quantity)){
                createCartItem(quantity, cart, product);
            }
        }else {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setCreateDate(new Date());
            cartRepository.saveAndFlush(newCart);
            createCartItem(quantity, newCart, product);
        }

    }


    private void createCartItem(Integer quantity, Cart cart, Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    private User getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException("ID is not found for these  " + id);
        }
        return user;
    }
    private Product getProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new EntityNotFoundException("ID is not found for these  " + id);
        }
        return product;
    }

    private Boolean isProductAvailableInCart(Cart cart, Product product, Integer quantity) {
        boolean isProductAvailableInCart = false;
        for (CartItem item : cart.getCartItems()) {
            if (item.getProduct().getId() == product.getId()) {
                isProductAvailableInCart = true;
                item.setQuantity(quantity);
                cartItemRepository.save(item);
                break;
            }
        }
        return isProductAvailableInCart;
    }

    public void deleteCartItem(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
        if (cartItem == null) {
            throw new EntityNotFoundException("ID is not found for these  " + id);
        }
        cartItemRepository.delete(cartItem);
    }


}
