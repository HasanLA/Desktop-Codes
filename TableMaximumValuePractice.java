package com.domain.CodesToMemorize;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TableMaximumValuePractice {
	static WebDriver wd;

	public static void main(String[] args) throws ParseException {

		WebDriverManager.chromedriver().setup();
		wd = new ChromeDriver();
		wd.get("http://money.rediff.com/gainers/bsc/daily/groupa?");
		String max;
		double m = 0, r = 0;

		// No.of rows
		List<WebElement> rows = wd.findElements(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
		System.out.println("Total No of rows are : " + rows.size());
		for (int i = 1; i < rows.size(); i++) {
			max = wd.findElement(By.xpath("//*[@id='leftcontainer']/table/tbody/tr[" + (i + 1) + "]/td[4]")).getText();
		NumberFormat nf = NumberFormat.getNumberInstance();
		Number number = nf.parse(max);
		max=number.toString();
		m=Double.parseDouble(max);
		if (m>r) {
			r=m;
		}
		}
		System.out.println("Maximum current price is : " + r);

		
	wd.quit();}

}
