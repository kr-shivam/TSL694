package testjunit;

//import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AssignmentAgileJunit {
	WebDriver webdriver;

	@Before
	public void setUp() throws Exception {
		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("http://www.agiletestingalliance.org");
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		webdriver.quit();
	}

	@Test
	public void test() {
		WebElement image = webdriver.findElement(By
				.xpath("/html/body/header/div/div/h1/a/img"));

		// get image element width
		int ImageWidth = image.getSize().getWidth();
		System.out.println("Image width Is " + ImageWidth + " pixels");

		// get image element height
		int ImageHeight = image.getSize().getHeight();
		System.out.println("Image height Is " + ImageHeight + " pixels");
	}

}
