package com.ndex.clonemate.global.utils;

public class CommonUtils {

    public static final String REGEXP_COLOR_CODE = "^#(?:[0-9a-f]{2}){3}$";

    public enum Days {
        MON, TUE, WED, THU, FRI, SAT, SUN
    }

    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isEmpty(Character chr) {
        return isEmpty(chr.toString());
    }
}
