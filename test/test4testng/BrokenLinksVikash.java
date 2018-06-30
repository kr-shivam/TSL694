package test4testng;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BrokenLinksVikash {
	WebDriver webdriver;
	
  @Test
  public void f() {
	  List <WebElement> elementLinks= webdriver.findElements(By.tagName("a"));
	  
	  for (WebElement link : elementLinks) {
		String url= link.getAttribute("href").toString();
		
		verifyLink(url);
	}
  }
  private void verifyLink(String urlLink) {
	
	  try {
		URL url= new URL(urlLink);
		HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
		 httpURLConnect.setConnectTimeout(3000);
         
         httpURLConnect.connect();
         
         if(httpURLConnect.getResponseCode()==200)
         {
             System.out.println(urlLink+" - "+httpURLConnect.getResponseMessage());
          }
        if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
         {
             System.out.println(urlLink+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
          }
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
@BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", "test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("https://www.flipkart.com/");
		By popupX = By.xpath("/html/body/div[2]/div/div/button");
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webdriver.findElement(popupX).click();

	}

	@AfterTest
	public void afterTest() {
		webdriver.close();
	}
}
