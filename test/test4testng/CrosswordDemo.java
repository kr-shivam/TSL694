package test4testng;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class CrosswordDemo {
	WebDriver webdriver;
	
  @Test
  public void f() {
	  By searchBoxBy= By.id("search-input");
	  By listOFBooksBy= By.id("search-result-items");
	  
	  
	  WebElement seacrhBoxEl= webdriver.findElement(searchBoxBy);
	  seacrhBoxEl.sendKeys("Cricket");
	  seacrhBoxEl.sendKeys(Keys.ENTER);
	  
	  
	  int initialHeight =webdriver.findElement(listOFBooksBy).getSize().getHeight();
	  int currentHeight=0;
	  while(initialHeight != currentHeight){
		  initialHeight =webdriver.findElement(listOFBooksBy).getSize().getHeight(); 
		  ((JavascriptExecutor)webdriver).executeScript("scroll(0,"+initialHeight+");");
		 try{
			 Thread.sleep(2000);
		 }catch (InterruptedException e) {
				e.printStackTrace();
			}
		 currentHeight= webdriver.findElement(listOFBooksBy).getSize().getHeight();
	  }
	  
	  List<WebElement> bookList= webdriver.findElements(By.xpath("//*[@id=\"search-result-items\"]/li"));
	  
	  @SuppressWarnings("unused")
	int i=0;
	  for(WebElement w: bookList){
		  System.out.println(webdriver.findElement((By) w).getAttribute("alt").toString());
		  i++;
	  }
	  
  }
  @BeforeTest
  public void beforeTest() {
	  System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("http://www.crossword.in/");
		webdriver.manage().window().maximize();
  }

  @AfterTest
  public void afterTest() {
	  webdriver.close();
  }

}
