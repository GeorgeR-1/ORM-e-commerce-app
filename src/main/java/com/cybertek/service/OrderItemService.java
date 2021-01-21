package com.cybertek.service;

import com.cybertek.enums.OrderStatus;
import com.cybertek.model.Order;
import com.cybertek.model.OrderItem;
import com.cybertek.model.User;
import com.cybertek.model.dto.CustomOrderItemDTO;
import com.cybertek.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderService orderService;
    private final UserService userService;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderService orderService,
                            UserService userService) {

        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
        this.userService = userService;
    }

    @Transactional
    public OrderItem create(OrderItem orderItem) throws Exception {

        //TODO get the current user from securityContextHolder;
        //instead of "orderItem.getOrder().getUser()" add current user
        List<Order> orders = orderService.readByUserAndStatus(orderItem.getOrder().getUser(), OrderStatus.PENDING);

        if (orders.size() > 0) {
            Order currentOrder = orders.get(0);
            orderItem.setOrder(currentOrder);
        }

        //TODO get the current user from securityContextHolder;
        //instead of "orderItem.getOrder().getUser().getId()" add current user
        Optional<OrderItem> foundItem = orderItemRepository.findByProductIdAndOrderUserIdAndOrderStatus(orderItem.getProduct().getId(),
                orderItem.getOrder().getUser().getId(),
                OrderStatus.PENDING);

        if (foundItem.isPresent()){
            foundItem.get().setPrice(orderItem.getPrice());
            foundItem.get().setQuantity(orderItem.getQuantity());
            return  orderItemRepository.save(foundItem.get());
        }

        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> buildOrderItems(CustomOrderItemDTO orderItemsDTO) throws Exception {

        User currentUser = userService.readByEmail("admin@admin.com");
        List<Order> orders = orderService.readByUserAndStatus(currentUser, OrderStatus.PENDING);

        Order currentOrder = orders.get(0);
        currentOrder.setBilling(orderItemsDTO.getBilling());
        currentOrder.setShipping(orderItemsDTO.getShipping());
        currentOrder.setStatus(OrderStatus.APPROVED);

        List<OrderItem> orderItems =
                orderItemsDTO.getOrderItem().stream().peek(orderItem -> {

                    currentOrder.setTotalPrice(currentOrder.getTotalPrice().add(orderItem.getPrice()));
                    orderItem.setStatus(OrderStatus.APPROVED);
                    orderItem.setOrder(currentOrder);

                }).collect(Collectors.toList());

        return orderItemRepository.saveAll(orderItems);
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
