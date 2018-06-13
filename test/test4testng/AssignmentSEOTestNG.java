package test4testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import jxl.*;

public class AssignmentSEOTestNG {

	private WebDriver driver;


	@BeforeTest
	public void setUp() throws Exception {

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
				"test/resources/data/seoTest1.xls", "Sheet1",
				"loc");
		return (retObjArr);
	}

	@Test(dataProvider = "DP1")
	public void testDataProviderExample(String searchText, String linkToSearch,
			String maxPageNum) throws Exception {
		driver.get("http://www.google.com/");
		driver.findElement(By.id("lst-ib")).sendKeys(searchText);
		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]")).click();
		By currPage= By.className("cur");
		By nextX = By.xpath("//*[@id=\"pnnext\"]/span[2]");
		int counter= Integer.parseInt(maxPageNum);
		boolean flag = false;
		while(counter-->0){
			Boolean b= driver.getPageSource().contains(linkToSearch);
			if(b){
				System.out.println("Found on page number: "+ driver.findElement(currPage).getText());
				flag=true;
				break;
			}else{
				driver.findElement(nextX).click();	
				continue;
			}
			
		}
		if(!flag){
			System.out.println("Not Found");
		}
		

	}

	@AfterClass
	public void tearDown() {
		driver.quit();

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

