package com.expertsoft.service;


import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {
    private Cart cart = new Cart();

    @Mock
    private PhoneDao phoneDao;

    private CartServiceImpl cartService;

    @Before
    public void init() {
        cartService = new CartServiceImpl(phoneDao, cart);
    }

    @Test
    public void addOrderItemTest() {
        Phone phone = createPhone("iPhone");
        when(phoneDao.get(1)).thenReturn(phone);
        phone = createPhone("Motorola");
        when(phoneDao.get(2)).thenReturn(phone);
        cartService.addOrderItem(1, 1);
        CartIndicator cartIndicator = cartService.getCartIndicator();
        assertEquals(1, cartIndicator.getItemsQuantity());
        assertEquals(BigDecimal.ONE, cartIndicator.getSubtotal());
        cartService.addOrderItem(1, 2);
        assertEquals(3, cartIndicator.getItemsQuantity());
        assertEquals(BigDecimal.valueOf(3), cartIndicator.getSubtotal());
        cartService.addOrderItem(2, 3);
        assertEquals(6, cartIndicator.getItemsQuantity());
        assertEquals(BigDecimal.valueOf(6), cartIndicator.getSubtotal());
    }

    @Test
    public void getOrderItemsTest() {
        List<CartItem> list = new ArrayList<>();
        cart.setCartItems(list);
        assertEquals(0, cartService.getCartItems().size());
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        list.add(cartItem);
        assertEquals(1, cartService.getCartItems().size());
    }

    @Test
    public void deleteOrderItemTest() {
        Phone phone = createPhone("iPhone");
        when(phoneDao.get(1)).thenReturn(phone);
        cartService.addOrderItem(phone.getId(), 3);
        boolean noSuchElementExceptionThrown = false;
        try {
            cartService.deleteCartItem(2);
        } catch (NoSuchElementException e) {
            noSuchElementExceptionThrown = true;
        }
        assertTrue(noSuchElementExceptionThrown);
        CartIndicator cartIndicator = cartService.getCartIndicator();
        assertEquals(BigDecimal.valueOf(3), cartIndicator.getSubtotal());
        assertEquals(3, cartIndicator.getItemsQuantity());
        assertEquals(1, cart.getCartItems().size());

        cartService.deleteCartItem(1);
        assertEquals(0, cartIndicator.getItemsQuantity());
        assertEquals(BigDecimal.ZERO, cartIndicator.getSubtotal());
        assertEquals(0, cart.getCartItems().size());
    }

    @Test
    public void updateOrderItemTest() {
        Phone phone = createPhone("iPhone");
        when(phoneDao.get(1)).thenReturn(phone);
        cartService.addOrderItem(phone.getId(), 3);
        cartService.updateCartItem(phone.getId(), 4);
        CartIndicator cartIndicator = cartService.getCartIndicator();
        assertEquals(BigDecimal.valueOf(4), cartIndicator.getSubtotal());
        assertEquals(4, cartIndicator.getItemsQuantity());
        cartService.updateCartItem(phone.getId(), 2);
        assertEquals(BigDecimal.valueOf(2), cartIndicator.getSubtotal());
        assertEquals(2, cartIndicator.getItemsQuantity());
        cartService.updateCartItem(phone.getId(), 2);
        assertEquals(BigDecimal.valueOf(2), cartIndicator.getSubtotal());
        assertEquals(2, cartIndicator.getItemsQuantity());
    }

    @Test
    public void getOrderTest() {
        assertEquals(cart, cartService.getCart());
    }

    private Phone createPhone(String model) {
        Phone phone = new Phone();
        phone.setId(1);
        phone.setModel(model);
        phone.setColor("black");
        phone.setDisplaySize(4);
        phone.setPrice(BigDecimal.ONE);
        return phone;
    }
}
