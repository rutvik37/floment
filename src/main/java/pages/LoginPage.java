package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

    private Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://stag-webapp.incenti.ai/login");
    }

    public void login(String email, String password) {
        page.getByLabel("Email").fill(email);
        page.getByLabel("Password").fill(password);
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Sign In")).click();
    }
}