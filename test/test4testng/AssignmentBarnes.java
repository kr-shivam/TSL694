package test4testng;

import java.io.File;
import java.sql.Timestamp;


import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;


public class AssignmentBarnes {
	private WebDriver driver;

	@BeforeTest
	public void setUp() throws Exception {

		File pathToBinary = new File(
				"C:\\Users\\LTI.INFVA07132\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();

		driver = new FirefoxDriver(ffBinary, firefoxProfile);
		driver.get("https://www.barnesandnoble.com/");
		driver.findElement(By.className("icon-close-modal")).click();
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));

	}

	@DataProvider(name = "DP1")
	public Object[][] createData1() {
		Object[][] retObjArr = getTableArray(
				"test/resources/data/books_barnesandnoble.xls", "Sheet1", "loc");
		return (retObjArr);
	}

	@Test(dataProvider = "DP1")
	public void testDataProviderExample(String bookName, String linkToSearch,
			String maxPageNum) throws Exception {
		// driver.get("https://www.barnesandnoble.com/");
		driver.findElement(By.id("searchBarBN")).clear();
		driver.findElement(By.id("searchBarBN")).sendKeys(bookName);
		driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div[2]")).click();
		By currPage = By.className("pagination__active");

		WebDriverWait waiting = new WebDriverWait(driver, 10);
		By activePageX = By.className("pagination__active");

		int counter = Integer.parseInt(maxPageNum);
		boolean flag = false;

		/*
		 * Developer Notes: We were unable to fetch the nextpage.We selected the
		 * current page and moved to next page by clicking tab button.
		 */

		while (counter-- > 0) {
			Boolean b = driver.getPageSource().contains(linkToSearch);
			if (b) {
				System.out.println("Found on page number: "
						+ driver.findElement(currPage).getText());
				flag = true;
				break;
			} else {
				waiting.until(ExpectedConditions
						.visibilityOfElementLocated(activePageX));
				WebElement currentPageEl = driver.findElement(activePageX);
				currentPageEl.click();
				
				Actions act = new Actions(driver);

				act.sendKeys(Keys.UP).sendKeys(Keys.TAB).sendKeys(Keys.RETURN)
						.build().perform();

				continue;

			}

		}
		if (!flag) {
			System.out.println("Not Found");
		}

	}

	@AfterClass
	public void tearDown() {
		// driver.quit();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - "
				+ new Timestamp(date.getTime()));

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

			System.out.println(startRow);
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

}
