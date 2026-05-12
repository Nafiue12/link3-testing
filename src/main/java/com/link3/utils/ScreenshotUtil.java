package com.link3.utils;

import java.io.File;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static void takeScreenshot(
            WebDriver driver,
            String fileName
    ) {

        try {

            File screenshot =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.FILE
                            );

            Files.copy(
                    screenshot.toPath(),
                    new File(fileName).toPath()
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}