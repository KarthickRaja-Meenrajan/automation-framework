package com.genesys.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.genesys.utils.WaitUtil;

public class InventoryPage {

    WebDriver driver;
    WaitUtil wait;

    By backpackAddBtn = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
    By jacketAddBtn = By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']");

    By cartBadge = By.className("shopping_cart_badge");
    By cartIcon = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtil(driver);
    }

    public void addItemsToCart() {
        wait.waitForElementClickable(backpackAddBtn).click();
        wait.waitForElementClickable(jacketAddBtn).click();
    }

    public int getCartCount() {
        String count = wait.waitForElementVisible(cartBadge).getText();
        return Integer.parseInt(count);
    }

    public void goToCart() {
        wait.waitForElementClickable(cartIcon).click();
    }
}