package test4testng;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class FlipkartAssignmentDay5 {
	
	WebDriver webdriver;
	
  @Test
  public void f() {
	  WebDriverWait wait = new WebDriverWait(webdriver, 10);
	  
	  By crossLogin = By.xpath("/html/body/div[2]/div/div/button");
	  wait.until(ExpectedConditions.visibilityOfElementLocated(crossLogin));
	  webdriver.findElement(crossLogin).click();
	  
	  By searchBar = By.xpath("//*[@id=\"container\"]/div/header/div[1]/div/div/div/div[1]/form/div/div[1]/div/input");
	  WebElement searchBarEl = webdriver.findElement(searchBar);
	  searchBarEl.sendKeys("The Time of My Life");
	  searchBarEl.sendKeys(Keys.RETURN);
	  
	  
	  By bookXpath = By.xpath("//*[@id=\"container\"]/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/div/div[1]/div/a[1]/div[1]/div/div/img");
	  wait.until(ExpectedConditions.visibilityOfElementLocated(bookXpath));
	  WebElement bookEl = webdriver.findElement(bookXpath);
	  bookEl.click();
	  
	  Set<String> newWindow = webdriver.getWindowHandles();
	  webdriver.switchTo().window((String)newWindow.toArray()[1]);
	  By authorXpath = By.xpath("//*[@id=\"container\"]/div/div[1]/div/div/div/div[1]/div/div[2]/div[5]/div[2]/div/a");
	  wait.until(ExpectedConditions.visibilityOfElementLocated(authorXpath));
	  WebElement authorEl = webdriver.findElement(authorXpath);
	  String authorNameFetch = authorEl.getText();
	  
	  String authorName = "Cecelia Ahern";
	  System.out.println(authorNameFetch);
	  Assert.assertEquals(authorNameFetch, authorName);
  }
  @BeforeTest
  public void beforeTest() {
	  	System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("http://www.flipkart.com/");
		webdriver.manage().window().maximize();
				
  }

  @AfterTest
  public void afterTest() {
	  webdriver.quit();
  }

}
