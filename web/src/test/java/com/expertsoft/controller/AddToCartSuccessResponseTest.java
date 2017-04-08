package com.expertsoft.controller;

import com.expertsoft.controller.addtocart.AddToCartSuccessResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AddToCartSuccessResponseTest {
    private AddToCartSuccessResponse response = new AddToCartSuccessResponse();

    @Test
    public void setterGetterTest() throws Throwable {
        response.setItemsQuantity(4L);
        assertEquals(4L, response.getItemsQuantity());
        response.setSubtotal("4L");
        assertEquals("4L", response.getSubtotal());
    }
}
