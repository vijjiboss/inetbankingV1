package com.inetbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	public static ExtentReports extent;
	public static ExtentHtmlReporter spark;
	public static ExtentTest logger;
	public static WebDriver driver;

	public void onstart(ITestContext testContext) throws IOException {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		String repname = "Test-Report-" + timestamp + ".html";

		spark = new ExtentHtmlReporter(
				"F:\\APCHEJMETERWORKSPACE\\EclipseForSelinium\\inetbankingV1\\test-output\\" + repname);
		spark.loadXMLConfig("F:\\APCHEJMETERWORKSPACE\\EclipseForSelinium\\inetbankingV1\\extent-config.xml");
		extent.attachReporter(spark);
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "VIJAY");

		spark.config().setDocumentTitle("InetbankingV1");
		spark.config().setReportName("FunctionalTestReport");
		spark.config().setTheme(Theme.STANDARD);
	}

	public void onTestSuccess(ITestResult tr) {

		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		/*
		 * try { BaseClass.screencapture(driver, "onTestFailure"); } catch (IOException
		 * e1) { // TODO Auto-generated catch block e1.printStackTrace(); }
		 */

		String screenshotpath = "F:\\APCHEJMETERWORKSPACE\\EclipseForSelinium\\inetbankingV1\\screenshots\\"
				+ tr.getName() + ".png";

		File f = new File(screenshotpath);
		if (f.exists()) {
			try {
				logger.fail("screenshot is below:" + logger.addScreenCaptureFromPath(screenshotpath));
			} catch (IOException e) {
				e.printStackTrace();
			}
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
