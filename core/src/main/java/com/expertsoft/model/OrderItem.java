package com.expertsoft.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;


public class OrderItem {
    private Long id;
    private Phone phone;
    private Order order;

    @Digits(integer = 100, fraction = 0)
    @NotNull
    private long quantity;

    public OrderItem() {

    }

    public OrderItem(Phone phone, Order order, long quantity) {
        this.phone = phone;
        this.order = order;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o.getClass() != OrderItem.class)
            return false;
        return this.id == ((OrderItem)o).id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
