package com.ziletech.ecommerce.repository;

import com.ziletech.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Hello this is just commented text for demo purpose
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
