package com.surbear.oauth.service;

import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.errorcode.BasicErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CertificationNumberProvider {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+";
    private static final SecureRandom random = new SecureRandom();

    public String generateRandomCertificationNumber() {
        int randomLength = random.nextInt(7) + 12;
        return generateTemporaryCertificationNumber(randomLength);
    }

    private String generateTemporaryCertificationNumber(int length) {
        if (length < 8) {
            throw new ProcessErrorCodeException(BasicErrorCode.INTERNAL_SERVER_ERROR);
        }

        List<Character> numChars = new ArrayList<>(length);

        numChars.add(LETTERS.charAt(random.nextInt(LETTERS.length())));
        numChars.add(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        numChars.add(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        for (int i = 3; i < length; i++) {
            char nextChar = (char) (random.nextInt(94) + 33);
            numChars.add(nextChar);
        }

        Collections.shuffle(numChars, random);

        StringBuilder sb = new StringBuilder(length);
        for (char c : numChars) {
            sb.append(c);
        }
        return sb.toString();
    }
}
