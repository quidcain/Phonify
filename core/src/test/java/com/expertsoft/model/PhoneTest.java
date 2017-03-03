package com.expertsoft.model;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;


/**
 * Created by stoat on 2/27/17.
 */
public class PhoneTest {
    Phone phone = new Phone("iPhone", "black", 4, BigDecimal.valueOf(800));
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
        Phone anotherPhone = new Phone("iPod", "white", 6, BigDecimal.valueOf(700));
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(800));
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(700));
        anotherPhone.setDisplaySize(4);
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(800));
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(700));
        anotherPhone.setDisplaySize(6);
        anotherPhone.setColor("black");
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(800));
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(700));
        anotherPhone.setDisplaySize(4);
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(800));
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(700));
        anotherPhone.setDisplaySize(6);
        anotherPhone.setColor("white");
        anotherPhone.setModel("iPhone");
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(800));
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(700));
        anotherPhone.setDisplaySize(4);
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(800));
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(700));
        anotherPhone.setDisplaySize(6);
        anotherPhone.setColor("black");
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(800));
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(700));
        anotherPhone.setDisplaySize(4);
        assertNotEquals(phone, anotherPhone);
        anotherPhone.setPrice(BigDecimal.valueOf(800));
        assertEquals(phone, anotherPhone);
    }
}
