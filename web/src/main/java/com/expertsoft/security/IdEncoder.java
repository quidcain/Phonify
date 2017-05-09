package com.expertsoft.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64;

@Component
public class IdEncoder {
    private String algorithm = "DESede";
    private Key key;
    private Cipher cipher;

    public IdEncoder(@Value("${idcoder.key}") String keySeed) throws GeneralSecurityException {
        SecretKeyFactory factory = null;
        factory = SecretKeyFactory.getInstance("DESede");
        key = factory.generateSecret(new DESedeKeySpec(keySeed.getBytes()));
        cipher = Cipher.getInstance(algorithm);
    }

    public String encrypt(Long input) throws GeneralSecurityException{
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] inputBytes = input.toString().getBytes();
        byte[] outputBytes = cipher.doFinal(inputBytes);
        return Base64.getEncoder().encodeToString(outputBytes);
    }

    public Long decrypt(String input) throws GeneralSecurityException, ShortEncryptedInputException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] inputBytes;
        try {
            inputBytes = Base64.getDecoder().decode(input);
        } catch (IllegalArgumentException e) {
            throw new ShortEncryptedInputException(e);
        }
        byte[] outputBytes = cipher.doFinal(inputBytes);
        return Long.parseLong(new String(outputBytes));
    }
}
