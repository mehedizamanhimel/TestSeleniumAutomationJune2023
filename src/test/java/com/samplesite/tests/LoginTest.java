package com.samplesite.tests;

import Web.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;
    String baseUrl = "";
    String userName_Positive= "abcd";
    String userName_Negative= "abcde";
    String userName_Blank= "";
    String passWord_Positive= "1234";
    String passWord_Negative= "12345";
    String passWord_Blank= "";
    String ExpectedMessage = "Login done successfully";
    String ExpectedMessage_NoUserName = "Please provide username";
    String ExpectedMessage_NoPassword = "Please provide password";
    String ExpectedMessage_WrongUserName = "Wrong username";
    String ExpectedMessage_WrongPassword = "Wrong password";

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println("Test started successfully");
    }


    @Test (priority = 0 , description = "Verify that login is failed with invalid username & valid password")
    public void verifyLoginWorking_Negative_Wrong_Username(){
        LoginPage loginPage = new LoginPage(driver);

        driver.get(baseUrl);
        loginPage.provide_Username(userName_Negative);
        loginPage.provide_Password(passWord_Positive);
        loginPage.clickTheLoginButton();
        Assert.assertEquals(loginPage.return_LoginConsent(), ExpectedMessage_WrongUserName);

    }

    @Test (priority = 1 , description = "Verify that login is failed with valid username & invalid password")
    public void verifyLoginWorking_Negative_Wrong_Password(){
        LoginPage loginPage = new LoginPage(driver);

        driver.get(baseUrl);
        loginPage.provide_Username(userName_Positive);
        loginPage.provide_Password(passWord_Negative);
        loginPage.clickTheLoginButton();
        Assert.assertEquals(loginPage.return_LoginConsent(), ExpectedMessage_WrongPassword);

    }

    @Test (priority = 2 , description = "Verify that login is failed with blank username & valid password")
    public void verifyLoginWorking_Negative_Blank_Username(){
        LoginPage loginPage = new LoginPage(driver);

        driver.get(baseUrl);
        loginPage.provide_Username(userName_Blank);
        loginPage.provide_Password(passWord_Positive);
        loginPage.clickTheLoginButton();
        Assert.assertEquals(loginPage.return_LoginConsent(), ExpectedMessage_NoUserName);

    }

    @Test (priority = 3 , description = "Verify that login is failed with valid username & blank password")
    public void verifyLoginWorking_Negative_Blank_Password(){
        LoginPage loginPage = new LoginPage(driver);

        driver.get(baseUrl);
        loginPage.provide_Username(userName_Positive);
        loginPage.provide_Password(passWord_Blank);
        loginPage.clickTheLoginButton();
        Assert.assertEquals(loginPage.return_LoginConsent(), ExpectedMessage_NoPassword);

    }

    @Test (priority = 4, description = "Verify that login is working properly with valid username & password")
    public void verifyLoginWorking_Positive(){
        LoginPage loginPage = new LoginPage(driver);

        driver.get(baseUrl);
        loginPage.provide_Username(userName_Positive);
        loginPage.provide_Password(passWord_Positive);
        loginPage.clickTheLoginButton();
        Assert.assertEquals(loginPage.return_LoginConsent(), ExpectedMessage);

    }

    @AfterTest
    public void TearDown(){
        driver.close();
        System.out.println("Test ended successfully");
    }

}
