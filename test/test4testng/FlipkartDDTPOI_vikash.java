package test4testng;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FlipkartDDTPOI_vikash {
	WebDriver webdriver;

	@BeforeTest
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver", "test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("https://www.flipkart.com/");
		By popupX = By.xpath("/html/body/div[2]/div/div/button");
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.findElement(popupX).click();

	}

	@AfterTest
	public void afterTest() {
		webdriver.close();
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

			System.out.println("totalNoOfRows=" + totalNoOfRows + "," + " totalNoOfCols=" + totalNoOfCols);
			arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];
			for (int i = 1; i <= totalNoOfRows - 1; i++) {
				for (int j = 0; j <= totalNoOfCols - 1; j++) {
					sh.getRow(i).getCell(j).setCellType(1);
					arrayExcelData[i - 1][j] = sh.getRow(i).getCell(j).getStringCellValue().toString();
				}
			}
		} catch (Exception e) {
			System.out.println("error in getExcelData() " + e.getMessage());
		}
		return arrayExcelData;
	}

	@DataProvider(name = "DP_POI")
	public Object[][] createData2() {
		Object[][] retObjArr = getExcelData("test/resources/data/flipkart_mobiles.xlsx", "DataPool");
		System.out.println("*****************  2 *************************");
		return (retObjArr);
	}

	@Test(dataProvider = "DP_POI")
	public void testDataProviderExample(String mobile) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(webdriver, 5);
		// finalPriceList.clear();
		System.out.println("---------------------" + mobile + " Example--------------");
		webdriver.get("http://www.flipkart.com/");
		By searchX = By.className("LM6RPg");
		WebElement searchBox = webdriver.findElement(searchX);
		searchBox.sendKeys(mobile);
		searchBox.sendKeys(Keys.RETURN);
		List<WebElement> mobileItems = webdriver.findElements(By.className("_1UoZlX"));
		int maxPrice = 0, minPrice = 0;
		int t = 2, t_high = 0, t_low = 0;
		String modelHigh = null, modelLow = null;
		String mobileNameHigh = null, mobileNameLow = null;
		for (WebElement we : mobileItems) {
			String priceCss = "#container > div > div:nth-child(2) > div > div._3e7xtJ > div._1HmYoV.hCUpcT > div._1HmYoV._35HD7C.col-10-12 > div:nth-child("
					+ t
					+ ") > div > div > div > a > div._1-2Iqu.row > div.col.col-5-12._2o7WAb > div._6BWGkk > div > div._1vC4OE._2rQ-NK";
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(priceCss)));
			WebElement mobPrice = we.findElement(By.cssSelector(priceCss));
			String price = mobPrice.getText();
			price = price.replaceAll("\u20B9", "");
			price = price.replaceAll(",", "");
			int intPrice = Integer.parseInt(price);
			System.out.println(intPrice);

			if (intPrice > maxPrice) {
				maxPrice = intPrice;
				t_high = t;
			} else if (minPrice == 0) {
				minPrice = intPrice;
			} else if (intPrice < minPrice) {
				minPrice = intPrice;
				t_low = t;
			}
			t++;
		}
		System.out.println("-------------------------");
		System.out.println("Min Price" + minPrice);
		System.out.println("Max Price" + maxPrice);

		String str = "#container > div > div:nth-child(2) > div > div._3e7xtJ > div._1HmYoV.hCUpcT > div._1HmYoV._35HD7C.col-10-12 > div:nth-child("
				+ t_high + ") > div > div > div";
		webdriver.findElement(By.cssSelector(str)).click();

		ArrayList<String> tabs2 = new ArrayList<String>(webdriver.getWindowHandles());
		webdriver.switchTo().window(tabs2.get(1));

		// --------------Block for the costliest
		WebElement mobileName = webdriver.findElement(By.className("_35KyD6"));
		mobileNameHigh = mobileName.getText();
		System.out.println("Costliest Mobile : " + mobileNameHigh);
		WebElement modelElement = webdriver.findElement(By.cssSelector(
				"#container > div > div:nth-child(2) > div > div > div > div._1GRhLX._3N5d1n > div > div._2Cl4hZ > div.MocXoX > div:nth-child(2) > div > div > div:nth-child(1) > ul > li:nth-child(2) > ul > li"));
		modelHigh = modelElement.getText();
		System.out.println("Model no of mobile: " + modelHigh);

		webdriver.close();
		webdriver.switchTo().window(tabs2.get(0));
		// ------------End CostliestBlock

		// --------------Block for the cheapest
		str = "#container > div > div:nth-child(2) > div > div._3e7xtJ > div._1HmYoV.hCUpcT > div._1HmYoV._35HD7C.col-10-12 > div:nth-child("
				+ t_low + ") > div > div > div";
		webdriver.findElement(By.cssSelector(str)).click();

		tabs2 = new ArrayList<String>(webdriver.getWindowHandles());
		webdriver.switchTo().window(tabs2.get(1));

		// System.out.println("window Title is: "+webdriver.getTitle());
		mobileName = webdriver.findElement(By.className("_35KyD6"));
		mobileNameLow = mobileName.getText();
		System.out.println("Costliest Mobile : " + mobileNameLow);
		modelElement = webdriver.findElement(By.cssSelector(
				"#container > div > div:nth-child(2) > div > div > div > div._1GRhLX._3N5d1n > div > div._2Cl4hZ > div.MocXoX > div:nth-child(2) > div > div > div:nth-child(1) > ul > li:nth-child(2) > ul > li"));
		modelLow = modelElement.getText();
		System.out.println("Cheapest Model Number of mobile: " + modelLow);

		webdriver.close();
		webdriver.switchTo().window(tabs2.get(0));
		// -----End of Cheapest Block
		By nextBy= By.className("_3fVaIS");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(nextBy));
		Thread.sleep(3000);
		WebElement next= webdriver.findElement(nextBy);
		next.click();
		
		Thread.sleep(3000);
		// ------- Block for page 2
		List<WebElement> mobileItems2 = webdriver.findElements(By.className("_1UoZlX"));
		int lengthMax = 40;
		int lengthPrev = mobileItems.size();
		int k = 2;
		int flag_max = 0, flag_min = 0;
		;
		for (int i = 0; i < (lengthMax - lengthPrev); i++) {
			String priceCss2 = "#container > div > div:nth-child(2) > div > div._3e7xtJ > div._1HmYoV.hCUpcT > div._1HmYoV._35HD7C.col-10-12 > div:nth-child("
					+ k
					+ ") > div > div > div > a > div._1-2Iqu.row > div.col.col-5-12._2o7WAb > div._6BWGkk > div > div._1vC4OE._2rQ-NK";
			WebElement web = mobileItems2.get(i);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(priceCss2)));
			WebElement priceMob = web.findElement(By.cssSelector(priceCss2));
			String price = priceMob.getText();
			price = price.replaceAll("\u20B9", "");
			price = price.replaceAll(",", "");
			int intPrice = Integer.parseInt(price);
			System.out.println(intPrice);

			if (intPrice > maxPrice) {
				maxPrice = intPrice;
				t_high = k;
				flag_max = 1;
			} else if (minPrice == 0) {
				minPrice = intPrice;
			} else if (intPrice < minPrice) {
				minPrice = intPrice;
				t_low = k;
				flag_min = 1;
			}
			k++;

		}

		System.out.println("---------- After Page 2---------------");
		System.out.println("Min Price" + minPrice);
		System.out.println("Max Price" + maxPrice);

		// --------------Block for the costliest Page2
		if (flag_max == 1) {
			str = "#container > div > div:nth-child(2) > div > div._3e7xtJ > div._1HmYoV.hCUpcT > div._1HmYoV._35HD7C.col-10-12 > div:nth-child("
					+ t_high + ") > div > div > div";
			webdriver.findElement(By.cssSelector(str)).click();

			tabs2 = new ArrayList<String>(webdriver.getWindowHandles());
			webdriver.switchTo().window(tabs2.get(1));
			System.out.println("---------------Data Page2-------------");
			mobileName = webdriver.findElement(By.className("_35KyD6"));
			mobileNameHigh = mobileName.getText();
			System.out.println("We found cheapest phone on page 2 ==== ");
			System.out.println("Costliest Mobile : " + mobileNameHigh);
			modelElement = webdriver.findElement(By.cssSelector(
					"#container > div > div:nth-child(2) > div > div > div > div._1GRhLX._3N5d1n > div > div._2Cl4hZ > div.MocXoX > div:nth-child(2) > div > div > div:nth-child(1) > ul > li:nth-child(2) > ul > li"));
			modelHigh = modelElement.getText();
			System.out.println("Model no of mobile: " + modelHigh);

			webdriver.close();
			webdriver.switchTo().window(tabs2.get(0));

		} else {
			System.out.println("No change in MAx after visiting page2");
		}
		// ------------End CostliestBlock

		// --------------Block for the cheapest
		if (flag_min == 1) {
			str = "#container > div > div:nth-child(2) > div > div._3e7xtJ > div._1HmYoV.hCUpcT > div._1HmYoV._35HD7C.col-10-12 > div:nth-child("
					+ t_low + ") > div > div > div";
			webdriver.findElement(By.cssSelector(str)).click();

			tabs2 = new ArrayList<String>(webdriver.getWindowHandles());
			webdriver.switchTo().window(tabs2.get(1));

			// System.out.println("window Title is: "+webdriver.getTitle());
			mobileName = webdriver.findElement(By.className("_35KyD6"));
			mobileNameLow = mobileName.getText();
			System.out.println("We found cheapest phone on page 2 ==== ");
			System.out.println("Costliest Mobile : " + mobileNameLow);
			modelElement = webdriver.findElement(By.cssSelector(
					"#container > div > div:nth-child(2) > div > div > div > div._1GRhLX._3N5d1n > div > div._2Cl4hZ > div.MocXoX > div:nth-child(2) > div > div > div:nth-child(1) > ul > li:nth-child(2) > ul > li"));
			modelLow = modelElement.getText();
			System.out.println("Cheapest Model Number of mobile: " + modelLow);

			webdriver.close();
			webdriver.switchTo().window(tabs2.get(0));

		} else {
			System.out.println("No change in Min after visiting page2");
		}
		// -----End of Cheapest Block

	}
}
