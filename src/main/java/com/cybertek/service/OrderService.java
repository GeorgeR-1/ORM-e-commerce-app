package com.cybertek.service;

import com.cybertek.enums.OrderStatus;
import com.cybertek.model.Order;
import com.cybertek.model.User;

import java.util.List;

public interface OrderService {

    Order create(Order order) throws Exception;

    List<Order> readAll();

    Order readById(Long id) throws Exception;

    void update(Order order) throws Exception;

    List<Order> readByUserAndStatus(User user, OrderStatus status) throws Exception;



}
