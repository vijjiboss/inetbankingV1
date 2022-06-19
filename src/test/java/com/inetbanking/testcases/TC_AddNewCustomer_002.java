package com.inetbanking.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.loginpage;

public class TC_AddNewCustomer_002 extends BaseClass {
	@Test
	public void AddNewCustomer() throws InterruptedException, IOException {

		loginpage lp = new loginpage(driver);

		lp.setusername(username);
		logger.info("user name entered");
		lp.setpassword(password);
		logger.info("password is entered");
		lp.clicksubmit();
		logger.info("login submitted");
		System.out.println("page title is : " + driver.getTitle());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		logger.info("add new customer page is opened");
		/*
		 * driver.findElement(By.xpath(
		 * "//ins[@id='gpt_unit_/24132379/INTERSTITIAL_DemoGuru99_0']")).click();
		 */

		// HOW TO HANDLE WITH GOOGLE ADVERTISEMENTS
		WebElement frame1 = driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0"));
		driver.switchTo().frame(frame1);
		WebElement frame2 = driver.findElement(By.id("ad_iframe"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//div[@id='dismiss-button']/div/span")).click();
		driver.switchTo().defaultContent();
		
		logger.info("google add is avoided");
		

		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("anjani devi");
		driver.findElement(By.xpath("(//input[@name='rad1'])[1]")).click();
		driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("15-08-1990");
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("door no 486g indira nagar colony 3 town area");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Narasaraopet");
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys("Andhrapradesh");
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("522420");
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("9908356885");
		String newemail=email()+"@gmail.com";
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(newemail);
		String newpassword=email();
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(newpassword);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		logger.info("details submitted sucessfully");
		Thread.sleep(3000);
		System.out.println("current page tittle is :"+driver.getTitle());
		logger.info("Customer Registered Successfully!!!");
		boolean page = driver.getPageSource().contains("Customer Registered Successfully!!!");
		if(page==true) {
			Assert.assertTrue(true);
		}		else {
			screencapture(driver,"AddNewCustomer");
			Assert.assertTrue(false);
		}
		

		/*
		 * driver.findElement(By.xpath("//a[text()='Delete Customer']")).click();
		 * logger.info("delete customer page is opened"); Thread.sleep(5000);
		 * driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		 * Thread.sleep(5000);
		 * driver.findElement(By.xpath("//a[text()='New Account']")).click();
		 * Thread.sleep(5000);
		 * driver.findElement(By.xpath("//a[text()='Edit Account']")).click();
		 * Thread.sleep(5000);
		 * driver.findElement(By.xpath("//a[text()='Delete Account']")).click();
		 * Thread.sleep(5000);
		 * driver.findElement(By.xpath("//a[text()='Deposit']")).click();
		 * Thread.sleep(5000);
		 * driver.findElement(By.xpath("//a[text()='Withdrawal']")).click();
		 * Thread.sleep(5000);
		 * driver.findElement(By.xpath("//a[text()='Change Password']")).click();
		 * Thread.sleep(5000);
		 * driver.findElement(By.xpath("//a[text()='Balance Enquiry']")).click();
		 * Thread.sleep(5000);
		 * driver.findElement(By.xpath("//a[text()='Mini Statement']")).click();
		 * Thread.sleep(5000);
		 * driver.findElement(By.xpath("//a[text()='Customised Statement']")).click();
		 * Thread.sleep(5000);
		 * driver.findElement(By.xpath("//a[text()='Log out']")).click();
		 * Thread.sleep(5000);
		 */

	}

}
