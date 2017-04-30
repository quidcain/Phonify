package com.expertsoft.service;


import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

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
    public void addCartItemTest() {
        Phone phone = createPhone("iPhone", 1, BigDecimal.ONE);
        when(phoneDao.get(1)).thenReturn(phone);
        phone = createPhone("Motorola", 2, BigDecimal.ONE);
        when(phoneDao.get(2)).thenReturn(phone);
        cartService.addOrderItem(1, 1);
        CartIndicator cartIndicator = cartService.getCart().getCartIndicator();
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
    public void getCartItemsTest() {
        List<CartItem> list = new ArrayList<>();
        cart.setCartItems(list);
        assertEquals(0, cartService.getCart().getCartItems().size());
        CartItem cartItem = new CartItem();
        list.add(cartItem);
        assertEquals(1, cartService.getCart().getCartItems().size());
    }

    @Test
    public void deleteCartItemTest() {
        Phone phone = createPhone("iPhone", 1, BigDecimal.ONE);
        when(phoneDao.get(1)).thenReturn(phone);
        cartService.addOrderItem(phone.getId(), 3);
        boolean noSuchElementExceptionThrown = false;
        try {
            cartService.deleteCartItem(2);
        } catch (NoSuchElementException e) {
            noSuchElementExceptionThrown = true;
        }
        assertTrue(noSuchElementExceptionThrown);
        CartIndicator cartIndicator = cartService.getCart().getCartIndicator();
        assertEquals(BigDecimal.valueOf(3), cartIndicator.getSubtotal());
        assertEquals(3, cartIndicator.getItemsQuantity());
        assertEquals(1, cart.getCartItems().size());

        cartService.deleteCartItem(1);
        assertEquals(0, cartIndicator.getItemsQuantity());
        assertEquals(BigDecimal.ZERO, cartIndicator.getSubtotal());
        assertEquals(0, cart.getCartItems().size());
    }

    @Test
    public void updateOrderItemsTest() {
        Phone iPhone = createPhone("iPhone",1, BigDecimal.ONE);
        Phone samsung = createPhone("Samsung",2,  BigDecimal.ONE);
        when(phoneDao.get(1)).thenReturn(iPhone);
        when(phoneDao.get(2)).thenReturn(samsung);
        cartService.addOrderItem(iPhone.getId(), 3);
        cartService.addOrderItem(samsung.getId(), 2);
        cartService.updateCartItems(new HashMap<Long, Long>() {{
            put(1L, 4L);
            put(2L, 1L);
        }});
        CartIndicator cartIndicator = cartService.getCart().getCartIndicator();
        assertEquals(BigDecimal.valueOf(5), cartIndicator.getSubtotal());
        assertEquals(5, cartIndicator.getItemsQuantity());
    }

    @Test
    public void getOrderTest() {
        assertEquals(cart, cartService.getCart());
    }

    private Phone createPhone(String model, long id, BigDecimal price) {
        Phone phone = new Phone();
        phone.setId(id);
        phone.setModel(model);
        phone.setColor("black");
        phone.setDisplaySize(4);
        phone.setPrice(price);
        return phone;
    }
}
