package com.cybertek.service;

import com.cybertek.enums.OrderStatus;
import com.cybertek.model.Order;
import com.cybertek.model.OrderItem;
import com.cybertek.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderService orderService) {
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
    }

    @Transactional
    public OrderItem create(OrderItem orderItem) throws Exception {

        List<Order> orders = orderService.readByUserAndStatus(orderItem.getOrder().getUser(), OrderStatus.PENDING);

        if (orders.size() > 0) {
            Order currentOrder = orders.get(0);
            orderItem.setOrder(currentOrder);
        }


//        Optional<OrderItem> foundedOrderItem = orderItemRepository.findById(orderItem.getId());
//
//        if (foundedOrderItem.isPresent()) {
//            throw new Exception("This order item already exists");
//        }

        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> readAll() {
        return orderItemRepository.findAll();
    }

    public List<OrderItem> readAllByOrder(Order order) {
        return orderItemRepository.findAllByOrder(order);
    }

    @Transactional
    public void update(OrderItem orderItem) {
        orderItemRepository.findById(orderItem.getId())
                .orElseThrow(() -> new NoSuchElementException("This order item does not exist"));

        orderItemRepository.save(orderItem);
    }


}
