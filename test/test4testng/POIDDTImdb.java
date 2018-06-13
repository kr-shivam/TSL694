package test4testng;

//Data Driven Test Framework using Selenium2 and TestNG
//This Test performs search for the movie and looks for the attributes on the result page
//Data is read from the Excel SS - movie_data1.xls

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.collections.Lists;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import jxl.Cell;

public class POIDDTImdb {

	private WebDriver driver;
	Scanner scan = new Scanner(System.in);


	@BeforeTest
	public void setUp() throws Exception {
		System.out.println("*****************  1 *************************");

		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));

		
	}

	

	@DataProvider(name = "DP_JXL")
	public Object[][] createData1() {
		Object[][] retObjArr = getTableArray(
				"test/resources/data/imdb_data1.xls", "DataPool",
				"imdbTestData1");
		System.out.println("*****************  2 *************************");
		return (retObjArr);
	}
	
	@DataProvider(name = "DP_POI")
	public Object[][] createData2() {
		Object[][] retObjArr = getExcelData(
				"test/resources/data/movie_data_POI.xlsx", "DataPool");
		System.out.println("*****************  2 *************************");
		return (retObjArr);
	}
	
	@DataProvider(name = "DP")
	public Object[][] dp() {
	  List<Object[][]> result = Lists.newArrayList();
	  result.add(createData1());
	  result.add(createData2());

	  System.out.println("Choose from the option below: ");
		System.out.println("1. Read data from xls via JXL");
		System.out.println("2. Read data from xls/xlsx via POI");

		int choice = scan.nextInt();

		switch (choice) {
		case 1:
			return result.get(0);
			
		case 2:
			return result.get(1);
			//
			
		default:
			System.out.println("Invalid Input, run the test again!");
			break;
		}
		return null;
	  
	}

	@Test(dataProvider = "DP")
	public void testDataProviderExample(String movieTitle, String directorName,
			String moviePlot, String actorName) throws Exception {
		System.out.println("*****************  3 *************************");
		driver.get("http://www.imdb.com/");
		System.out.println("*****************  3.1 *************************");
		driver.findElement(By.id("navbar-query")).sendKeys(movieTitle);
		driver.findElement(By.id("navbar-submit-button")).click();
		driver.findElement(By.linkText(movieTitle)).click();


		if (verifyTextPresent(directorName))
			System.out.println("Found Dir Name: " + directorName);
		Assert.assertTrue(verifyTextPresent(directorName));

		if (verifyTextPresent(moviePlot))
			System.out.println("Found Movie Plot: " + moviePlot);
		Assert.assertTrue(verifyTextPresent(moviePlot));

		if (verifyTextPresent(actorName))
			System.out.println("Found Actor Name: " + actorName);
		Assert.assertTrue(verifyTextPresent(actorName));

		System.getProperties().put(
				"org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_FATAL",
				"error");
			
		}

	public boolean verifyTextPresent(String value) {
		boolean x = driver.getPageSource().contains(value);
		return x;
	}

	public static String[][] getExcelData(String fileName, String sheetName) {

		String[][] arrayExcelData = null;
		Workbook wb = null;
		try {
			System.out.println(" before file here......");
			File file = new File(fileName);
			FileInputStream fs = new FileInputStream(file);
			System.out.println("after file here......");
			// .xls
			if (fileName.substring(fileName.indexOf(".")).equals(".xlsx")) {

				// If it is xlsx file then create object of XSSFWorkbook class
				System.out.println(" here......");
				wb = new XSSFWorkbook(fs);
			} else if (fileName.substring(fileName.indexOf(".")).equals(".xls")) {
				// If it is xls file then create object of HSSFWorkbook class

				wb = new HSSFWorkbook(fs);
			}

			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfRows = sh.getPhysicalNumberOfRows();
			int totalNoOfCols = sh.getRow(0).getPhysicalNumberOfCells();

			System.out.println("totalNoOfRows=" + totalNoOfRows + ","
					+ " totalNoOfCols=" + totalNoOfCols);
			arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];
			for (int i = 1; i <= totalNoOfRows - 1; i++) {
				for (int j = 0; j <= totalNoOfCols - 1; j++) {
					sh.getRow(i).getCell(j).setCellType(1);
					arrayExcelData[i - 1][j] = sh.getRow(i).getCell(j)
							.getStringCellValue().toString();
				}
			}
		} catch (Exception e) {
			System.out.println("error in getExcelData() " + e.getMessage());
		}
		return arrayExcelData;
	}

	public static String[][] getTableArray(String xlFilePath, String sheetName,
			String tableName) {
		String[][] tabArray = null;
		try {
			jxl.Workbook workbook = jxl.Workbook.getWorkbook(new File(
					xlFilePath));
			jxl.Sheet sheet = workbook.getSheet(sheetName);

			int startRow, startCol, endRow, endCol, ci, cj;

			Cell tableStart = sheet.findCell(tableName);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();

			Cell tableEnd = sheet.findCell(tableName, startCol + 1,
					startRow + 1, 100, 64000, false);

			endRow = tableEnd.getRow();
			endCol = tableEnd.getColumn();

			System.out.println("startRow=" + startRow + ", endRow=" + endRow
					+ ", " + "startCol=" + startCol + ", endCol=" + endCol);
			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
			ci = 0;

			for (int i = startRow + 1; i < endRow; i++, ci++) {
				cj = 0;
				for (int j = startCol + 1; j < endCol; j++, cj++) {
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			System.out.println("error in getTableArray()");

		}

		return (tabArray);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		scan.close();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - "
				+ new Timestamp(date.getTime()));

	}
}// end of class

