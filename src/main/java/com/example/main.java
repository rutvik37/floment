package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

import com.microsoft.playwright.*;

public class main {

    public static String email = generateRandomEmail();
    public static String password = generateStrongPassword();
    public static String firstName = generateRandomName(4);
    public static String lastName = generateRandomName(3);
    public static String taskName1 = generateRandomName(5);
    public static String taskName2 = generateRandomName(5);
    public static String description1 = generateRandomName(12);
    public static String description2 = generateRandomName(12);

    public static void main(String[] args) throws InterruptedException {

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext context = browser.newContext();

            Page page = context.newPage();

            page.navigate("https://stag-webapp.incenti.ai/");
            Thread.sleep(1000);

            signup signupFlow = new signup(page);
            signupFlow.performsignup(main.email, main.password, main.firstName, main.lastName);

            login loginFlow = new login(page);
            loginFlow.performlogin(main.email, main.password);

            todo todoFlow = new todo(page);
            todoFlow.performTodoFlow();

            page.waitForTimeout(1000);

            browser.close();
        }
    }

    public static String extractSixDigitOtp(String text) {
        if (text == null)
            return null;
        Pattern p = Pattern.compile("\\b(\\d{6})\\b");
        Matcher m = p.matcher(text);
        if (m.find()) {
            return m.group(1);
        }
        return null;

    }

    public static String generateRandomEmail() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 8 + random.nextInt(3);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString() + "@maildrop.cc";
    }

    public static String generateStrongPassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*";
        String all = upper + lower + digits + special;

        Random random = new Random();

        StringBuilder password = new StringBuilder();
        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(special.charAt(random.nextInt(special.length())));

        for (int i = 0; i < 5; i++) {
            password.append(all.charAt(random.nextInt(all.length())));
        }

        return password.toString();
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
