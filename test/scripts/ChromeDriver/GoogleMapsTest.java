package scripts.ChromeDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleMapsTest {

	public static void main(String[] args) {
		
		/*File pathToBinary = new File("C:\\Users\\LTI.INFVA07132\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		
		WebDriver webdriver = new FirefoxDriver(ffBinary,firefoxProfile);*/
		
		System.setProperty("webdriver.chrome.driver", 
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");
		
		WebDriver webdriver = new ChromeDriver();
		
		webdriver.get("https://maps.google.com");
		
		File scrFile = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("d:\\tmp\\screenshot1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		webdriver.findElement(By.id("searchboxinput")).sendKeys("Harvard Business School, Boston, United States");
		webdriver.findElement(By.id("searchbox-searchbutton")).click();
		
		String rating = "//*[@id=\"pane\"]/div/div[1]/div/div/div[16]/div/span/span";
		String phnumber = "//*[@id=\"pane\"]/div/div[1]/div/div/div[7]/div/div[1]/span[3]/span[3]";
		long timeoutInSeconds = 15;
		WebDriverWait wait = new WebDriverWait(webdriver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rating)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(phnumber)));
		
		System.out.println("Rating: "+webdriver.findElement(By.xpath(rating)).getText());
		System.out.println("Phone Number: "+webdriver.findElement(By.xpath(phnumber)).getText());
		
		
		File scrFile2 = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile2, new File("d:\\tmp\\screenshot2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
