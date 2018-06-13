package test4testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class IMDBTest {
	WebDriver webdriver;
  
	
	@Test
  public void f() {
	  
	webdriver.get("https://www.imdb.com");
	  WebElement searchBox = webdriver.findElement(By.xpath("//*[@id=\"navbar-query\"]"));
	  searchBox.sendKeys("Black Panther");
	  searchBox.sendKeys(Keys.RETURN);
	  
	  WebElement bp = webdriver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/table/tbody/tr[1]/td[2]/a"));
	  bp.click();
	  
	  WebElement syno = webdriver.findElement(By.xpath("//*[@id=\"title-overview-widget\"]/div[3]/div[1]/div[1]"));
	  String expected = "T'Challa, the King of Wakanda, rises to the throne in the isolated, technologically advanced African nation, but his claim is challenged by a vengeful outsider who was a childhood victim of T'Challa's father's mistake.";
	  Assert.assertEquals(syno.getText(), expected);
	  
	  WebElement director = webdriver.findElement(By.xpath("//*[@id=\"title-overview-widget\"]/div[3]/div[1]/div[2]"));
	  String directortxt = director.getText();
	  
	  if (directortxt.contains("Ryan Coogler")) {
		System.out.println("Director is Ryan Coogler.");
	} else {
		System.out.println("Director not found");
	}
	  
	  WebDriverWait wait = new WebDriverWait (webdriver, 15);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"title-overview-widget\"]/div[3]/div[1]/div[4]")));
	  WebElement stars = webdriver.findElement(By.xpath("//*[@id=\"title-overview-widget\"]/div[3]/div[1]/div[4]"));
	//*[@id="title-overview-widget"]/div[3]/div[1]/div[4]/span[1]/a/span
	  String starstxt = stars.getText();
	  
	  if (starstxt.contains("Chadwick Boseman")) {
			System.out.println("Star is Chadwick");
		} else {
			System.out.println("Star not found");
		}
  }
	
	@BeforeTest
  public void beforeTest() {

		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@AfterTest
	public void afterTest() {
		webdriver.close();
	}

}
