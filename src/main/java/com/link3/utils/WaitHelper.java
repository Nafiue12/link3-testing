package com.link3.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    public static void waitForElement(

            WebDriver driver,
            By locator

    ) {

        WebDriverWait wait =

                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );

        wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(
                                locator
                        )
        );
    }
}