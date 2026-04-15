package com.genesys.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.genesys.utils.WaitUtil;

public class LoginPage {

    WebDriver driver;
    WaitUtil wait;

    By username = By.id("user-name");
    By password = By.id("password");
    By loginBtn = By.id("login-button");
    By errorMsg = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtil(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void enterCredentials(String user, String pass) {
        wait.waitForElementVisible(username).sendKeys(user);
        wait.waitForElementVisible(password).sendKeys(pass);
    }

    public void clickLogin() {
        wait.waitForElementClickable(loginBtn).click();
    }

    public String getErrorMessage() {
        return wait.waitForElementVisible(errorMsg).getText();
    }
}