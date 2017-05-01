package com.expertsoft.security;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IdCryptUtilsTest {
    @Test(expected = IllegalArgumentException.class)
    public void testUtils() {
        String encryptedValue = IdCryptUtils.encrypt(1L);
        System.out.println(encryptedValue);
        Long decryptedValue = IdCryptUtils.decrypt(encryptedValue);
        System.out.println(decryptedValue);
        assertEquals(Long.valueOf(1), decryptedValue);
        IdCryptUtils.decrypt("1");
    }
}
