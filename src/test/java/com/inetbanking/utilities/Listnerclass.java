package com.inetbanking.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.inetbanking.testcases.BaseClass;

public class Listnerclass extends BaseClass implements ITestListener {
	public static ExtentHtmlReporter sparkreporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;
	public static String timestamp;
	
	
	
	@Override
	public void onStart(ITestContext context) {
		String timestamp = new SimpleDateFormat("HH-mm-ss-dd-MM-yyyy").format(new Date());// time stamp
		String repname = " LatestReport@ "+ timestamp +" .html";
		sparkreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\test-output\\" + repname);
		sparkreporter.loadXMLConfig(System.getProperty("user.dir")+"\\extent-config.xml");
		//sparkreporter.config().setDocumentTitle("AUTOMATION TEST REPORT");
		//sparkreporter.config().setReportName("INET BANKING TEST AUTOMATON");
		//sparkreporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("HostName", "vijay");
		extent.setSystemInfo("Project Name", "INetbanking");
		extent.setSystemInfo("Tester", "jai");
		extent.setSystemInfo("Os", "windows");
		extent.setSystemInfo("Browser", "Chrome");

	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " - test case is passed ", ExtentColor.GREEN));
		}

	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test case failed", ExtentColor.RED));
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + "-Test case failed", ExtentColor.RED));

			String pathString = BaseClass.screenShot(BaseClass.driver, result.getName());
			try {
				test.addScreenCaptureFromPath(pathString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + "Test case is skipped", ExtentColor.ORANGE));

		}

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
