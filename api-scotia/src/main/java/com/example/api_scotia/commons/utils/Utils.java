package com.example.api_scotia.commons.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

public class Utils {

    public static String generateRandomId(String prefix){
        return prefix +
                RandomStringUtils.random(3, 0, 0, Boolean.FALSE, Boolean.TRUE, null, new SecureRandom());
    }

    private Utils(){}
}
