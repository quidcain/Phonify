package com.expertsoft.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Created by stoat on 3/3/17.
 */
public class OrderItem {
    private Phone phone;
    
    @Digits(integer = 100, fraction = 0)
    @NotNull
    private long quantity;

    private Order order;

    public OrderItem() {

    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
