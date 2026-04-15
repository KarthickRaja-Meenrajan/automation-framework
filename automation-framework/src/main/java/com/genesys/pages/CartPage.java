package com.genesys.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.genesys.utils.WaitUtil;

public class CartPage {

    WebDriver driver;
    WaitUtil wait;

    By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtil(driver);
    }

    public void clickCheckout() {
        wait.waitForElementClickable(checkoutBtn).click();
    }
}