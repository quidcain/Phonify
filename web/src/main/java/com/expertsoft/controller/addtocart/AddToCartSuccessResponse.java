package com.expertsoft.controller.addtocart;

import java.math.BigDecimal;

public class AddToCartSuccessResponse {
    private long itemsQuantity;
    private BigDecimal subtotal;


    public long getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(long itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
