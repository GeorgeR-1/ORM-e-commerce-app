package com.cybertek.model;

import com.cybertek.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity<Long>{

    @Enumerated(EnumType.STRING)
    private OrderStatus status=OrderStatus.PENDING;

    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
