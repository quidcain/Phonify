package com.expertsoft.controller;

public class AddToCartResponse {
    private long itemsQuantity;
    private String subtotal;

    public AddToCartResponse() {

    }

    public AddToCartResponse(long itemsQuantity, String subtotal) {
        this.itemsQuantity = itemsQuantity;
        this.subtotal = subtotal;
    }

    public long getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(long itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
}
