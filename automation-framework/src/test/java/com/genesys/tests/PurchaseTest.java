package com.genesys.tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.genesys.base.BaseTest;
import com.genesys.base.DriverManager;
import com.genesys.pages.CartPage;
import com.genesys.pages.CheckoutPage;
import com.genesys.pages.InventoryPage;
import com.genesys.pages.LoginPage;
import com.genesys.utils.Config;
import com.genesys.utils.JsonUtil;

import org.slf4j.Logger;
import com.genesys.utils.LoggerUtil;

public class PurchaseTest extends BaseTest {
	private static final Logger log = LoggerUtil.getLogger(PurchaseTest.class);

    @Test
    public void testPurchaseFlow() throws Exception {
    	
    	
    	log.info("===== Starting Purchase Test =====");

        JSONObject creds = JsonUtil.readJson("src/test/resources/credential.json");

        String user = creds.getString("username");
        String pass = creds.getString("password");

        LoginPage login = new LoginPage(DriverManager.getDriver());
        login.open(Config.SAUCE_DEMO_URL);
        log.info("Opening SauceDemo URL");
        login.enterCredentials(user, pass);
        login.clickLogin();
        log.info("Entered credentials and clicked login");

        InventoryPage inventory = new InventoryPage(DriverManager.getDriver());
        inventory.addItemsToCart();
        log.info("Added Backpack and Jacket to cart");
        Assert.assertEquals(inventory.getCartCount(), 2, "Cart count mismatch");
        log.info("Cart count validated successfully");
        inventory.goToCart();

        CartPage cart = new CartPage(DriverManager.getDriver());
        cart.clickCheckout();
        log.info("Checkout initiated");
        CheckoutPage checkout = new CheckoutPage(DriverManager.getDriver());
        checkout.enterDetails("John", "Doe", "12345");
        log.info("Entered checkout details");
        checkout.finishOrder();
        log.info("Order completed");

        Assert.assertEquals(
                checkout.getSuccessMessage(),
                "Thank you for your order!",
                "Order not completed"
        );
        log.info("===== Test Completed Successfully =====");;
    }
}