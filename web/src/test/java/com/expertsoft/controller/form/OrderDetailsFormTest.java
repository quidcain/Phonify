package com.expertsoft.controller.form;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderDetailsFormTest {
    private OrderDetailsForm orderDetailsForm = new OrderDetailsForm();

    @Test
    public void setterGetterTest() {
        orderDetailsForm.setFirstName("John");
        assertEquals("John", orderDetailsForm.getFirstName());

        orderDetailsForm.setLastName("Doe");
        assertEquals("Doe", orderDetailsForm.getLastName());

        orderDetailsForm.setDeliveryAddress("1234 Main Street Anytown, USA 123456");
        assertEquals("1234 Main Street Anytown, USA 123456", orderDetailsForm.getDeliveryAddress());

        orderDetailsForm.setContactPhoneNo("1-800-354-0387");
        assertEquals("1-800-354-0387", orderDetailsForm.getContactPhoneNo());

        orderDetailsForm.setAdditionalInfo("Lorem ipsum");
        assertEquals("Lorem ipsum", orderDetailsForm.getAdditionalInfo());

    }
}
