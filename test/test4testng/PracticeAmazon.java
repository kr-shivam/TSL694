package test4testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class PracticeAmazon {
	
	WebDriver webdriver;
	
	
  @Test
  public void f() {
	  webdriver.get("https://www.amazon.in");
  }
  
  
  @BeforeTest
  public void beforeTest() {
	  
	  System.setProperty("webdriver.chrome.driver", "test\\resources\\drivers\\chromedriver.exe");
	  webdriver = new ChromeDriver();
	  webdriver.manage().window().maximize();
	  
  }
  
  @DataProvider(name="amazonList")
  public Object[][] findObject(){
	  
	  Object[][] createdObject = tableExcelData("test/resources/data/imdb_data1.xls", "DataPool",
				"imdbTestData1");
	return createdObject;
	  
  }

  private Object[][] tableExcelData(String productName, String itemName, String price) {
	// TODO Auto-generated method stub
	return null;
}


@AfterTest
  public void afterTest() {
	  webdriver.quit();
  }

}
