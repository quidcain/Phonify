package com.expertsoft.controller.form;

import org.junit.Test;

import static org.junit.Assert.*;


public class SpecificItemFormTest {
    private SpecificItemForm specificItemForm = new SpecificItemForm();

    @Test
    public void setterGetterTest() throws Throwable {
        specificItemForm.setId(4);
        assertEquals(4, specificItemForm.getId());
        specificItemForm.setQuantity("4");
        assertEquals("4", specificItemForm.getQuantity());
    }
}
