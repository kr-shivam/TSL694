package test4testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PageFactoryLoginTest {

	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://205.147.102.59:9001/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void logintestPF() {
		PageFactoryLogin loginpageF = new PageFactoryLogin(driver);
		driver.findElement(By.cssSelector("#show_login")).click();
		loginpageF.login("narendra@qaagility.com", "test@123");
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginpageF.checkIfErrorExists();
		loginpageF.login("narendra.gupta@qaagility.com", "test@123");
		loginpageF.checkIfErrorExists();
	}

	@AfterTest
	public void afterTest() {
		// driver.quit();
	}

}
