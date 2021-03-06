package com.expertsoft.service;

import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@Service
public class CartServiceImpl implements CartService {
    private PhoneDao phoneDao;
    private Cart cart;

    @Autowired
    public CartServiceImpl(PhoneDao phoneDao, Cart cart) {
        this.phoneDao = phoneDao;
        this.cart = cart;
    }

    @Override
    public void addOrderItem(long id, long quantity) {
        boolean itemAlreadyPresents = false;
        Phone phone;
        for (CartItem item : cart.getCartItems()) {
            phone = item.getPhone();
            if (phone.getId() == id) {
                item.setQuantity(item.getQuantity() + quantity);
                itemAlreadyPresents = true;
                break;
            }
        }
        if (!itemAlreadyPresents) {
            phone = phoneDao.get(id);
            CartItem item = new CartItem(phone, quantity);
            cart.getCartItems().add(item);
        }
        CartIndicator cartIndicator = cart.getCartIndicator();
        cartIndicator.setItemsQuantity(cartIndicator.getItemsQuantity() + quantity);
        recalculateSubtotal();
    }

    @Override
    public void deleteCartItem(long phoneId) {
        int itemIndex = getItemIndex(phoneId);
        List<CartItem> orderItems = cart.getCartItems();
        CartItem item = orderItems.get(itemIndex);
        CartIndicator cartIndicator = cart.getCartIndicator();
        cartIndicator.setItemsQuantity(cartIndicator.getItemsQuantity() - item.getQuantity());
        orderItems.remove(itemIndex);
        recalculateSubtotal();
    }

    @Override
    public void updateCartItems(Map<Long, Long> items) {
        long newTotalQuantity = 0;
        for (Map.Entry<Long, Long> item : items.entrySet()) {
            Long phoneId = item.getKey();
            Long newQuantity = item.getValue();
            newTotalQuantity += newQuantity;
            cart.getCartItems().get(getItemIndex(phoneId)).setQuantity(newQuantity);
        }
        cart.getCartIndicator().setItemsQuantity(newTotalQuantity);
        recalculateSubtotal();
    }

    public Cart getCart() {
        return cart;
    }

    private void recalculateSubtotal() {
        List<CartItem> cartItems = cart.getCartItems();
        BigDecimal subtotal = BigDecimal.ZERO;
        for (int i = 0, j = cartItems.size(); i < j; i++) {
            CartItem item = cartItems.get(i);
            subtotal = subtotal.add(item.getPhone().getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        cart.setSubtotal(subtotal);
        cart.getCartIndicator().setSubtotal(subtotal);
    }

    private int getItemIndex(long phoneId) {
        List<CartItem> orderItems = cart.getCartItems();
        for (int i = 0, j = orderItems.size(); i < j; i++) {
            CartItem item = orderItems.get(i);
            if (item.getPhone().getId() == phoneId) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }
}
