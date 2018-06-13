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

public class CrosswordAssignmentDay5 {
	
	WebDriver webdriver;
  @Test
  public void f() {
	
	  By searchBox = By.id("search-input");
	  By listOfBooks = By.id("search-result-items");
	  WebElement searchBoxEl = webdriver.findElement(searchBox);
	  
	  searchBoxEl.sendKeys("Cricket");
	  searchBoxEl.sendKeys(Keys.ENTER);
	  
	  int initialHeight = webdriver.findElement(listOfBooks).getSize().getHeight();
	  int currentHeight = 0;

	  while(initialHeight != currentHeight){
	          initialHeight = webdriver.findElement(listOfBooks).getSize().getHeight();

	          //Scroll to bottom
	          ((JavascriptExecutor) webdriver).executeScript("scroll(0," + initialHeight + ");");

	          System.out.println("Waiting for more Elements . . .");
	          try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	          currentHeight = webdriver.findElement(listOfBooks).getSize().getHeight();
	  }
	  By bookListX= By.xpath("//*[@id=\"search-result-items\"]/li");
	  List<WebElement> allBooks = webdriver.findElements(bookListX);
	  
	  
	  int i=1;
	  while(i<=allBooks.size()) {
		  System.out.println(webdriver.findElement(By.xpath("//*[@id=\"search-result-items\"]/li["+i+"]/div/div[1]/a/span/span[3]/img")).getAttribute("alt").toString());
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
	  webdriver.quit();
  }

}
