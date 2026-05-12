package com.link3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    // Open website
    public void openWebsite() {

        driver.get(
                "https://www.link3.net/"
        );
    }

    // Get title
    public String getTitle() {

        return driver.getTitle();
    }

    // Get URL
    public String getUrl() {

        return driver.getCurrentUrl();
    }

    // Total links
    public int totalLinks() {

        return driver.findElements(
                By.tagName("a")
        ).size();
    }

    // Click Self Care
    public void clickSelfCare() {

        WebElement selfCareButton =

                driver.findElement(
                        By.linkText("Self Care")
                );

        selfCareButton.click();
    }

    // Enter username
    public void enterUsername(
            String username
    ) {

        WebElement usernameField =

                driver.findElement(
                        By.cssSelector(
                                "input[type='text']"
                        )
                );

        usernameField.clear();

        usernameField.sendKeys(username);
    }

    // Enter password
    public void enterPassword(
            String password
    ) {

        WebElement passwordField =

                driver.findElement(
                        By.cssSelector(
                                "input[type='password']"
                        )
                );

        passwordField.clear();

        passwordField.sendKeys(password);
    }

    // Click login button
    public void clickLogin() {

        WebElement loginButton =

                driver.findElement(
                        By.cssSelector(
                                "button[type='submit']"
                        )
                );

        loginButton.click();
    }
}