package testjunit;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AgileTestJunit {
	WebDriver webdriver;

	@Before
	public void setUp() throws Exception {
System.setProperty("webdriver.chrome.driver", "D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");
		
		webdriver = new ChromeDriver();
		webdriver.get("http://www.agiletestingalliance.org");
		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
	
		webdriver.quit();
	}

	@Test
	public void test() {
		
		//fail("Not yet implemented");
		for(int i=1; i<5; i++){
			String text = webdriver.findElement(By.xpath("/html/body/footer/div/a["+i+"]")).getAttribute("href");
			System.out.println(text);
		}
		
		
	}

}
