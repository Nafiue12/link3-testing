package com.link3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    // Username field
    By usernameField =

            By.cssSelector(
                    "input[type='text']"
            );

    // Password field
    By passwordField =

            By.cssSelector(
                    "input[type='password']"
            );

    // Login button
    By loginButton =

            By.cssSelector(
                    "button[type='submit']"
            );

    // Enter username
    public void enterUsername(
            String username
    ) {

        driver.findElement(
                usernameField
        ).sendKeys(username);
    }

    // Enter password
    public void enterPassword(
            String password
    ) {

        driver.findElement(
                passwordField
        ).sendKeys(password);
    }

    // Click login
    public void clickLogin() {

        driver.findElement(
                loginButton
        ).click();
    }
}