package com.expertsoft.service;

import com.expertsoft.model.Cart;

import java.util.Map;

public interface CartService {
    void addOrderItem(long id, long quantity);
    void deleteCartItem(long phoneId);
    void updateCartItems(Map<Long, Long> items);
    Cart getCart();
}
