package com.inetbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	public static ExtentSparkReporter htmlreporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	public void onstart(ITestContext testContext) throws IOException {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		String repname = "Test-Report-" + timestamp + ".html";

		htmlreporter = new ExtentSparkReporter(
				"F:\\APCHEJMETERWORKSPACE\\EclipseForSelinium\\inetbankingV1\\test-output" + repname);
		htmlreporter.loadXMLConfig("F:\\APCHEJMETERWORKSPACE\\EclipseForSelinium\\inetbankingV1\\extent-config.xml");

		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "VIJAY");

		htmlreporter.config().setDocumentTitle("InetbankingV1");
		htmlreporter.config().setReportName("FunctionalTestReport");
		htmlreporter.config().setTheme(Theme.STANDARD);
	}

	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		System.out.println("hello" + logger);
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult tr) {
		System.out.println("hi");
		logger = extent.createTest(tr.getName());
		System.out.println("hello" + logger);
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		String screenshotpath = System.getProperty(
				"F:\\APCHEJMETERWORKSPACE\\EclipseForSelinium\\inetbankingV1\\screenshots\\" + tr.getName() + ".png");
		
		File f = new File(screenshotpath);
		if (f.exists()) {
			logger.fail("screenshot is below:" + logger.addScreenCaptureFromPath(screenshotpath));
		}
	}

	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testcontext) {
		extent.flush();
	}

}
