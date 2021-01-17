package com.cybertek.model;

import com.cybertek.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders_items")
@Where(clause = "is_deleted=false")
public class OrderItem extends BaseEntity<Long>{

    private Integer quantity;
    private BigDecimal price;

    private String productHistory;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;



}
