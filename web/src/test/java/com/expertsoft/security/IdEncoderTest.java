package com.expertsoft.security;


import org.junit.Test;

import java.security.GeneralSecurityException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IdEncoderTest {
    IdEncoder idEncoder;

    public IdEncoderTest() throws GeneralSecurityException {
        idEncoder = new IdEncoder("0123456789abcdefghijklmn");
    }

    @Test(expected = ShortEncryptedInputException.class)
    public void testUtils() throws GeneralSecurityException, ShortEncryptedInputException {
        String encryptedValue = idEncoder.encrypt(1L);
        System.out.println(encryptedValue);
        Long decryptedValue = idEncoder.decrypt(encryptedValue);
        System.out.println(decryptedValue);
        assertEquals(Long.valueOf(1), decryptedValue);
        assertNull(idEncoder.decrypt("1"));
    }
}
