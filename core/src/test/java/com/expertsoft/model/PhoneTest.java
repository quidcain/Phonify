package com.expertsoft.model;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;


public class PhoneTest {
    private Phone phone = new Phone();

    @Test
    public void equalsNull() {
        assertFalse(phone.equals(null));
    }

    @Test
    public void equalsAnotherClass() {
        assertFalse(phone.equals("iphone"));
    }

    @Test
    public void equalsPhone() {
        assertEquals(phone, phone);
        Phone anotherPhone = new Phone();
        anotherPhone.setId(2L);
        assertNotEquals(phone, anotherPhone);
    }

    @Test
    public void setterGetterTest() {
        phone.setId(123L);
        assertEquals(new Long(123L), phone.getId());

        phone.setModel("iPhone");
        assertEquals("iPhone", phone.getModel());

        phone.setColor("black");
        assertEquals("black", phone.getColor());

        phone.setDisplaySize(4);
        assertEquals(4, phone.getDisplaySize());

        phone.setPrice(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, phone.getPrice());

        phone.setLength(4);
        assertEquals(4, phone.getLength());

        phone.setWidth(4);
        assertEquals(4, phone.getWidth());

        phone.setCamera(4);
        assertEquals(4, phone.getCamera());
    }
}
