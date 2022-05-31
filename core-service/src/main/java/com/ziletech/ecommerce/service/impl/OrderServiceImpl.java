package com.ziletech.ecommerce.service.impl;

import com.ziletech.ecommerce.entity.*;
import com.ziletech.ecommerce.repository.*;
import com.ziletech.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void placeOrder(Long userId) {
        Order order = new Order();
        User user = getUser(userId);
        order.setUser(user);
        order.setCreatedDate(new Date());
        orderRepository.saveAndFlush(order);
        createOrderItem(user, order);

    }

    private void createOrderItem(User user, Order order) {
        Cart cart = cartRepository.findByUserId(user.getId());
        if (cart.getCartItems() != null) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            List<OrderItem> items = new ArrayList<>();
            for (CartItem item : cart.getCartItems()) {
                orderItem.setQuantity(item.getQuantity());
                orderItem.setProduct(item.getProduct());
                orderItem.setPrice(item.getProduct().getUnitPrice());
                items.add(orderItem);
            }
            orderItemRepository.saveAll(items);
            clearItemFromCart(cart);
        }
    }

    private void clearItemFromCart(Cart cart) {
        cartItemRepository.deleteAll(cart.getCartItems());
    }

    private User getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException("ID is not found for these  " + id);
        }
        return user;
    }
}
