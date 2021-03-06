package scripts.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WikipediaTest {

	public static void main(String[] args) {

		/*File pathToBinary = new File("C:\\Users\\LTI.INFVA07132\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		
		WebDriver webdriver = new FirefoxDriver(ffBinary,firefoxProfile);*/
		
		System.setProperty("webdriver.chrome.driver", 
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");
		
		WebDriver webdriver = new ChromeDriver();
		
		webdriver.get("https://www.wikipedia.org");
		webdriver.findElement(By.id("search-input")).sendKeys("Wiki");
		webdriver.findElement(By.id("js-link-box-en")).click();
		webdriver.findElement(By.id("searchInput")).sendKeys("selenium software");
		webdriver.findElement(By.id("searchButton")).click();
		System.out.println(webdriver.getTitle()+"\n");
		WebElement webelement = webdriver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div/p[1]"));
		String text = webelement.getText();
		
		System.out.println(text);

	}

}
