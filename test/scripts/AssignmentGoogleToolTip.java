package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class AssignmentGoogleToolTip {

	public static void main(String[] args) {
		
		File pathToBinary = new File("C:\\Users\\LTI.INFVA07132\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		
		// create instance of driver and visit the web site
		WebDriver webdriver = new FirefoxDriver(ffBinary,firefoxProfile);
		webdriver.get("https://google.com");
		
		// get the image into WebElement object
		WebElement image = webdriver.findElement(By.xpath("//*[@id=\"lst-ib\"]"));
		
		System.out.println("Tool tip: "+image.getAttribute("title"));

	}

}
