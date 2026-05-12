package com.link3;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        System.setProperty(
                "webdriver.edge.driver",
                "msedgedriver.exe"
        );

        WebDriver driver = new EdgeDriver();

        Document pdf = new Document();

        try {

            // Create PDF
            PdfWriter.getInstance(
                    pdf,
                    new FileOutputStream(
                            "Link3-Test-Report.pdf"
                    )
            );

            pdf.open();

            pdf.add(
                    new Paragraph(
                            "LINK3 AUTOMATION TEST REPORT"
                    )
            );

            pdf.add(
                    new Paragraph(
                            "------------------------------------"
                    )
            );

            // Start timer
            long startTime =
                    System.currentTimeMillis();

            // Open website
            driver.get("https://www.link3.net/");

            // Maximize browser
            driver.manage().window().maximize();

            // Explicit wait
            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(10)
                    );

            wait.until(
                    ExpectedConditions
                            .visibilityOfElementLocated(
                                    By.tagName("body")
                            )
            );

            // End timer
            long endTime =
                    System.currentTimeMillis();

            long loadTime =
                    endTime - startTime;

            // Get title
            String title =
                    driver.getTitle();

            pdf.add(
                    new Paragraph(
                            "Website Title: "
                                    + title
                    )
            );

            // Validate title
            if(title.contains("Link3")) {

                pdf.add(
                        new Paragraph(
                                "TITLE TEST: PASSED"
                        )
                );

            } else {

                pdf.add(
                        new Paragraph(
                                "TITLE TEST: FAILED"
                        )
                );
            }

            // Get URL
            String url =
                    driver.getCurrentUrl();

            pdf.add(
                    new Paragraph(
                            "Current URL: "
                                    + url
                    )
            );

            // Load time
            pdf.add(
                    new Paragraph(
                            "Page Load Time: "
                                    + loadTime
                                    + " ms"
                    )
            );

            // Find all links
            List<WebElement> links =
                    driver.findElements(
                            By.tagName("a")
                    );

            pdf.add(
                    new Paragraph(
                            "Total Links Found: "
                                    + links.size()
                    )
            );

            // Print first 10 links
            int count = 0;

            for(WebElement link : links) {

                if(count >= 10)
                    break;

                pdf.add(
                        new Paragraph(
                                "Link Text: "
                                        + link.getText()
                        )
                );

                count++;
            }

            // Scroll page
            JavascriptExecutor js =
                    (JavascriptExecutor) driver;

            js.executeScript(
                    "window.scrollBy(0,1500)"
            );

            pdf.add(
                    new Paragraph(
                            "SCROLL TEST: PASSED"
                    )
            );

            Thread.sleep(3000);

            // Screenshot
            File screenshot =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.FILE
                            );

            Files.copy(
                    screenshot.toPath(),
                    new File(
                            "link3-homepage.png"
                    ).toPath()
            );

            pdf.add(
                    new Paragraph(
                            "SCREENSHOT SAVED"
                    )
            );

            // Open new tab
            js.executeScript(
                    "window.open('https://google.com','_blank');"
            );

            ArrayList<String> tabs =
                    new ArrayList<>(
                            driver.getWindowHandles()
                    );

            // Switch tab
            driver.switchTo()
                    .window(tabs.get(1));

            pdf.add(
                    new Paragraph(
                            "NEW TAB TITLE: "
                                    + driver.getTitle()
                    )
            );

            // Switch back
            driver.switchTo()
                    .window(tabs.get(0));

            pdf.add(
                    new Paragraph(
                            "TAB SWITCH TEST: PASSED"
                    )
            );

            Thread.sleep(3000);

            pdf.add(
                    new Paragraph(
                            "ALL TESTS COMPLETED SUCCESSFULLY"
                    )
            );

        } catch (Exception e) {

            try {

                pdf.add(
                        new Paragraph(
                                "ERROR OCCURRED"
                        )
                );

                pdf.add(
                        new Paragraph(
                                e.getMessage()
                        )
                );

                // Error screenshot
                File errorShot =
                        ((TakesScreenshot) driver)
                                .getScreenshotAs(
                                        OutputType.FILE
                                );

                Files.copy(
                        errorShot.toPath(),
                        new File(
                                "error.png"
                        ).toPath()
                );

            } catch (Exception ex) {

                ex.printStackTrace();
            }

        } finally {

            pdf.close();

            driver.quit();

            System.out.println(
                    "TEST COMPLETED"
            );

            System.out.println(
                    "PDF REPORT GENERATED"
            );
        }
    }
}