package com.expertsoft.security;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IdCoderTest {
    IdCoder idCoder = new IdCoder("0123456789abcdefghijklmn");

    @Test(expected = IllegalArgumentException.class)
    public void testUtils() {
        String encryptedValue = idCoder.encrypt(1L);
        System.out.println(encryptedValue);
        Long decryptedValue = idCoder.decrypt(encryptedValue);
        System.out.println(decryptedValue);
        assertEquals(Long.valueOf(1), decryptedValue);
        idCoder.decrypt("1");
    }
}
