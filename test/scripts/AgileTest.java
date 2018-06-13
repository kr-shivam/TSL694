package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class AgileTest {

	public static void main(String[] args) {

		File pathToBinary = new File("C:\\Users\\LTI.INFVA07132\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		
		WebDriver webdriver = new FirefoxDriver(ffBinary,firefoxProfile);
		webdriver.get("http://www.agiletestingalliance.org");
		
		
		for(int i=1; i<5; i++){
			String text = webdriver.findElement(By.xpath("/html/body/footer/div/a["+i+"]")).getAttribute("href");
			System.out.println(text);
		}
		
	
		

	}

}
