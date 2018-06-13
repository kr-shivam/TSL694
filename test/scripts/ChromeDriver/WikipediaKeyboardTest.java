package scripts.ChromeDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WikipediaKeyboardTest {

	public static void main(String[] args) {
		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		WebDriver webdriver = new ChromeDriver();

		webdriver.get("https://www.wikipedia.org");

		By english = By.id("js-link-box-en");
		webdriver.findElement(english).click();
		webdriver.findElement(By.id("searchInput")).sendKeys(
				"Selenium Software");

		WebElement w = webdriver.findElement(By.id("searchInput"));
		w.sendKeys(Keys.ENTER);

		System.out.println(webdriver.getTitle() + "\n");

		By xpathText = By.xpath("//*[@id=\"mw-content-text\"]/div/p[1]");
		WebElement webelement = webdriver.findElement(xpathText);
		String text = webelement.getText();

		System.out.println(text);

	}

}
