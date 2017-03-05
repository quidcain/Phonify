package com.expertsoft.controller;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * Created by stoat on 3/6/17.
 */
public class AddToCartResponseTest {
    private Class clazz;
    private Object addToCartResponseTest;

    public AddToCartResponseTest() throws Throwable {
        clazz = Class.forName("com.expertsoft.controller.ProductsController$AddToCartResponse");
        Constructor constructor = clazz.getDeclaredConstructor(long.class, String.class);
        constructor.setAccessible(true);
        addToCartResponseTest = constructor.newInstance(4L, "100");
    }


    @Test
    public void setterGetterTest() throws Throwable {
        Method getItemsQuantity = clazz.getMethod("getItemsQuantity");
        assertEquals(4L, getItemsQuantity.invoke(addToCartResponseTest));

        Method getTotalPrice = clazz.getMethod("getTotalPrice");
        assertEquals("100", getTotalPrice.invoke(addToCartResponseTest));
    }
}
