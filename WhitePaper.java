package com.DeleteTheseCodes;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WhitePaper {
	WebDriver driver;
	
	protected String URL = "https://www.google.com";
	
//	@Parameters({"browserName", "initialURL"})
//	@BeforeMethod
//	public void setUp(@Optional("chrome") String browserName, String initialURL) {
	
	@Parameters("browserName")
	@BeforeMethod
	public void setUp(@Optional("chrome") String browserName) {
		if (browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		else if (browserName.contains("frefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
			
		}
		
		try {
			
			
			driver.navigate().to(URL);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
