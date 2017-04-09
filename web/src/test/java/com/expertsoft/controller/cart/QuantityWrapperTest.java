package com.expertsoft.controller.cart;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuantityWrapperTest {
    private QuantityWrapper quantityWrapper = new QuantityWrapper();

    @Test
    public void setterGetterTest() throws Throwable {
        quantityWrapper.setQuantity("1");
        assertEquals("1", quantityWrapper.getQuantity());
    }
}
