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
    }

    @Test
    public void setterGetterTest() {
        phone.setId(123L);
        assertEquals(123L, phone.getId());

        phone.setModel("iPhone");
        assertEquals("iPhone", phone.getModel());

        phone.setColor("black");
        assertEquals("black", phone.getColor());

        phone.setDisplaySize(4);
        assertEquals(4, phone.getDisplaySize());

        phone.setPrice(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, phone.getPrice());

        phone = new Phone("iPhone", "black", 4, BigDecimal.ONE);
        assertEquals("iPhone", phone.getModel());
        assertEquals("black", phone.getColor());
        assertEquals(4, phone.getDisplaySize());
        assertEquals(BigDecimal.ONE, phone.getPrice());
    }
}
