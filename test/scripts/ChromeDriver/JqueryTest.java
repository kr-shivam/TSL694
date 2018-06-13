package scripts.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JqueryTest {

	public static void main(String[] args) {
		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		WebDriver webdriver = new ChromeDriver();
		webdriver.get("http://jqueryui.com/");

		webdriver.findElement(By.xpath("//*[@id=\"sidebar\"]/aside[2]/ul/li[2]/a")).click();
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		webdriver.switchTo().frame(0);
		
		webdriver.findElement(By.xpath("//*[@id=\"tags\"]")).sendKeys("a");
		
		//Actions action = new Actions(webdriver);
		
		//WebElement sel = webdriver.findElement(By.id("tags"));
		
		/*sel.sendKeys(Keys.ARROW_DOWN);
		sel.sendKeys(Keys.ARROW_DOWN);
		sel.sendKeys(Keys.ARROW_DOWN);
		sel.sendKeys(Keys.RETURN);*/
		
		WebDriverWait wait = new WebDriverWait(webdriver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ui-id-1\"]/li")));
				
		List<WebElement> allOptions = webdriver.findElements(By.xpath("//*[@id=\"ui-id-1\"]/li"));
		
		
		/*String line[]= options.getText().split("\\r?\\n");
		for(String l: line){
			System.out.println("-"+l);
		}
		webdriver.findElement(By.xpath("//*[@id=\"tags\"]")).clear();
		webdriver.findElement(By.xpath("//*[@id=\"tags\"]")).sendKeys(line[1]);;*/
		
		for (WebElement webElement : allOptions) {
			if(webElement.getText().equalsIgnoreCase("java")){
				webElement.click();
			}
		}
		
		
	
	}
}
