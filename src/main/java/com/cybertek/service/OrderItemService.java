package com.cybertek.service;

import com.cybertek.model.Order;
import com.cybertek.model.OrderItem;
import com.cybertek.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem create(OrderItem orderItem) throws Exception {
        Optional<OrderItem> foundedOrderItem = orderItemRepository.findById(orderItem.getId());

        if(foundedOrderItem.isPresent()){
            throw new Exception("This order item already exists");
        }

        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> readAll(){
        return orderItemRepository.findAll();
    }

    public List<OrderItem> readAllByOrder(Order order){
        return orderItemRepository.findAllByOrder(order);
    }

    public void update(OrderItem orderItem){
        orderItemRepository.findById(orderItem.getId())
                .orElseThrow(() -> new NoSuchElementException("This order item does not exist"));

        orderItemRepository.save(orderItem);
    }




}
