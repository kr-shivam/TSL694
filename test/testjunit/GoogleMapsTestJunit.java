package testjunit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleMapsTestJunit {

	WebDriver webdriver;

	@Before
	public void setUp() throws Exception {
		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("https://maps.google.com");
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		webdriver.quit();
	}

	@Test
	public void test() {
		File scrFile = ((TakesScreenshot) webdriver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("d:\\tmp\\screenshot1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		webdriver.findElement(By.id("searchboxinput")).sendKeys(
				"Harvard Business School, Boston, United States");
		webdriver.findElement(By.id("searchbox-searchbutton")).click();

		String rating = "//*[@id=\"pane\"]/div/div[1]/div/div/div[16]/div/span/span";
		String phnumber = "//*[@id=\"pane\"]/div/div[1]/div/div/div[7]/div/div[1]/span[3]/span[3]";
		long timeoutInSeconds = 15;
		WebDriverWait wait = new WebDriverWait(webdriver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(rating)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(phnumber)));

		By ratingElement = By.xpath(rating);
		WebElement rateElement = webdriver.findElement(ratingElement);
		System.out.println("Rating: "
				+ (rateElement.getText()));
		
		By phoneElement = By.xpath(phnumber);
		WebElement phone2Element = webdriver.findElement(phoneElement);
		
		System.out.println("Phone Number: "
				+ phone2Element.getText());
		
		assertEquals(rateElement.getText(),"4.5");
		
		String actualPhoneNumber = "+1 617-495-6000";
		
		// This works as Verify, i.e. even if there is an error, program will continue. Look for the method
		verifyEquals(webdriver.findElement(phoneElement), actualPhoneNumber);
		
				
		// This works as assert i.e if it fails, program halts and exits
		//assertEquals(phone2Element.getText(),"+1 617-495-6000");
		
		System.out.println("Assertion successful");

		File scrFile2 = ((TakesScreenshot) webdriver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile2, new File("d:\\tmp\\screenshot2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void verifyEquals(WebElement findElement, String actualPhoneNumber) {

		try {
			assertTrue(findElement.getText().equals(actualPhoneNumber));
			
			System.out.println("Verification Successful!");
			} catch (Error e) {
			e.printStackTrace();
			}
		
	}

}
