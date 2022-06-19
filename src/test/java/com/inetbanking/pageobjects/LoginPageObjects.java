package com.inetbanking.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObjects {
	WebDriver driver;
	public LoginPageObjects(WebDriver d){
		driver=d;
	}
	
	
	By username=By.name("uid");
	By password=By.name("password");
	By login=By.name("btnLogin");
	By logout=By.xpath("//a[text()='Log out']");
	
	
	public void setusername(String uname) {
		driver.findElement(username).sendKeys(uname);
	}
	
	public void setpassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}
	
	public void clicksubmit() {
		driver.findElement(login).click();
	}
	
	public void clicklogout() {
		driver.findElement(logout).click();
	}
	

}
