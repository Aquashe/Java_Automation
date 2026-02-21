package com.thomas.listeners;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thomas.reporting.ExtentManager;
import com.thomas.web.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener extends BaseTest implements ITestListener {
    ExtentReports extent = ExtentManager.getExtentReport();
    ExtentTest test;
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        threadLocal.set(test);
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        threadLocal.get().log(Status.PASS, "Test Passed");
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        threadLocal.get().fail(result.getThrowable());
        WebDriver failureDriver;
        try {
            failureDriver = ((BaseTest) result.getInstance()).getDriver();
        } catch (Exception e) {
            throw new RuntimeException("Due to driver for screenshot", e);
        }

        BaseTest testInstance = (BaseTest) result.getInstance();
        String filePath = testInstance.getScreenshot(
                result.getMethod().getMethodName(),
                failureDriver);

        threadLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        ITestListener.super.onFinish(context);
    }
}
