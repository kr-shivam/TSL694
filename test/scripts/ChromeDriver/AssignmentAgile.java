package scripts.ChromeDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AssignmentAgile {

	public static void main(String[] args) {

		/*File pathToBinary = new File("C:\\Users\\LTI.INFVA07132\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		
		// create instance of driver and visit the web site
		WebDriver webdriver = new FirefoxDriver(ffBinary,firefoxProfile); */
		
		System.setProperty("webdriver.chrome.driver", 
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");
		
		WebDriver webdriver = new ChromeDriver();
		webdriver.get("http://www.agiletestingalliance.org");
		
		// get the image into WebElement object
		WebElement image = webdriver.findElement(By.xpath("/html/body/header/div/div/h1/a/img"));
		
		// get image element width
		int ImageWidth = image.getSize().getWidth();
        System.out.println("Image width Is "+ImageWidth+" pixels");
        
        // get image element height
        int ImageHeight = image.getSize().getHeight();        
        System.out.println("Image height Is "+ImageHeight+" pixels");

	}

}
