package com.inetbanking.testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	public static WebDriver driver;
	public static Logger logger;

	ReadConfig rc = new ReadConfig();

	public String baseurl = rc.geturl();
	public String username = rc.getusername();
	public String password = rc.getpassword();

	@Parameters("Browser")
	@BeforeClass
	public void setup(String br) {

		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");

		if (br.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", rc.getchromepath());
			driver = new ChromeDriver();
		}

		else if (br.equals("Fiefox")) {
			System.setProperty("webdriver.gecko.driver", rc.getfirefoxpath());
			driver = new FirefoxDriver();
		} else if (br.equals("Ie")) {
			System.setProperty("webdriver.ie.driver", rc.getiepath());
			driver = new InternetExplorerDriver();
		} else if (br.equals("Edge")) {
			System.setProperty("webdriver.edge.driver", rc.getmsedgepath());
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseurl);
		logger.info("url is opened");

	}

	@AfterClass

	public void teardown() {
		driver.quit();
	}

	public void screencapture(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File Target = new File(
				"F:\\APCHEJMETERWORKSPACE\\EclipseForSelinium\\inetbankingV1\\screenshots" + tname + ".png");
		FileUtils.copyFile(Source, Target);
		System.out.println("screenshot is taken sucesssfully");

	}

	// AUTOMATICALLY GENERATE RANDOM NEW MAIL ID
	public String email() {
		String newmail = RandomStringUtils.randomAlphanumeric(9);
		return newmail;
	}

	String newemail = email() + "@gmail.com";
	String newpassword = email();
}
