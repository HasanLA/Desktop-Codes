package testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class TestUtils {

	public JSONObject responseString;

	String sheetName;
	int sheetIndex;
	int columnIndex;
	public WebDriver driver;

	private JSONObject jSONresponseString;
	static String excelFilePath;
	
	
	
	
	
	

	/************************** Apache Poi Code **************************/
	public static ArrayList<String> readEntireDataFromExcel(int sheetIndex, int cellnum) throws Exception {
		ArrayList<String> thisArrayList = new ArrayList();
		File file = new File(excelFilePath);
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			thisArrayList.add(rowIterator.next().getCell(cellnum).getStringCellValue());

		}

		return thisArrayList;
	}

	public static ArrayList<String> readEntireDataFromExcel(String sheetName, int cellnum) throws Exception {
		ArrayList<String> thisArrayList = new ArrayList();
		File file = new File(excelFilePath);
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = workbook.getSheet(sheetName);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			thisArrayList.add(rowIterator.next().getCell(cellnum).getStringCellValue());

		}

		return thisArrayList;
	}

	@Test
	public void apachePoiTest() throws Exception {
		ArrayList<String> userName = TestUtils.readEntireDataFromExcel(0, 0);
		ArrayList<String> password = TestUtils.readEntireDataFromExcel(0, 1);

		for (int i = 0; i < userName.size(); i++) {

			driver.findElement(By.xpath(null)).sendKeys(userName.get(i));
			driver.findElement(By.xpath(null)).sendKeys(password.get(i));
		}
	}

	/*****************************
	 * * END OF Apache Poi Code
	 **************************/

	/**************************
	 * * JSON Response Object Verification Method
	 **************************/

	public static String getValueFromJpath(JSONObject jSONresponseString, String jpath) {

		Object obj = jSONresponseString;
		for (String s : jpath.split("/")) {

			if (!s.isEmpty())
				if (!(s.contains("[") || s.contains("]"))) {
					obj = ((JSONObject) obj).get(s);
				}

				else if (s.contains("[") || s.contains("]")) {

					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
							.get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));

				}

		}

		return obj.toString();

	};

	/********************************************************************
	 * * NEED TO CREATE NEW DRAFT OF THIS METHOD USING NAVEEN AUTOMATION
	 ********************************************************************/
	@Test
	public void m3() {
		TestUtils.getValueFromJpath(jSONresponseString, "Name of Object ");
		TestUtils.getValueFromJpath(jSONresponseString, "//arrayName[0]");

	}

	/******************************
	 * END OFJSON Response Object Verification Method
	 **************************/

}
