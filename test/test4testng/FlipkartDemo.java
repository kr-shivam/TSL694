package test4testng;

import java.util.Set;

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

public class FlipkartDemo {

	WebDriver webdriver;

	@Test
	public void f() {

		By loginPrompt = By.xpath("/html/body/div[2]/div/div/button");   // Absolute XPath Used
		WebDriverWait wait = new WebDriverWait(webdriver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginPrompt));
		webdriver.findElement(loginPrompt).click();

		By SearchInput = By
				.xpath("//*[@id=\"container\"]/div/header/div[1]/div/div/div/div[1]/form/div/div[1]/div/input");	//Relative XPath
		WebElement searchBarEl = webdriver.findElement(SearchInput);
		searchBarEl.click();
		searchBarEl.sendKeys("The Time of My Life");
		searchBarEl.sendKeys(Keys.ENTER);

		By bookXpath = By
				.xpath("//*[@id=\"container\"]/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/div/div[1]/div/a[1]/div[1]/div/div/img");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bookXpath));
		webdriver.findElement(bookXpath).click();

		Set<String> newWindow = webdriver.getWindowHandles();
		webdriver.switchTo().window((String) newWindow.toArray()[1]);
		By author= By.xpath("//*[@id=\"container\"]/div/div[1]/div/div/div/div[1]/div/div[2]/div[5]/div[2]/div/a");
		WebElement authorEl = webdriver.findElement(author);
		String authorNameFetch = authorEl.getText();

		String authorName = "Cecelia Ahern";
		System.out.println(authorNameFetch);
		Assert.assertEquals(authorNameFetch, authorName);

	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("http://www.flipkart.com/");
		webdriver.manage().window().maximize();
	}

	@AfterTest
	public void afterTest() {
		webdriver.quit();
	}

}
