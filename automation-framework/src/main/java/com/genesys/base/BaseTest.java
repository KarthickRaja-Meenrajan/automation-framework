package com.genesys.base;

import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.genesys.utils.LoggerUtil;
import com.genesys.utils.ScreenshotUtil;

public class BaseTest {
	private static final Logger log = LoggerUtil.getLogger(BaseTest.class);

    @BeforeMethod
    public void setup() {
    	log.info("Launching browser");
        DriverManager.init();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(
                    DriverManager.getDriver(),
                    result.getName()
            );
        }
        log.info("Closing browser");
        DriverManager.quit();
    }
}