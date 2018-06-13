package testjunit;

//import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AtaCalcTestJunit {
	WebDriver webdriver;

	@Before
	public void setUp() throws Exception {
		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("http://ata123456789123456789.appspot.com");
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		webdriver.quit();
	}

	@Test
	public void test() {
		By xyz = By.id("ID_nameField1");
		webdriver.findElement(xyz).sendKeys("400");
		webdriver.findElement(By.id("ID_nameField2")).sendKeys("12");
		webdriver.findElement(By.id("gwt-uid-2")).click();
		webdriver.findElement(By.id("ID_calculator")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String el = webdriver.findElement(By.id("ID_nameField3")).getAttribute(
				"value");
		System.out.println(el);
	}

}
