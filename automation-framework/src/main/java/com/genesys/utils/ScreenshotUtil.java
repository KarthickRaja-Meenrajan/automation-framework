package com.genesys.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {

        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String path = "screenshots/" + testName + "_" + timestamp + ".png";

            File dest = new File(path);
            dest.getParentFile().mkdirs();

            src.renameTo(dest);

            return path;

        } catch (Exception e) {
            throw new RuntimeException("Screenshot failed: " + e.getMessage());
        }
    }
}