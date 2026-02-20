package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {

        LoginPage login = new LoginPage(page);
        login.navigate();
        login.login("test@maildrop.cc", "Password@123");
    }
}