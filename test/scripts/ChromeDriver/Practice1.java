/*package scripts.ChromeDriver;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

public class Practice1 {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");
		WebDriver webdriver = new ChromeDriver();
		webdriver.get("https://wikipedia.org");
		
		//webdriver.findElement(arg0)
		
		List<WebElement> ls = webdriver.findElements(By.id("searchLanguage"));
		
		for (WebElement webElement : ls) {
			System.out.println((webElement).getText());
			
		}
		
		System.out.println("done");
		//WebElement x = webdriver.findElement(By.xpath("//*[@id=\"searchInput\"]"));
		x.sendKeys("Wikipedia");
		x.sendKeys(Keys.RETURN);
		System.out.println(webdriver.getTitle());
		
	}

}
*/