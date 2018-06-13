package test4testng;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class FlipkartMobileDDT {
	WebDriver webdriver;
	List<Integer> finalPriceList = new ArrayList<>();
	List<String> finalUrlList = new ArrayList<>();

	@DataProvider(name = "DP_POI")
	public Object[][] createData2() {
		Object[][] retObjArr = getExcelData(
				"test/resources/data/flipkart_mobiles.xlsx", "DataPool");
		return (retObjArr);
	}

	public static String[][] getExcelData(String fileName, String sheetName) {

		String[][] arrayExcelData = null;
		Workbook wb = null;
		try {
			File file = new File(fileName);
			FileInputStream fs = new FileInputStream(file);
			System.out.println("File Reading Successful");
			// .xls
			if (fileName.substring(fileName.indexOf(".")).equals(".xlsx")) {

				// If it is xlsx file then create object of XSSFWorkbook class
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

	@Test(dataProvider = "DP_POI")
	public void testDataProviderExample(String mobile) throws Exception {
		
		// Clear the finalPriceList and the finalUrlList for every test.
		finalPriceList.clear();
		finalUrlList.clear();
		
		System.out.println("+++++++++++++*******New Search Begins******+++++++++++++");
		webdriver.get("http://www.flipkart.com/");
		
		// Search for searchBar and search for data retrieved from excel sheet
		By searchX = By.className("LM6RPg");
		WebElement searchBox = webdriver.findElement(searchX);
		searchBox.sendKeys(mobile);
		searchBox.sendKeys(Keys.RETURN);
		
		
		// stored for comparison
		int minPrice = Integer.MAX_VALUE;
		int maxPrice = Integer.MIN_VALUE;

		/*
		 * complete division of a product is selected and all the divisions are returned in the list
		 * this was done because a change in xpath was observed when different test cases are executed.
		 * 
		 *  so the basic idea is for accessing the complete division and then searching for the price in that division
		 *  
		 * */
		
		By mobileItemDivX = By.className("_1UoZlX");
		
		// for clicking next page
		By nextPageClickX = By.className("_3fVaIS");
		
		List<WebElement> ls = webdriver.findElements(mobileItemDivX);
		
		// a method that gets the price of all elements in first page and stores in list 'finalPriceList' and their url in 'finalUrlList'
		
		functional(ls);

		Thread.sleep(3000);
		webdriver.findElement(nextPageClickX).click();
		
		Thread.sleep(3000);
		List<WebElement> ls2 = webdriver.findElements(mobileItemDivX);

		// the problem was to get a list of 40 elements and each page displays 24 results
		// so this loop gets all elements and deletes the extra elements
		
		int x = ls.size();
		for (int i = 0; i < x - (40 - x); i++) {
			ls2.remove(x - i - 1);
			// System.out.println("removing: "+ (x-i+1));
		}
		
		// again the functional method is called and new values are added in price & url lists 
		functional(ls2);

		// Find out the minimum and maximum from the collections
		minPrice = Collections.min(finalPriceList);
		maxPrice = Collections.max(finalPriceList);


		System.out.println("Product Search: " + mobile + "\n\n");
		
		// open the webpage of cheapest product using the corresponding url
		webdriver.get(finalUrlList.get(finalPriceList.indexOf(minPrice)));
		System.out.println("Cheapest "+mobile+" product under top 40 search results: \n");
		// get details of the product
		detailsFetch();
		
		// open the webpage of cheapest product using the corresponding url
		webdriver.get(finalUrlList.get(finalPriceList.indexOf(maxPrice)));
		System.out.println("Most Expensive "+mobile+" product under top 40 search results: \n");
		// get details of the product
		detailsFetch();


		System.getProperties().put(
				"org.apache.commons.logging.impl.SimpleLog.LOG_LEVEL_FATAL",
				"error");

	}

	// Display the product details
	private void detailsFetch() throws InterruptedException {

		Thread.sleep(2000);
		System.out.println("Product Name: "
				+ webdriver.findElement(By.className("_35KyD6")).getText());
		Thread.sleep(2000);
		List<WebElement> productDetails = webdriver.findElements(By
				.className("_3_6Uyw"));
		for (WebElement webElement : productDetails) {
			
			if (webElement.getText().contains("Number")) {
				System.out.println(webElement.getText().replace("\n", " : "));
			}else if (webElement.getText().contains("Name")) {
				System.out.println(webElement.getText().replace("\n", " : "));
			}else if (webElement.getText().contains("Color")) {
				System.out.println(webElement.getText().replace("\n", " : "));
			}
		}
		System.out.println("Price: Rs. "+ webdriver.findElement(By.className("_1vC4OE")).getText().substring(1));
		System.out.println("***********");

	}

	private void functional(List<WebElement> ls) {

		// delimiter for Rupee symbol
		String delim = "\u20B9";

		/*
		 * get every element (division of product)
		 * and within that element, search for the price element
		 * the price element sends extra details as old price and discount percentage
		 * 
		 * all this data is sent in a single string
		 * so we need to remove the following:
		 * 		(,) symbol
		 * 		(\n) character
		 * 		("") whitespaces
		 * 		(\u20B9) Rupee symbol
		 * */
		
		for (WebElement webElement : ls) {

			String x = webElement.findElement(By.className("_1uv9Cb"))
					.getText();

			// if the extra details are sent with price
			if (x.length() > 8) {
				String[] tempo = x.split(delim);
				x = tempo[1];
				x = x.replace(",", "");
				x = x.replace("\n", "");
			} else {
				// if no extra details are present
				x = x.replace("\u20B9", "");
				x = x.replace(",", "");
			}

			// add every price to an Integer list 'finalPriceList'
			finalPriceList.add(Integer.parseInt(x));

			// fetch the corresponding url and add it in a different list 'finalUrlList'

			String url = webElement.findElement(By.className("_31qSD5"))
					.getAttribute("href");
			finalUrlList.add(url);

		}

	}

	@BeforeTest
	public void setUp() throws Exception {
		System.out.println("*****************  1 *************************");

		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();

		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		webdriver.manage().window().maximize();
		webdriver.get("http://www.flipkart.com/");

		By popupX = By.xpath("/html/body/div[2]/div/div/button");
		webdriver.findElement(popupX).click();
		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));

	}

	@AfterTest
	public void afterTest() {
		webdriver.close();
	}

}
