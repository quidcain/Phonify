package com.expertsoft.controller.form;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuantityFormTest {
    private QuantityForm quantityForm = new QuantityForm();

    @Test
    public void setterGetterTest() throws Throwable {
        quantityForm.setQuantity(4L);
        assertEquals(Long.valueOf(4), quantityForm.getQuantity());
    }
}
