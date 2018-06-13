package testjunit;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WikipidiaTestJnuit {

	WebDriver webdriver;

	@Before
	public void setUp() throws Exception {
		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("https://www.wikipedia.org");
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		//webdriver.quit();
	}
	
	@Test
	public void test() {
		webdriver.findElement(By.id("js-link-box-en")).click();
		webdriver.findElement(By.id("searchInput")).sendKeys("selenium software");
		webdriver.findElement(By.id("searchButton")).click();
		System.out.println(webdriver.getTitle()+"\n");
		WebElement webelement = webdriver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div/p[1]"));
		String text = webelement.getText();
		
		System.out.println(text);
		
		String textToAssert = "The page \"Selenium software\" does not exist. You can ask for it to be created, but consider checking the search results below to see whether the topic is already covered.";
		By textxpath = By.xpath("//*[@id=\"mw-content-text\"]/div/p[1]/i");
		WebElement webel = webdriver.findElement(textxpath);
		
		assertEquals(webel.getText(), textToAssert);

	}

}
