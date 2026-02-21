package com.thomas.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReport(){
       if(extent == null){
           String path = ExecutionManager.getExecutionFolder()
                   + "\\" + ExecutionManager.timeStamp+  ".html";

           ExtentSparkReporter reporter = new ExtentSparkReporter(path);
           reporter.config().setReportName("Web Automation Results");
           reporter.config().setDocumentTitle("Test Results");
           extent  = new ExtentReports();
           extent.attachReporter(reporter);
           extent.setSystemInfo("Tester", "Thomas");
       }

       return extent;
    }
}
