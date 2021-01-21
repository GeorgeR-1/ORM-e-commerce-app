package com.cybertek.model;

import com.cybertek.enums.ShipBillStatus;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "shippment_billing")
public class ShipBill extends BaseEntity<Long>{

    private String street1;

    private String street2;

    private String country;

    private String state;

    private String city;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private ShipBillStatus status;

}
