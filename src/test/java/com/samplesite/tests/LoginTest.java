package com.samplesite.tests;

import com.samplesite.base.BaseTest;
import com.samplesite.pages.LoginPage;
import com.samplesite.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {ConfigReader.get("invalid.username"), ConfigReader.get("valid.password"),   "Your username is invalid!"},
                {ConfigReader.get("valid.username"),   ConfigReader.get("invalid.password"),  "Your password is invalid!"},
                {"",                                   ConfigReader.get("valid.password"),    "Your username is invalid!"},
                {ConfigReader.get("valid.username"),   "",                                   "Your password is invalid!"},
        };
    }

    @Test(priority = 0,
          description = "Verify successful login with valid credentials")
    public void verifySuccessfulLogin() {
        loginPage.login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );
        String flashMessage = loginPage.getFlashMessage();
        logger.info("Flash message received: {}", flashMessage);
        Assert.assertTrue(flashMessage.contains("You logged into a secure area!"),
                "Expected success message not found. Actual: " + flashMessage);
    }

    @Test(priority = 1,
          dataProvider = "invalidCredentials",
          description = "Verify login fails with invalid credentials")
    public void verifyLoginFailure(String username, String password, String expectedMessage) {
        loginPage.login(username, password);
        String flashMessage = loginPage.getFlashMessage();
        logger.info("Flash message received: {}", flashMessage);
        Assert.assertTrue(flashMessage.contains(expectedMessage),
                "Expected error message not found. Actual: " + flashMessage);
    }
}
