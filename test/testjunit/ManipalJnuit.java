package testjunit;

//import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManipalJnuit {

	WebDriver webdriver;

	@Before
	public void setUp() throws Exception {
		System.setProperty(
				"webdriver.chrome.driver",
				"D:\\workspace-selenium_maven_jenkins\\TSL694\\test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();
		webdriver.get("https://manipal.edu/mu.html");

		webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		webdriver.quit();
	}

	@Test
	public void test() {

		Actions actions = new Actions(webdriver);

		long timeoutInSeconds = 15;
		WebDriverWait wait = new WebDriverWait(webdriver, timeoutInSeconds);

		String academicXpath = "//*[@id=\"primary-sticky\"]/div/ul/li[2]/a";
		String institutionXpath = "//*[@id=\"fat-menu\"]/div/ul/li[3]";
		String engineeringXpath = "//*[@id=\"fat-menu\"]/div/div/ul[1]/li[7]";
		// String departmentAndFacultyXpath =
		// "//*[@id=\"fat-menu\"]/div/div/ul[2]/li[1]/ul[1]/li[3]/a";

		// find Academics tab and hover
		WebElement academics = webdriver.findElement(By.xpath(academicXpath));
		actions.moveToElement(academics).build().perform();

		// find Institution tab and hover
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(institutionXpath)));
		WebElement institution = webdriver.findElement(By
				.xpath(institutionXpath));
		actions.moveToElement(institution).build().perform();

		// find Engineering tab and hover
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(engineeringXpath)));
		WebElement engineering = webdriver.findElement(By
				.xpath(engineeringXpath));
		actions.moveToElement(engineering).build().perform();

		// find Institution tab and hover, then click

		/*
		 * Developer's note The "xpath" is not working as the link is
		 * auto-generated and no id is assigned Mysteriously, it is working
		 * sometimes, when it gets the correct xpath as assigned in this
		 * program, but is not reliable As this link is the first to appear in
		 * the page, it can be accessed by partialLinkText or linkText
		 */

		/*
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * departmentAndFacultyXpath))); WebElement departmentAndFaculty =
		 * webdriver.findElement(By.xpath(departmentAndFacultyXpath));
		 * actions.moveToElement
		 * (departmentAndFaculty).click().build().perform();
		 */

		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.partialLinkText("Dept")));
		WebElement departmentAndFaculty = webdriver.findElement(By
				.partialLinkText("Dept"));
		actions.moveToElement(departmentAndFaculty).click().build().perform();

		String var1 = webdriver.findElement(
				By.xpath("//*[@id=\"quick-facts\"]/div[2]/div[2]/div/div[1]"))
				.getText();
		String var2 = webdriver.findElement(
				By.xpath("//*[@id=\"quick-facts\"]/div[2]/div[5]/div/div[1]"))
				.getText();
		String var3 = webdriver.findElement(
				By.xpath("//*[@id=\"quick-facts\"]/div[2]/div[7]/div/div[1]"))
				.getText();
		String var4 = webdriver.findElement(
				By.xpath("//*[@id=\"quick-facts\"]/div[2]/div[10]/div/div[1]"))
				.getText();
		String var5 = webdriver.findElement(
				By.xpath("//*[@id=\"quick-facts\"]/div[2]/div[12]/div/div[1]"))
				.getText();

		System.out.println(Integer.parseInt(var1) + Integer.parseInt(var2));

		System.out.println("Acres: " + var1);
		System.out.println("Students: " + var2);
		System.out.println("Rank in Indian Univ. in Research: " + var3);
		System.out.println("Rank in private engg schools: " + var4);
		System.out.println("Scholarship to EWS students: " + var5);

		File scrFile2 = ((TakesScreenshot) webdriver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile2, new File("d:\\tmp\\Manipal.png"));
			System.out
					.println("\nScreenshot Taken & saved in 'd:\\tmp\\Manipal.png'");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
