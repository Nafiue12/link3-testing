package com.link3.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class BaseTest {

    static ThreadLocal<WebDriver> driver =

            new ThreadLocal<>();

    // Setup browser
    public void setup() {

        EdgeOptions options =
                new EdgeOptions();

        // Check GitHub environment
        String github =
                System.getenv("GITHUB_ACTIONS");

        // Headless only in GitHub
        if(github != null) {

            options.addArguments(
                    "--headless"
            );

            options.addArguments(
                    "--disable-gpu"
            );

            options.addArguments(
                    "--no-sandbox"
            );

            options.addArguments(
                    "--window-size=1920,1080"
            );
        }

        driver.set(

                new EdgeDriver(options)
        );

        // Maximize locally
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