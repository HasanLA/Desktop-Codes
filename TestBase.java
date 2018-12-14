package com.domain.CodesToMemorize;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	WebDriver driver;
	WebDriverWait wait;
	protected String URL;
	
	SoftAssert softAssert = new SoftAssert();
	
	
	@Parameters(value="browserName")
	@BeforeMethod
	public void setUp(String browserName) {
		WebElement element = null;
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			
		} else if(browserName.equalsIgnoreCase("ff")){
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}
		
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, 10);
			driver.navigate().to(URL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@AfterClass
	public void tearDown() {
	
		softAssert.assertAll();
	}
}
