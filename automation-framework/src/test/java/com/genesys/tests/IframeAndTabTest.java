package com.genesys.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.genesys.base.BaseTest;
import com.genesys.base.DriverManager;
import com.genesys.utils.Config;
import com.genesys.utils.LoggerUtil;
import com.genesys.utils.WaitUtil;

public class IframeAndTabTest extends BaseTest {
	private static final Logger log = LoggerUtil.getLogger(IframeAndTabTest.class);

    @Test
    public void testIframeAndTabHandling() throws InterruptedException {
    	log.info("===== Starting Iframe and Tab Test =====");

        WebDriver driver = DriverManager.getDriver();
        WaitUtil wait = new WaitUtil(driver);
        driver.get(Config.IFRAME_URL);
        log.info("Opened Guru99 page");
        String parentWindow = driver.getWindowHandle();

        wait.waitForElementPresent(By.id("a077aa5e"));
        log.info("Switching to iframe");
        driver.switchTo().frame("a077aa5e");

        wait.waitForElementClickable(By.tagName("img")).click();
        log.info("Clicked image inside iframe");
        Thread.sleep(2000);

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }

        driver.switchTo().window(parentWindow);
        driver.switchTo().defaultContent();
        log.info("Handled new tab and switched back");
        
        log.info("Entering email and submitting");
        wait.waitForElementVisible(By.xpath("//input[@type='text']"))
                .sendKeys("test@gmail.com");

        wait.waitForElementClickable(By.id("philadelphia-field-submit")).click();

        Alert alert = driver.switchTo().alert();

        Assert.assertTrue(alert.getText().contains("Successfully"),
                "Alert validation failed");
        log.info("Alert validated successfully");
        alert.accept();
        
        String mainWindow = driver.getWindowHandle();

        wait.waitForElementClickable(By.linkText("Selenium")).click();
        log.info("Navigating to Tooltip page");
        wait.waitForElementClickable(By.linkText("Tooltip")).click();

        Thread.sleep(2000);

        for (String win : driver.getWindowHandles()) {
            if (!win.equals(mainWindow)) {
                driver.switchTo().window(win);
                break;
            }
        }
        Assert.assertTrue(
                wait.waitForElementVisible(
                        By.xpath("//a[contains(text(),'Download')]")
                ).isDisplayed(),
                "Download button not visible"
        );
        log.info("Tooltip page validated successfully");
        log.info("===== Test Completed Successfully =====");
    }
}