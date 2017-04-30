package com.expertsoft.model;


public class CartItem {
    private Phone phone;
    private long quantity;

    public CartItem() {
    }

    public CartItem(Phone phone, long quantity) {
        this.phone = phone;
        this.quantity = quantity;
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
}
