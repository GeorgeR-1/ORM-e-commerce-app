package com.cybertek.service;

import com.cybertek.enums.OrderStatus;
import com.cybertek.model.Order;
import com.cybertek.model.User;
import com.cybertek.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order create(Order order) throws Exception {

        Optional<Order> foundedOrder = orderRepository.findById(order.getId());

        if(foundedOrder.isPresent()){
            throw new Exception("This order already exists");
        }

        return orderRepository.save(order);
    }

    public List<Order> readAll(){
        return orderRepository.findAll(Sort.by("orderDate").descending());
    }

    public Order readById(Long id) throws Exception {

        return orderRepository.findById(id)
                .orElseThrow(() -> new Exception("This order does not exist"));
    }

    @Transactional
    public void update(Order order) throws Exception {

        orderRepository.findById(order.getId())
                .orElseThrow(() -> new Exception("This order does not exist"));

        orderRepository.save(order);
    }

    public List<Order> readByUserAndStatus(User user, OrderStatus status) throws Exception {
        return orderRepository.findByUserAndStatus(user,status);
    }




}
