package com.expertsoft.model;

import java.math.BigDecimal;

public class CartIndicator {
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
