package com.surbear.common.encryption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class Aes256Util {

    private final String ALGORITHM;

    public Aes256Util(@Value("${giftishow.encryption.algorithm}") String algorithm) {
        this.ALGORITHM = algorithm;
    }


    public String encrypt(String tokenKey, String authorizationKey) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(authorizationKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(tokenKey.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
}

