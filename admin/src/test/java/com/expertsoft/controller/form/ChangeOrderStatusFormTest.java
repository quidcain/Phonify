package com.expertsoft.controller.form;


import com.expertsoft.model.Order;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ChangeOrderStatusFormTest {
    ChangeOrderStatusForm changeOrderStatusForm = new ChangeOrderStatusForm();

    @Test
    public void setterGetterTest() throws Throwable {
        Map<Long, Order.Status> map = new HashMap<>();
        changeOrderStatusForm.setStatuses(map);
        assertEquals(map, changeOrderStatusForm.getStatuses());
    }
}
