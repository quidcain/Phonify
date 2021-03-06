package com.expertsoft.model;

public class OrderItem {
    private Long id;
    private Phone phone;
    private Order order;

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
}
