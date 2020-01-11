package com.lootos.www;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringHash {
    public static String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");    // создаем обьект для хеш суммы и указываем какой алогоритм будет использоваться
            byte[] hash = digest.digest(input.getBytes("UTF-8"));   // расчитываем хеш сумму
            String hex;
            StringBuffer stringHex = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) stringHex.append('0');
                stringHex.append(hex);
            }
            return stringHex.toString();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
