package com.expertsoft.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.util.Base64;

@Component
public class IdCoder {
    private String algorithm = "DESede";
    private Key key;
    private Cipher cipher;

    public IdCoder(@Value("${idcoder.key}") String keySeed) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            key = factory.generateSecret(new DESedeKeySpec(keySeed.getBytes()));
            cipher = Cipher.getInstance(algorithm);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    public String encrypt(Long input) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] inputBytes = input.toString().getBytes();
            byte[] outputBytes = cipher.doFinal(inputBytes);
            return Base64.getEncoder().encodeToString(outputBytes);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    public Long decrypt(String input) {
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
