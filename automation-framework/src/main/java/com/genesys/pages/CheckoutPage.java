package com.genesys.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.genesys.utils.WaitUtil;

public class CheckoutPage {

    WebDriver driver;
    WaitUtil wait;

    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueBtn = By.id("continue");

    By finishBtn = By.id("finish");
    By successMsg = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtil(driver);
    }

    public void enterDetails(String fName, String lName, String zip) {
        wait.waitForElementVisible(firstName).sendKeys(fName);
        wait.waitForElementVisible(lastName).sendKeys(lName);
        wait.waitForElementVisible(postalCode).sendKeys(zip);
        wait.waitForElementClickable(continueBtn).click();
    }

    public void finishOrder() {
        wait.waitForElementClickable(finishBtn).click();
    }

    public String getSuccessMessage() {
        return wait.waitForElementVisible(successMsg).getText();
    }
}