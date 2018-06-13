package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class AtaCalcTest {

	public static void main(String[] args) {
		File pathToBinary = new File("C:\\Users\\LTI.INFVA07132\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		
		WebDriver webdriver = new FirefoxDriver(ffBinary,firefoxProfile);
		webdriver.get("http://ata123456789123456789.appspot.com");
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
		String el = webdriver.findElement(By.id("ID_nameField3")).getAttribute("value");
		System.out.println(el);		

	}

}
