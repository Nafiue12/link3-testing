package com.link3.allure;

import io.qameta.allure.Attachment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureUtil {

    @Attachment(
            value = "{fileName}",
            type = "image/png"
    )

    public static byte[] attachScreenshot(

            String fileName

    ) throws IOException {

        return Files.readAllBytes(

                Paths.get(fileName)
        );
    }
}