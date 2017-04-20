package com.expertsoft.controller.form;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenericQuantityFormTest {
    private GenericQuantityForm genericQuantityForm = new GenericQuantityForm();

    @Test
    public void setterGetterTest() throws Throwable {
        genericQuantityForm.setQuantity("4");
        assertEquals("4", genericQuantityForm.getQuantity());
    }
}
