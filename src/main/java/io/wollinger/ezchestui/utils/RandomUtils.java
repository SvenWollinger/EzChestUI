package io.wollinger.ezchestui.utils;

import java.time.Instant;
import java.util.Random;

public class RandomUtils {

    public static String randomKey() {
        final int LENGTH = 10;
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString() + Instant.now().getEpochSecond();
    }
}
