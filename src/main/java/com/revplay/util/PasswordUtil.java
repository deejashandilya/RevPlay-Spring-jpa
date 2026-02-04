package com.revplay.util;


import java.util.Base64;

public class PasswordUtil {

    public static String encrypt(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static boolean matches(String raw, String encoded) {
        return encrypt(raw).equals(encoded);
    }
}
