package com.expertsoft.controller;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;


public class AddToCartRequestTest {
    private AddToCartRequest request = new AddToCartRequest();

    @Test
    public void setterGetterTest() throws Throwable {
        request.setId(4L);
        assertEquals(4L, request.getId());
        request.setQuantity("4L");
        assertEquals("4L", request.getQuantity());
    }
}
