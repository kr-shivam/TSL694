package scripts.ChromeDriver;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyNew {

	public static void main(String[] args) {

		
		
		System.setProperty("webdriver.chrome.driver", 
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");
		
		WebDriver webdriver = new ChromeDriver();
		
		webdriver.get("https://www.wikipedia.org");
			List<WebElement> allText = webdriver.findElements(By.xpath("//*[@id=\"searchLanguage\"]"));
		for ( WebElement element: allText) { 
		    System.out.println(element.getText());
		}
		webdriver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys("wikipedia");
		webdriver.findElement(By.xpath("//*[@id=\"search-form\"]/fieldset/button/i")).click();;
		String str=webdriver.getTitle();
		System.out.println(str);
		

	}

}
