package com.example;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import java.nio.file.*;
import java.io.IOException;

public class signup {

  Page page;

  public signup(Page page) {
    this.page = page;
  }

  public void performsignup(String email, String password, String firstName, String lastName)
      throws InterruptedException {

    String otp = null;

    page.navigate("https://stag-webapp.incenti.ai/signup");
    System.out.println("Sign up page Opened");

    page.getByLabel("Email*").fill(main.email);
    page.getByLabel("Create Password*").fill(main.password);
    Thread.sleep(1000);

    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify Your Email")).click();
    System.out.println("OTP requested");

    Thread.sleep(3000);

BrowserContext context = page.context();
Page cc = context.newPage();

cc.navigate("https://maildrop.cc/");
Thread.sleep(3000);

cc.locator("#navbar").getByPlaceholder("view-this-mailbox").fill(main.email);
Thread.sleep(3000);

cc.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View Mailbox")).first().click();

boolean otpFound = false;
int maxAttempts = 3;

for (int attempt = 1; attempt <= maxAttempts; attempt++) {

    cc.waitForTimeout(4000);

    for (Frame frame : cc.frames()) {
        String bodyText;

        try {
            bodyText = frame.innerText("body");
        } catch (Exception e) {
            continue;
        }

        if (bodyText != null &&
           (bodyText.contains("stag-webapp") || bodyText.contains("Incenti"))) {

            otp = main.extractSixDigitOtp(bodyText);

            if (otp != null) {
                otpFound = true;
                break;
            }
        }
    }

    if (otpFound) {
        break;
    }

    try {
        cc.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Refresh Mailbox"))
          .click();
    } catch (Exception e) {
        
    }
}

if (!otpFound) {
    cc.close();
    context.close();

    System.out.println("OTP not sent, we can't find mail, please try again");
    System.exit(0);   
} else {
    
}

cc.close();

Thread.sleep(3000);
    if (otp != null && otp.length() == 6) {
      for (int i = 0; i < 6; i++) {
        String digit = String.valueOf(otp.charAt(i));
        page.getByRole(AriaRole.TEXTBOX).nth(i).fill(digit);
        Thread.sleep(300);
      }

      System.out.println("OTP entered Done");

      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify OTP")).click();
      System.out.println("OTP verified Done");
      Thread.sleep(3000);

      page.navigate("https://stag-webapp.incenti.ai/create-profile");
      Thread.sleep(2000);

      page.onDialog(dialog -> {
        System.out.println("Blocked dialog: " + dialog.message());
        dialog.dismiss();
      });

      try {
        Path baseDir = Paths.get("").toAbsolutePath();

        Path imagePath = Files.walk(baseDir, 5)
            .filter(p -> p.getFileName().toString().equals("test-image.jpg"))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("test-image.jpg not found in project folder!"));

        page.setInputFiles("input[type='file']", imagePath);
        System.out.println("Profile image uploaded Done");

      } catch (IOException e) {
        e.printStackTrace();
      }

      Thread.sleep(2000);

      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name*")).fill(main.firstName);
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name*")).fill(main.lastName);

      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
      System.out.println("complete profile data fillup Done");
      Thread.sleep(4000);

      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName(main.firstName).setExact(true)).click();

      page.getByText("Sign Out").click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes,Sign Out")).click();
      System.out.println("Sign out Done");
      Thread.sleep(2000);

    }

  }
}
