package com.expertsoft.controller;

import com.expertsoft.controller.addtocart.AddToCartErrorResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddToCartErrorResponseTest {
    private AddToCartErrorResponse response = new AddToCartErrorResponse();

    @Test
    public void setterGetterTest() throws Throwable {
        response.setMessage("Error");
        assertEquals("Error", response.getMessage());
    }
}
