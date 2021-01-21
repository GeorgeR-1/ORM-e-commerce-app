package com.cybertek.repository;

import com.cybertek.enums.OrderStatus;
import com.cybertek.model.Order;
import com.cybertek.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

   List<OrderItem> findAllByOrder(Order order);

   Optional<OrderItem> findByProductIdAndOrderUserIdAndOrderStatus(Long productID, Long userId, OrderStatus status);


