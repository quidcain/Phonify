package com.expertsoft.controller.form;

import org.junit.Test;

import static org.junit.Assert.*;


public class AddToCartFormTest {
    private AddToCartForm addToCartForm = new AddToCartForm();

    @Test
    public void setterGetterTest() throws Throwable {
        addToCartForm.setId(4);
        assertEquals(4, addToCartForm.getId());
        addToCartForm.setQuantity("4L");
        assertEquals("4L", addToCartForm.getQuantity());
    }
}
