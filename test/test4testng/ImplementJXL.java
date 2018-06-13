package test4testng;

//Data Driven Test Framework using Selenium2 and TestNG
//This Test performs search for the movie and looks for the attributes on the result page
//Data is read from the Excel SS - movie_data1.xls

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import jxl.*;

public class ImplementJXL {

	private WebDriver driver;


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

	@DataProvider(name = "DP1")
	public Object[][] createData1() {
		Object[][] retObjArr = getTableArray(
				"test/resources/data/imdb_data1.xls", "DataPool",
				"imdbTestData1");
		System.out.println("*****************  2 *************************");
		return (retObjArr);
	}

	@Test(dataProvider = "DP1")
	public void testDataProviderExample(String movieTitle, String directorName,
			String moviePlot, String actorName) throws Exception {
		System.out.println("*****************  3 *************************");
		driver.get("http://www.imdb.com/");
		System.out.println("*****************  3.1 *************************");
		driver.findElement(By.id("navbar-query")).sendKeys(movieTitle);
		driver.findElement(By.id("navbar-submit-button")).click();
		driver.findElement(By.linkText(movieTitle)).click();

		// s_assert.assertTrue(verifyTextPresent(actorName));

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

	@AfterClass
	public void tearDown() {
		driver.quit();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - "
				+ new Timestamp(date.getTime()));

	}

	public boolean verifyTextPresent(String value) {
		boolean x = driver.getPageSource().contains(value);
		return x;
	}

	public String[][] getTableArray(String xlFilePath, String sheetName,
			String tableName) {
		String[][] tabArray = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);

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

}// end of class

