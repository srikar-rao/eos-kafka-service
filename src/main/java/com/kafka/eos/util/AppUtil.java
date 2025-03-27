package com.kafka.eos.util;

public final class AppUtil {

    // Private constructor to prevent instantiation
    private AppUtil() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    // Static method to check if a number is prime
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;

        if (number % 2 == 0 || number % 3 == 0) return false;

        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }

        return true;
    }
}
