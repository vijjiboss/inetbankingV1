package com.inetbanking.testcases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.loginpage;
import com.inetbanking.utilities.Xlutils;

public class TC_loginDDT001 extends BaseClass{
	@Test(dataProvider="logindata")
	public void loginDDT(String uname,String pwd) throws InterruptedException {
		System.out.println("vijay");
		loginpage lp=new loginpage(driver);
		lp.setusername(uname);
		logger.info("user name provided sucessfully");
		lp.setpassword(pwd);
		logger.info("password provided sucessfully");
		lp.clicksubmit();
		logger.info("credentials submitted sucessfully");
		
		if(IsAlertPresent()==true) {
			logger.info("login failed alert is present");
			Thread.sleep(4000);
			driver.switchTo().alert().accept();
			logger.info("login failed alert is handled sucessfully");
			driver.switchTo().defaultContent();
			logger.info("login home page is ready");
			Assert.assertTrue(false);
			logger.warn("login failed");
		}else {
			lp.clicklogout();
			Thread.sleep(4000);
			logger.info("home pagelogoutsucessfully");
			driver.switchTo().alert().accept();
			logger.info("logout alert accepted");
			driver.switchTo().defaultContent();	
			logger.info("now log in page available");
			Assert.assertTrue(true);
			logger.warn("login sucessfull");
			}
		
		
	}
	
	
	
	public boolean IsAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e) {
		return false;
		}
	}
	@DataProvider(name="logindata")
	String[][] getData() throws IOException{
		String path="F:/APCHEJMETERWORKSPACE/EclipseForSelinium/inetbankingV1/src/test/java/com/inetbanking/testdata/logindata.xlsx";
		int rowcount=Xlutils.getRowCount(path,"Sheet1");
		int colcount=Xlutils.getCellCount(path,"Sheet1",2);
		String logindata[][] = new String[rowcount][colcount];
		
		for(int i=1;i<=rowcount;i++) {
			for(int j=0;j<colcount;j++) {
				logindata[i-1][j]=Xlutils.getCellData(path,"Sheet1",i,j);
			}
		}
		return logindata;
	}

	

}
