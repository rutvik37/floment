package com.example;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class login {

    Page page;

    public login(Page page) {
        this.page = page;
    }

    public void performlogin(String email, String password) throws InterruptedException {

        page.navigate("https://stag-webapp.incenti.ai/login");

        page.getByLabel("Email").fill(main.email);
        page.getByLabel("Password").fill(main.password);
        

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        Thread.sleep(3000);
        System.out.println("Login Done");
        
    }
}
