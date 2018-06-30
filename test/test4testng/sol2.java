package test4testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class sol2 {
	WebDriver webdriver;
  @Test
  public void f() {
 
	  webdriver.get("https://www.google.com/?gws_rd=ssl");
  }
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("https://www.google.com/");
		webdriver.findElements(By.id("heheheheheheheheh"));
		//webdriver.manage().
  }

  @AfterClass
  public void afterClass() {
	  
  
  
  }

}
