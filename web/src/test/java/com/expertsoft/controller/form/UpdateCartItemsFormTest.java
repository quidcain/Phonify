package com.expertsoft.controller.form;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class UpdateCartItemsFormTest {
    private UpdateCartItemsForm updateCartItemsForm = new UpdateCartItemsForm();

    @Test
    public void setterGetterTest() throws Throwable {
        Map<Long, QuantityForm> map = new HashMap<>();
        updateCartItemsForm.setItems(map);
        assertEquals(map, updateCartItemsForm.getItems());
    }
}
