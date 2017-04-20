package com.expertsoft.controller.form;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ErrorMessageResponseTest {
    private ErrorMessageResponse response = new ErrorMessageResponse();

    @Test
    public void setterGetterTest() throws Throwable {
        response.setMessage("Error");
        assertEquals("Error", response.getMessage());
    }
}
