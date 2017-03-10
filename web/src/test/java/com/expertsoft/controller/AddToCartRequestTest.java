package com.expertsoft.controller;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;


public class AddToCartRequestTest {
    private Class clazz;
    private Object addToCartRequest;

    public AddToCartRequestTest() throws Throwable {
        clazz = Class.forName("com.expertsoft.controller.ProductsController$AddToCartRequest");
        Constructor constructor = clazz.getDeclaredConstructor(long.class, String.class);
        addToCartRequest = constructor.newInstance(4L, "100");
    }


    @Test
    public void setterGetterTest() throws Throwable {
        Method getId = clazz.getMethod("getId");
        assertEquals(4L, getId.invoke(addToCartRequest));
        Method getQuantity = clazz.getMethod("getQuantity");
        assertEquals("100", getQuantity.invoke(addToCartRequest));

        Constructor constructor = clazz.getDeclaredConstructor();
        addToCartRequest = constructor.newInstance();

        Method setId = clazz.getMethod("setId", long.class);
        setId.invoke(addToCartRequest, 4L);
        getId = clazz.getMethod("getId");
        assertEquals(4L, getId.invoke(addToCartRequest));

        Method setQuantity = clazz.getMethod("setQuantity", String.class);
        setQuantity.invoke(addToCartRequest, "4L");
        getQuantity = clazz.getMethod("getQuantity");
        assertEquals("4L", getQuantity.invoke(addToCartRequest));
    }
}
