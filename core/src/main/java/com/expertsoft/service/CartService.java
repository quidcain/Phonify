package com.expertsoft.service;

import com.expertsoft.model.Cart;

public interface CartService {
    void addOrderItem(long id, long quantity);
    void deleteCartItem(long phoneId);
    void updateCartItem(long phoneId, long newQuantity);
    Cart getCart();
}
