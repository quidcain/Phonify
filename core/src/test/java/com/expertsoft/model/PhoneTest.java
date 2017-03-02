package com.expertsoft.model;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;


/**
 * Created by stoat on 2/27/17.
 */
public class PhoneTest {
    Phone phone = new Phone(8_432_142,
            "iPhone", "black", 4, BigDecimal.valueOf(800));
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
        assertFalse(phone.equals(new Phone(8_432_143,
                "iPod", "white", 6, BigDecimal.valueOf(700))));
    }
}
