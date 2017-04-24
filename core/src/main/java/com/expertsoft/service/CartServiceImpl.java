package com.expertsoft.service;

import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
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
        Phone phone = null;
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
            CartItem item = new CartItem(phone, cart, quantity);
            cart.getCartItems().add(item);
        }
        cart.setSubtotal(cart.getSubtotal().add(
                phone.getPrice().multiply(BigDecimal.valueOf(quantity))));
        CartIndicator cartIndicator = cart.getCartIndicator();
        cartIndicator.setItemsQuantity(cartIndicator.getItemsQuantity() + quantity);
        cartIndicator.setSubtotal(cart.getSubtotal());
    }

    @Override
    public CartIndicator getCartIndicator() {
        return cart.getCartIndicator();
    }

    @Override
    public List<CartItem> getCartItems() {
        return cart.getCartItems();
    }

    @Override
    public void deleteCartItem(long phoneId) {
        int itemIndex = getItemIndex(phoneId);
        List<CartItem> orderItems = cart.getCartItems();
        CartItem item = orderItems.get(itemIndex);
        cart.setSubtotal(cart.getSubtotal().subtract(
                item.getPhone().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))));
        CartIndicator cartIndicator = cart.getCartIndicator();
        cartIndicator.setItemsQuantity(cartIndicator.getItemsQuantity() - item.getQuantity());
        cartIndicator.setSubtotal(cart.getSubtotal());
        orderItems.remove(itemIndex);
    }

    @Override
    public void updateCartItem(long phoneId, long newQuantity) {
        int itemIndex = getItemIndex(phoneId);
        CartItem item = cart.getCartItems().get(itemIndex);
        long quantityDifference = newQuantity - item.getQuantity();
        if (quantityDifference == 0)
            return;
        item.setQuantity(newQuantity);
        cart.setSubtotal(cart.getSubtotal().add(
                item.getPhone().getPrice().multiply(BigDecimal.valueOf(quantityDifference))));
        CartIndicator cartIndicator = cart.getCartIndicator();
        cartIndicator.setItemsQuantity(cartIndicator.getItemsQuantity() + quantityDifference);
        cartIndicator.setSubtotal(cart.getSubtotal());
    }

    public Cart getCart() {
        return cart;
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
