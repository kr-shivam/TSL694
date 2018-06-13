package testjunit;

//import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JqueryTestJunit {

	WebDriver webdriver;

	@Before
	public void setUp() throws Exception {
		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("http://jqueryui.com/");
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		webdriver.quit();
	}
	

	@Test
	public void test() {
		webdriver.findElement(By.xpath("//*[@id=\"sidebar\"]/aside[2]/ul/li[2]/a")).click();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		webdriver.switchTo().frame(0);
		
		webdriver.findElement(By.xpath("//*[@id=\"tags\"]")).sendKeys("a");
		
		//Actions action = new Actions(webdriver);
		
		//WebElement sel = webdriver.findElement(By.id("tags"));
		
		/*sel.sendKeys(Keys.ARROW_DOWN);
		sel.sendKeys(Keys.ARROW_DOWN);
		sel.sendKeys(Keys.ARROW_DOWN);
		sel.sendKeys(Keys.RETURN);*/
		
		WebDriverWait wait = new WebDriverWait(webdriver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ui-id-1\"]/li")));
				
		List<WebElement> allOptions = webdriver.findElements(By.xpath("//*[@id=\"ui-id-1\"]/li"));
		
		
		for (WebElement webElement : allOptions) {
			if(webElement.getText().equalsIgnoreCase("java")){
				webElement.click();
			}
		}
	}

}
