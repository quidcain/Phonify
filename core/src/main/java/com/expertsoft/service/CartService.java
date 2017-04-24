package com.expertsoft.service;

import com.expertsoft.model.Cart;
import com.expertsoft.model.CartIndicator;
import com.expertsoft.model.CartItem;

import java.util.List;

public interface CartService {
    void addOrderItem(long id, long quantity);
    CartIndicator getCartIndicator();
    List<CartItem> getCartItems();
    void deleteCartItem(long phoneId);
    void updateCartItem(long phoneId, long newQuantity);
    Cart getCart();
}
