package com.expertsoft.controller.form;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class EntireItemsFormTest {
    private EntireItemsForm  entireItemsForm = new EntireItemsForm();

    @Test
    public void setterGetterTest() throws Throwable {
        Map<Long, GenericQuantityForm> map = new HashMap<>();
        entireItemsForm.setItems(map);
        assertEquals(map, entireItemsForm.getItems());
    }
}
