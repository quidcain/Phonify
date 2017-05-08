package com.expertsoft.security;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IdEncoderTest {
    IdEncoder idEncoder = new IdEncoder("0123456789abcdefghijklmn");

    @Test(expected = IllegalArgumentException.class)
    public void testUtils() {
        String encryptedValue = idEncoder.encrypt(1L);
        System.out.println(encryptedValue);
        Long decryptedValue = idEncoder.decrypt(encryptedValue);
        System.out.println(decryptedValue);
        assertEquals(Long.valueOf(1), decryptedValue);
        idEncoder.decrypt("1");
    }
}
