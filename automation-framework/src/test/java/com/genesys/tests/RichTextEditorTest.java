package com.genesys.tests;

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

public class RichTextEditorTest extends BaseTest {
	private static final Logger log = LoggerUtil.getLogger(RichTextEditorTest.class);
    @Test
    public void testRichTextEditor() {
    	log.info("===== Starting Rich Text Editor Test =====");
        WebDriver driver = DriverManager.getDriver();
        WaitUtil wait = new WaitUtil(driver);
        
        driver.get(Config.RICH_TEXT_URL);
        log.info("Opened Rich Text Editor page");
        
        log.info("Typing formatted text");
        By editor = By.xpath("//div[@role='textbox']");
        By boldBtn = By.xpath("//button[.//span[text()='Bold']]");
        By underlineBtn = By.xpath("//button[.//span[text()='Underline']]");

        try {
            wait.waitForElementClickable(
                    By.xpath("//button[contains(text(),'Allow all cookies')]")
            ).click();
        } catch (Exception ignored) {}

        wait.waitForElementVisible(editor).click();

        wait.waitForElementClickable(boldBtn).click();
        wait.waitForElementVisible(editor).sendKeys("Automation ");
        log.info("Entered bold text: Automation");
        wait.waitForElementClickable(boldBtn).click();

        wait.waitForElementClickable(underlineBtn).click();
        wait.waitForElementVisible(editor).sendKeys("Test ");
        log.info("Entered Underline text: Test");
        wait.waitForElementClickable(underlineBtn).click();

        wait.waitForElementVisible(editor).sendKeys("Example");
        log.info("Entered normal text: Example");

        String text = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return arguments[0].innerText;", 
                        wait.waitForElementVisible(editor));

        Assert.assertTrue(text.contains("Automation"));
        Assert.assertTrue(text.contains("Test"));
        Assert.assertTrue(text.contains("Example"));
        log.info("Rich Text validation successful");
        log.info("===== Test Completed Successfully =====");
    }
}