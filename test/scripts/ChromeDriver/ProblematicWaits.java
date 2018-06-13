package scripts.ChromeDriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProblematicWaits {

public static void main(String[] args) {
		
		// Opening chrome browser
		System.setProperty("webdriver.chrome.driver", "D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		
		// setting implicit time
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
		// Loading a URL
		driver.get("https://www.redbus.in/");
		
		// defining explicit wait
		WebDriverWait wait= new WebDriverWait(driver, 10);
		// Locating and typing in From text box. 
		
		System.out.println("Wait starts:"+new Date());
		try{
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Wrong Locator"))));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Wait ends:"+new Date());
		
		
		driver.quit();
		
	}

}
