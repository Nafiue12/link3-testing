package com.link3.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseTest {

    // Thread-safe driver
    static ThreadLocal<WebDriver> driver =

            new ThreadLocal<>();

    // Setup browser
    public void setup() {

        System.setProperty(
                "webdriver.edge.driver",
                "msedgedriver.exe"
        );

        driver.set(
                new EdgeDriver()
        );

        getDriver()
                .manage()
                .window()
                .maximize();
    }

    // Get driver
    public static WebDriver getDriver() {

        return driver.get();
    }

    // Close browser
    public void closeBrowser() {

        getDriver().quit();

        driver.remove();
    }
}