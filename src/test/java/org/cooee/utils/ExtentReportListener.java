package org.cooee.utils;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.testng.IAlterSuiteListener;
import org.testng.IExecutionListener;

public class ExtentReportListener extends ExtentITestListenerClassAdapter
        implements IAlterSuiteListener, IExecutionListener {

    public ExtentReportListener() {
        System.out.println("✅ ExtentReportListener is active");
    }
}
