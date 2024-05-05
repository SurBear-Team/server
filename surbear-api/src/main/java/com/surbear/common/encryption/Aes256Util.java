package com.surbear.common.encryption;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@Component
public class Aes256Util {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    public String encrypt(String tokenKey, String authorizationKey) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(tokenKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(authorizationKey.getBytes());
        return Base64.encodeBase64String(encrypted);
    }
}
