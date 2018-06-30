package test4testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class GoogleDay8 {
	WebDriver webdriver;
	
	
	@Test
  public void f() {
		webdriver.navigate().refresh();
	  webdriver.get("https://www.google.com.bd/");
	  webdriver.navigate().refresh();
	//  webdriver.findElement(By.id("gbqfq"));
	  webdriver.findElement(By.id("lst-ib")).sendKeys("\uE035");
	 //webdriver.executeScript("location.reload()");
	}
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", "test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		//WebDriverWait wait = new WebDriverWait(webdriver, 10);
		//wait.until(ExpectedConditions.)
		//webdriver.findElement(By.id("")).
		//webdriver.manage().
		//webdriver.findElement(By.id("")).
  }

  @AfterTest
  public void afterTest() {
  }

}
