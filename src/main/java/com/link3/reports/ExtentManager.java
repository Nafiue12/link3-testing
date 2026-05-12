package com.link3.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    static ExtentReports extent;

    public static ExtentReports getReport() {

        if(extent == null) {

            ExtentSparkReporter reporter =

                    new ExtentSparkReporter(
                            "ExtentReport.html"
                    );

            reporter.config().setReportName(
                    "Link3 Automation Report"
            );

            reporter.config().setDocumentTitle(
                    "Selenium Report"
            );

            extent = new ExtentReports();

            extent.attachReporter(reporter);
        }

        return extent;
    }
}