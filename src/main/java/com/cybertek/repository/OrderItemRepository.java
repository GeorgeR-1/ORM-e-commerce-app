package com.cybertek.repository;

import com.cybertek.model.Order;
import com.cybertek.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

   List<OrderItem> findAllByOrder(Order order);
}
