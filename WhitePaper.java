package com.DeleteTheseCodes;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WhitePaper {
	WebDriver driver;
	WebElement dynamicElement;
	WebDriverWait explicitWait = new WebDriverWait(driver, 10);
	
	/*
	 WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("dynamicElement")));
	  */

	/*  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
  .withTimeout(30, SECONDS)
    .pollingEvery(5, SECONDS)
    .ignoring(NoSuchElementException.class);
  */
	
	protected String URL = "https://www.google.com";

//	@Parameters({"browserName", "initialURL"})
//	@BeforeMethod
//	public void setUp(@Optional("chrome") String browserName, String initialURL) {

	
	
	@Test
	public void validateJsonResponse() {
		
		getMethodDefault(url);
		verifyPayloadResponse(payloadResponseString, "/users[0]");
		
	}
	
	
	@Parameters("browserName")
	@BeforeMethod
	public void setUp(@Optional("chrome") String browserName) {

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}

		else if (browserName.equals("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		try {
			driver.navigate().to(URL);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} catch (Exception e) {
			// TODO: handle exception
			logError("Unable to navigate to specified URL");
			e.printStackTrace();
		}

	}
	
	
public void explicitlyWaitForElement(WebElement element, String desiredActionToBePerformed) {
		
		if (desiredActionToBePerformed.equalsIgnoreCase("click")) {
			
			explicitWait.until(ExpectedConditions.elementToBeClickable(element)).click();}
		
		else if (desiredActionToBePerformed.equalsIgnoreCase("visibility of text")) {
			WebElement vE= explicitWait.until(ExpectedConditions.visibilityOf(element));
			vE.getText();
		}

		}
			
		
			
		public void explicitlyWaitForElement(By locator, String desiredActionToBePerformed) {
			
			if (desiredActionToBePerformed.equalsIgnoreCase("click")) {
				
			WebElement ele=	explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
				ele.click();
			}
			
			else if (desiredActionToBePerformed.equalsIgnoreCase("visibility of text")) {
				WebElement ele= explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				ele.getText();
			}

			}
		

	Logger log = LogManager.getLogger(WhitePaper.class);
	private CloseableHttpResponse closeableHttpResponse;

	public void logDebug(String debugMessage) {

		log.debug(debugMessage);
	}

	public void logError(String errorMessage) {

		log.debug(errorMessage);
	}

	public CloseableHttpResponse getMethodDefault(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
return closeableHttpResponse = httpClient.execute(httpGet);
	}
	
	public void getMethodCompleteResponse(String url) throws ClientProtocolException, IOException {

		// TODO: this is to get the status code, payload response, and response headers
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);

		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		logDebug("Status Code: " + statusCode);
		// verify the payload

		String payloadResponseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject jsonObject = new JSONObject(payloadResponseString);
		logDebug("Payload Response: " + jsonObject);
		// verify the response headers

		Header[] headersArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeadersHashMap = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeadersHashMap.put(header.getName(), header.getValue());
		}

		logDebug("Response Headers: " + allHeadersHashMap);
	}

	
	public static String verifyPayloadResponse(JSONObject payloadResponseString, String jpath) {
		Object obj = payloadResponseString;
		for (String s : jpath.split("/")) {
			if (!(s.contains("[") || (s.contains("]")))) {
				obj = ((JSONObject) obj).get(s);

			} else if (s.contains("[") || s.contains("]")) {
				obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
						.get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
			}

		}

		return obj.toString();

	}

}
