package test4testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class AlertTestNG {
	
	WebDriver webdriver; 
  @Test
  public void f() {
	
		// fail("Not yet implemented");
		By clickobj = By.xpath("//*[@id=\"content\"]/div/ul/li[1]/button");
		webdriver.findElement(By.xpath(""));

		
		webdriver.findElement(clickobj).click();
		webdriver.switchTo().alert().accept();
		By resultobj = By.id("result");

		WebElement e = webdriver.findElement(resultobj);
		System.out.println(e.getText());
		By secondobj = By.xpath("//*[@id=\"content\"]/div/ul/li[2]/button");
		webdriver.findElement(secondobj).click();
		webdriver.switchTo().alert().accept();

		WebElement e1 = webdriver.findElement(resultobj);
		System.out.println(e1.getText());
		
		webdriver.findElement(secondobj).click();
		webdriver.switchTo().alert().dismiss();
		System.out.println(webdriver.findElement(resultobj).getText());
		
		By thirdObj = By.xpath("//*[@id=\"content\"]/div/ul/li[3]/button");
		webdriver.findElement(thirdObj).click();
		webdriver.switchTo().alert().sendKeys("Vikash");
		
		webdriver.switchTo().alert().accept();
		System.out.println(webdriver.findElement(resultobj).getText());
		Assert.assertEquals(webdriver.findElement(resultobj).getText(), "You entered: Vikash");
		
	}
  @BeforeTest
  public void beforeTest() {
	  
	  System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("http://the-internet.herokuapp.com/javascript_alerts");
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @AfterTest
  public void afterTest() {
	  
	  webdriver.quit();
  }

}
