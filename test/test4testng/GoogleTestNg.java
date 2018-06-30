package test4testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class GoogleTestNg {

	WebDriver webdriver;

	@Test
	public void f() {

		webdriver.findElement(By.className("gsfi")).sendKeys("Selenium Software");  //find by className
		webdriver.findElement(By.name("btnK")).click();
		
	}

	@BeforeTest
	public void beforeTest() {

		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("https://www.google.com");
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterTest
	public void afterTest() {
		webdriver.close();
	}

}
