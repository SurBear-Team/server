package com.surbear.common.encryption;

import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
public class Aes256Util {

    private final String ALGORITHM;

    public Aes256Util(@Value("${giftishow.encryption.algorithm}") String algorithm) {
        this.ALGORITHM = algorithm;
    }


    public String encrypt(String tokenKey, String authorizationKey) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(tokenKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(authorizationKey.getBytes());
        return Base64.encodeBase64String(encrypted);
    }
}

