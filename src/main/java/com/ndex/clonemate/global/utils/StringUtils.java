package com.ndex.clonemate.global.utils;

import java.util.Random;

public class StringUtils {
    private static int LOWER_A = 97;
    private static int LOWER_Z = 122;
    private static int UPPER_A = 65;
    private static int UPPER_Z = 90;
    private static int CHAR_TO_INT_ZERO = 48;
    private static int CHAR_TO_INT_NINE = 57;

    public static String generateRandomString(int length) {
        Random random = new Random();
        return random.ints(CHAR_TO_INT_ZERO, LOWER_Z + 1)
                .filter(i -> (i < CHAR_TO_INT_NINE)
                        || (i >= UPPER_A && i <= UPPER_Z)
                        || (i > LOWER_A))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
