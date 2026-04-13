package com.genesys.tests;

import org.slf4j.Logger;
import com.genesys.utils.LoggerUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.genesys.base.BaseTest;
import com.genesys.base.DriverManager;
import com.genesys.pages.LoginPage;
import com.genesys.utils.Config;
import com.genesys.utils.WaitUtil;

public class LoginErrorTest extends BaseTest {
	private static final Logger log = LoggerUtil.getLogger(LoginErrorTest.class);

    @Test
    public void testLoginErrorAndFooter() {
    	log.info("===== Starting Login Error Test =====");
        WebDriver driver = DriverManager.getDriver();
        WaitUtil wait = new WaitUtil(driver);

        LoginPage login = new LoginPage(driver);

        login.open(Config.SAUCE_DEMO_URL);

        log.info("Clicking login without credentials");
        login.clickLogin();

        Assert.assertTrue(
                login.getErrorMessage().contains("Username is required"),
                "Error message mismatch"
        );
        log.info("Error message validated");

        login.enterCredentials("standard_user", "secret_sauce");
        login.clickLogin();
        log.info("Logged in with valid credentials");

        By footer = By.className("footer_copy");

        log.info("Scrolling to footer");
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                wait.waitForElementVisible(footer)
        );

        String footerText = wait.waitForElementVisible(footer).getText();

        Assert.assertTrue(footerText.contains("2026"), "Year missing");
        Assert.assertTrue(footerText.contains("Terms"), "Terms missing");
        log.info("Footer validated successfully");
        log.info("===== Test Completed Successfully =====");
    }
}