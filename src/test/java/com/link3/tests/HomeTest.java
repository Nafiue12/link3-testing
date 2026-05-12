package com.link3.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.link3.base.BaseTest;
import com.link3.data.CSVReader;
import com.link3.pages.HomePage;
import com.link3.pages.LoginPage;
import com.link3.reports.ExtentManager;
import com.link3.retry.RetryAnalyzer;
import com.link3.utils.ScreenshotUtil;

public class HomeTest extends BaseTest {

    // DATA PROVIDER
    @DataProvider(name = "loginData")

    public Object[][] getData() {

        return CSVReader.getCSVData(

                "src/test/resources/login-data.csv"
        );
    }

    // TEST
    @Test(
            dataProvider = "loginData",
            retryAnalyzer = RetryAnalyzer.class
    )

    public void testLink3Login(

            String username,
            String password

    ) {

        // Start browser
        setup();

        // Extent Report
        ExtentReports extent =
                ExtentManager.getReport();

        ExtentTest test =
                extent.createTest(

                        "Login Test: "
                                + username
                );

        // Page Objects
        HomePage home =
                new HomePage(getDriver());

        LoginPage login =
                new LoginPage(getDriver());

        try {

            // Open website
            home.openWebsite();

            test.pass(
                    "Website Opened"
            );

            // Click Self Care
            home.clickSelfCare();

            test.pass(
                    "Self Care Clicked"
            );

            // Wait for new tab
            Thread.sleep(5000);

            // Switch to new tab
            for(String tab :
                    getDriver().getWindowHandles()) {

                getDriver()
                        .switchTo()
                        .window(tab);
            }

            // Current URL
            String currentUrl =
                    getDriver()
                            .getCurrentUrl();

            // Validate login page
            Assert.assertTrue(

                    currentUrl.contains(
                            "selfcare.link3.net"
                    )
            );

            test.pass(
                    "Login Page Opened"
            );

            // Enter username
            login.enterUsername(username);

            test.pass(
                    "Username Entered: "
                            + username
            );

            // Enter password
            login.enterPassword(password);

            test.pass(
                    "Password Entered"
            );

            // Screenshot before login
            ScreenshotUtil.takeScreenshot(

                    getDriver(),

                    username
                            + "-before-login.png"
            );

            test.pass(
                    "Before Login Screenshot Saved"
            );

            // Click login
            login.clickLogin();

            test.pass(
                    "Login Button Clicked"
            );

            // Wait after login
            Thread.sleep(5000);

            // Screenshot after login
            ScreenshotUtil.takeScreenshot(

                    getDriver(),

                    username
                            + "-after-login.png"
            );

            test.pass(
                    "After Login Screenshot Saved"
            );

            // Page source
            String pageSource =

                    getDriver()
                            .getPageSource()
                            .toLowerCase();

            // Detect login failure
            if(pageSource.contains("invalid")
                    ||
               pageSource.contains("wrong")
                    ||
               pageSource.contains("error")
                    ||
               pageSource.contains("failed")) {

                test.pass(
                        "LOGIN FAILURE DETECTED"
                );

            } else {

                test.pass(
                        "NO LOGIN ERROR MESSAGE FOUND"
                );
            }

            test.pass(
                    "TEST COMPLETED"
            );

        } catch (Exception e) {

            // Report failure
            test.fail(
                    e.getMessage()
            );

            // Error screenshot
            ScreenshotUtil.takeScreenshot(

                    getDriver(),

                    "error-" + username + ".png"
            );

            e.printStackTrace();

        } finally {

            // Save Extent Report
            extent.flush();

            // Close browser
            closeBrowser();
        }
    }
}