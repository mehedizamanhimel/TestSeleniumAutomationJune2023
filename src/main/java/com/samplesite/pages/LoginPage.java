package com.samplesite.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    private WebElement textfield_UserName;

    @FindBy(id = "password")
    private WebElement textfield_Password;

    @FindBy(css = "button[type='submit']")
    private WebElement button_Login;

    @FindBy(css = "#flash")
    private WebElement text_FlashMessage;

    public void provideUsername(String username) {
        logger.info("Entering username: {}", username);
        textfield_UserName.clear();
        textfield_UserName.sendKeys(username);
    }

    public void providePassword(String password) {
        logger.info("Entering password.");
        textfield_Password.clear();
        textfield_Password.sendKeys(password);
    }

    public void clickLoginButton() {
        logger.info("Clicking login button.");
        button_Login.click();
    }

    public String getFlashMessage() {
        return text_FlashMessage.getText().trim();
    }

    public LoginPage login(String username, String password) {
        provideUsername(username);
        providePassword(password);
        clickLoginButton();
        return this;
    }
}
