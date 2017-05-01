package com.expertsoft.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.util.Base64;

public class IdCryptUtils {
    private static String algorithm = "DESede";
    private static Key key;
    private static Cipher cipher;
    static {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            key = factory.generateSecret(new DESedeKeySpec(new String("Phonify1234123gvHgVbjg0i").getBytes()));
            cipher = Cipher.getInstance(algorithm);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    public static String encrypt(Long input) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] inputBytes = input.toString().getBytes();
            byte[] outputBytes = cipher.doFinal(inputBytes);
            return Base64.getEncoder().encodeToString(outputBytes);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    public static Long decrypt(String input) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] inputBytes = Base64.getDecoder().decode(input);
            byte[] outputBytes = cipher.doFinal(inputBytes);
            return Long.parseLong(new String(outputBytes));
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
