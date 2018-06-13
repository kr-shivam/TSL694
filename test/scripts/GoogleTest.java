package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class GoogleTest {

	public static void main(String[] args) {

		File pathToBinary = new File("C:\\Users\\LTI.INFVA07132\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		
		WebDriver webdriver = new FirefoxDriver(ffBinary,firefoxProfile);
		webdriver.get("https://www.google.com");
		webdriver.findElement(By.id("lst-ib")).sendKeys("Selenium Software");
		webdriver.findElement(By.name("btnK")).click();

	}

}
