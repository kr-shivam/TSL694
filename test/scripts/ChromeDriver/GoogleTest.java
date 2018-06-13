package scripts.ChromeDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleTest {

	public static void main(String[] args) {

		/*File pathToBinary = new File("C:\\Users\\LTI.INFVA07132\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		
		WebDriver webdriver = new FirefoxDriver(ffBinary,firefoxProfile);*/
		
		System.setProperty("webdriver.chrome.driver", 
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");
		
		WebDriver webdriver = new ChromeDriver();
		
		webdriver.get("https://www.google.com");
	
	/*IMPLICIT WAIT*/
		
		// Implicit wait used here to wait 10 seconds and invoking a wrong id to test exception.
		
		webdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
	/*EXPLICIT WAIT*/
		
		// Explicit wait used here to wait 5 seconds and invoking a wrong id to test exception.
		
		WebDriverWait wait = new WebDriverWait(webdriver, 6);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lst-ib2")));
		
		webdriver.findElement(By.id("lst-ib")).sendKeys("Selenium Software");
		webdriver.findElement(By.name("btnK")).click();

	}

}
