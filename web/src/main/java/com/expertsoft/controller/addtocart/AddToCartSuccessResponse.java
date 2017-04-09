package com.expertsoft.controller.addtocart;

public class AddToCartSuccessResponse {
    private long itemsQuantity;
    private String subtotal;


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
