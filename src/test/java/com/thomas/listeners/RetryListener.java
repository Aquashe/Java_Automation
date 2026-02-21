package com.thomas.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

    int maxTry = 2;
    int count =0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count<=maxTry){
            count++;
            return true;
        }
        return false;
    }
}
