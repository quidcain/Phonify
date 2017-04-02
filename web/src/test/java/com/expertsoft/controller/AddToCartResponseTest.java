package com.expertsoft.controller;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;


public class AddToCartResponseTest {
    AddToCartResponse response = new AddToCartResponse(4L, "100");

    @Test
    public void setterGetterTest() throws Throwable {
        assertEquals(4L, response.getItemsQuantity());
        assertEquals("100", response.getSubtotal());

        response = new AddToCartResponse();
        response.setItemsQuantity(4L);
        assertEquals(4L, response.getItemsQuantity());
        response.setSubtotal("4L");
        assertEquals("4L", response.getSubtotal());

    }
}
