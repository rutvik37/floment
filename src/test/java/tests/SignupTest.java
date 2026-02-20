package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.DataGenerator;

public class SignupTest extends BaseTest {

    @Test
    public void signupFlowTest() {

        String email = DataGenerator.generateRandomEmail();
        String password = DataGenerator.generateStrongPassword();

        SignupPage signup = new SignupPage(page);
        signup.navigate();
        signup.signup(email, password);

        MailPage mail = new MailPage(context);
        String otp = mail.fetchOtp(email);

        Assert.assertNotNull(otp, "OTP not received!");

        signup.enterOtp(otp);
    }
}