package utils;

import java.util.Random;

public class DataGenerator {

    public static String generateRandomEmail() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString() + "@maildrop.cc";
    }

    public static String generateStrongPassword() {
        return "Pass@" + new Random().nextInt(9999);
    }

    public static String generateRandomName(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }
}