package com.cucumber.testng.utilities.misc_utils;


import java.util.Random;

public class StringUtililties {

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        char[] chars = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ".toCharArray();
        for (int i = 0; i < length; i++) {
            char c = chars[getRand(chars.length - 1)];
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Returns random integer between 0 and {@code max} inclusively.
     */
    public static int getRand(Integer max) {
        return new Random().nextInt(max + 1);
    }
}
