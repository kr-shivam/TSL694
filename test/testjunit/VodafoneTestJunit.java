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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VodafoneTestJunit {

	WebDriver webdriver;

	@Before
	public void setUp() throws Exception {
		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("http://www.vodafone.in");
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		webdriver.quit();
	}
	
	@Test
	public void test() {
		String adXpath ="//*[@id=\"ctl00_QN_SmartBanner_divPTOBanner\"]/a";
		
		long timeoutInSeconds = 15;
		WebDriverWait wait = new WebDriverWait(webdriver, timeoutInSeconds );
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(adXpath)));
		webdriver.findElement(By.xpath(adXpath)).click();
		webdriver.findElement(By.xpath("//*[@id=\"ctl00_TM_liLocator\"]/a")).click();
		
		Select dropdown = new Select(webdriver.findElement(By.id("ctl00_CU_ddlCircle")));
		List<WebElement> allOptions = dropdown.getOptions();
		
		System.out.println(allOptions.size());
		
		for (WebElement webElement : allOptions) {
			System.out.println(webElement.getText());
		}
		
		dropdown.selectByVisibleText("Bihar and Jharkhand");
		System.out.println("Bihar and Jharkhand Selected");
		
		dropdown = new Select(webdriver.findElement(By.id("ctl00_CU_ddlCircle")));
		dropdown.selectByIndex(17);
		System.out.println("Odisha Selected by ID= 17");
		
		dropdown = new Select(webdriver.findElement(By.id("ctl00_CU_ddlCircle")));
		dropdown.selectByValue("mumbai");
		System.out.println("Mumbai Selected");

	}

}
