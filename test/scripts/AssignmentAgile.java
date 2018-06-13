package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class AssignmentAgile {

	public static void main(String[] args) {

		File pathToBinary = new File("C:\\Users\\LTI.INFVA07132\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		
		// create instance of driver and visit the web site
		WebDriver webdriver = new FirefoxDriver(ffBinary,firefoxProfile);
		webdriver.get("http://www.agiletestingalliance.org");
		
		// get the image into WebElement object
		WebElement image = webdriver.findElement(By.ById.xpath("/html/body/header/div/div/h1/a/img"));
		
		// get image element width
		int ImageWidth = image.getSize().getWidth();
        System.out.println("Image width Is "+ImageWidth+" pixels");
        
        // get image element height
        int ImageHeight = image.getSize().getHeight();        
        System.out.println("Image height Is "+ImageHeight+" pixels");

	}

}
