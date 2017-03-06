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
    private Object addToCartResponse;

    public AddToCartResponseTest() throws Throwable {
        clazz = Class.forName("com.expertsoft.controller.ProductsController$AddToCartResponse");
        Constructor constructor = clazz.getDeclaredConstructor(long.class, String.class);
        addToCartResponse = constructor.newInstance(4L, "100");
    }


    @Test
    public void setterGetterTest() throws Throwable {
        Method getItemsQuantity = clazz.getMethod("getItemsQuantity");
        assertEquals(4L, getItemsQuantity.invoke(addToCartResponse));
        Method getTotalPrice = clazz.getMethod("getTotalPrice");
        assertEquals("100", getTotalPrice.invoke(addToCartResponse));

        Constructor constructor = clazz.getDeclaredConstructor();
        addToCartResponse = constructor.newInstance();

        Method setItemsQuantity = clazz.getMethod("setItemsQuantity", long.class);
        setItemsQuantity.invoke(addToCartResponse, 4L);
        getItemsQuantity = clazz.getMethod("getItemsQuantity");
        assertEquals(4L, getItemsQuantity.invoke(addToCartResponse));

        Method setTotalPrice = clazz.getMethod("setTotalPrice", String.class);
        setTotalPrice.invoke(addToCartResponse, "4L");
        getTotalPrice = clazz.getMethod("getTotalPrice");
        assertEquals("4L", getTotalPrice.invoke(addToCartResponse));
    }
}
