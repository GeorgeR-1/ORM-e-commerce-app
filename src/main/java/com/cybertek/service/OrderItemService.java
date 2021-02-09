package com.cybertek.service;

import com.cybertek.enums.OrderStatus;
import com.cybertek.model.Order;
import com.cybertek.model.OrderItem;
import com.cybertek.model.User;
import com.cybertek.model.dto.CustomOrderItemDTO;

import java.util.List;

public interface OrderItemService {

    OrderItem create(OrderItem orderItem)throws Exception;

    List<OrderItem> buildOrderItems(CustomOrderItemDTO orderItemsDTO) throws Exception;

    List<OrderItem> readAll();

    List<OrderItem> readAllByOrder(Order order);

    void update(OrderItem orderItem);

    List<Order> readByUserAndStatus(User user, OrderStatus status) throws Exception;
}
