package com.inetbanking.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.LoginPageObjects;

public class LoginTestCase_001 extends BaseClass {

	@Test
	public void logintestcase() throws InterruptedException, IOException {

		driver.get(baseurl);
		logger.info("url is opened");

		LoginPageObjects lp = new LoginPageObjects(driver);
		lp.setusername(username);
		logger.info("user name entered");
		lp.setpassword(password);
		logger.info("password is entered");
		lp.clicksubmit();
		logger.info("login successfully");
		System.out.println(driver.getTitle());

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("home page opened sucessfully");
			lp.clicklogout();
			logger.info("home page logout sucessfully");
			Thread.sleep(3000);
		} else {
			Assert.assertTrue(false);

			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();

		}

	}

}
