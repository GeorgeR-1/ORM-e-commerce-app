package com.cybertek.model.dto;

import com.cybertek.model.OrderItem;
import com.cybertek.model.ShipBill;
import lombok.Data;

import java.util.List;

@Data
public class CustomOrderItemDTO {

    private List<OrderItem> orderItem;
    private ShipBill shipping;
    private ShipBill billing;

}
