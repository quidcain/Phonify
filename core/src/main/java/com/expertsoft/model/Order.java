package com.expertsoft.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Order {
    private long id;
    private List<OrderItem> orderItems;
    private BigDecimal subtotal;
    @Value("${delivery.price}")
    private BigDecimal deliveryPrice;
    private String firstName;
    private String lastName;
    private String deliveryAddress;
    private String contactPhoneNo;

    public Order() {
        orderItems = new ArrayList<>();
        subtotal = BigDecimal.ZERO;
    }

    public Order(List<OrderItem> orderItems, BigDecimal subtotal,
                 BigDecimal deliveryPrice, String firstName, String lastName,
                 String deliveryAddress, String contactPhoneNo) {
        this.orderItems = orderItems;
        this.subtotal = subtotal;
        this.deliveryPrice = deliveryPrice;
        this.firstName = firstName;
        this.lastName = lastName;
        this.deliveryAddress = deliveryAddress;
        this.contactPhoneNo = contactPhoneNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public BigDecimal getTotalPrice() {
        return subtotal.add(deliveryPrice);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactPhoneNo() {
        return contactPhoneNo;
    }

    public void setContactPhoneNo(String contactPhoneNo) {
        this.contactPhoneNo = contactPhoneNo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o.getClass() != Order.class)
            return false;
        return this.id == ((Order)o).id;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}
