package com.expertsoft.controller;

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;


/**
 * Created by stoat on 3/6/17.
 */
public class AddToCartRequestTest {
    private Class clazz;
    private Object addToCartRequestTest;

    public AddToCartRequestTest() throws Throwable {
        clazz = Class.forName("com.expertsoft.controller.ProductsController$AddToCartRequest");
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        addToCartRequestTest = constructor.newInstance();
    }


    @Test
    public void setterGetterTest() throws Throwable {
        Method setPhoneId = clazz.getMethod("setPhoneId", long.class);
        setPhoneId.invoke(addToCartRequestTest, 4L);
        Method getPhoneId = clazz.getMethod("getPhoneId");
        assertEquals(4L, getPhoneId.invoke(addToCartRequestTest));

        Method setQuantity = clazz.getMethod("setQuantity", long.class);
        setQuantity.invoke(addToCartRequestTest, 4L);
        Method getQuantity = clazz.getMethod("getQuantity");
        assertEquals(4L, getQuantity.invoke(addToCartRequestTest));
    }
}
