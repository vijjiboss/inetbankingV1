package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;

	public ReadConfig() {
		File src = new File("./configuration/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is" + e.getMessage());
		}
	}

	public String geturl() {
		String url = pro.getProperty("baseurl");
		return url;
	}
	public String getusername() {
		String uname=pro.getProperty("username");
		return uname;
	}
	public String getpassword() {
		String pwd=pro.getProperty("password");
		return pwd;
	}
	public String getchromepath() {
		String chrome=pro.getProperty("chromepath");
		return chrome;
	}
	public String getfirefoxpath() {
		String ffox=pro.getProperty("firefoxpath");
		return ffox;
}
	public String getiepath() {
		String ie=pro.getProperty("iepath");
		return ie;
	}
	public String getmsedgepath() {
		String me =pro.getProperty("msedgepath");
		return me;
	}
}

