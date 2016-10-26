package ru.khannanovayrat.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Ayrat on 26.10.2016.
 */
public final class Token {
    private static SecureRandom random = new SecureRandom();

    public static String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}
