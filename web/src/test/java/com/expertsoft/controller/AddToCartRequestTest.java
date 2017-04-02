package com.expertsoft.controller;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;


public class AddToCartRequestTest {
    AddToCartRequest request = new AddToCartRequest(4L, "100");


    @Test
    public void setterGetterTest() throws Throwable {
        assertEquals(4L, request.getId());
        assertEquals("100", request.getQuantity());

        request = new AddToCartRequest();
        request.setId(4L);
        assertEquals(4L, request.getId());
        request.setQuantity("4L");
        assertEquals("4L", request.getQuantity());
    }
}
