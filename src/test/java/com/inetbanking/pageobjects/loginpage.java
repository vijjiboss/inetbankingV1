package com.inetbanking.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpage {
	WebDriver driver;

	public loginpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(name = "uid")
	@CacheLookup
	WebElement txtuserid;

	@FindBy(name = "password")
	@CacheLookup
	WebElement txtpassword;

	@FindBy(name = "btnLogin")
	@CacheLookup
	WebElement btnlogin;

	@FindBy(xpath = "//a[text()='Log out']")
	@CacheLookup
	WebElement logout;

	public void setusername(String uname) {

		txtuserid.sendKeys(uname);
	}

	public void setpassword(String pwd) {

		txtpassword.sendKeys(pwd);
	}

	public void clicksubmit() {

		btnlogin.click();

	}

	public void clicklogout() {
		logout.click();

	}
	
}
