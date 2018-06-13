package test4testng;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class DDTImdb {

	WebDriver webdriver;

	@SuppressWarnings("resource")
	@Test
	public void f() throws IOException {
		webdriver.get("https://www.imdb.com");

		int rows = 1;
		String split = ",";
		BufferedReader br = new BufferedReader(new FileReader(
				"d:\\moviesddt.csv"));
		String line = br.readLine();
		
		// Get Number of Columns
		int cols = line.split(split).length;
		int i = 0;

		//Get number of Rows
		while ((line = br.readLine()) != null) {
			rows++;
		}

		// Reinit br object for getting pointer at top
		
		br = new BufferedReader(new FileReader("d:\\moviesddt.csv"));

		String[][] b = new String[rows][cols];
		
		// Put data into multidimensional array
		while ((line = br.readLine()) != null) {

			String[] str = line.split(split);

			for (int k = 0; k < cols; k++) {
				b[i][k] = str[k];
			}
			i++;
		}
		br.close();

		/* Print Elements of Array
		 *
		 * for (int j = 0; j < rows; j++) { for (int j2 = 0; j2 < cols; j2++) {
		 * System.out.println(b[j][j2]); } }
		 */

		// Perform test

		for (int c_rows = 1; c_rows < rows; c_rows++) {
			int c_cols = 0;
			WebDriverWait wait = new WebDriverWait(webdriver, 15);

			WebElement searchBox = webdriver.findElement(By
					.xpath("//*[@id=\"navbar-query\"]"));

			searchBox.sendKeys(b[c_rows][c_cols]);
			searchBox.sendKeys(Keys.RETURN);
			System.out.println("Movie: " + b[c_rows][c_cols]);
			c_cols++;

			By titleX = By
					.xpath("//*[@id=\"main\"]/div/div[2]/table/tbody/tr[1]/td[2]/a");
			WebElement bp = webdriver.findElement(titleX);
			bp.click();
			//*[@id="title-overview-widget"]/div[3]/div[2]/div[1]/div[2]
			
			By directorX = By
					.xpath("//*[@id=\"title-overview-widget\"]/div[3]/div[1]/div[2]");
			wait.until(ExpectedConditions.visibilityOfElementLocated(directorX));
			WebElement director = webdriver.findElement(directorX);
			String directortxt = director.getText();

			if (directortxt.contains(b[c_rows][c_cols])) {
				System.out.println("Director: " + b[c_rows][c_cols]);
			} else {
				System.out.println("Director not found");
			}
			c_cols++;

			

			By starsX = By
					.xpath("//*[@id=\"title-overview-widget\"]/div[3]/div[1]/div[4]");
			wait.until(ExpectedConditions.visibilityOfElementLocated(starsX));
			WebElement stars = webdriver.findElement(starsX);

			String starstxt = stars.getText();

			if (starstxt.contains(b[c_rows][c_cols])) {
				System.out.println("Star: " + b[c_rows][c_cols]);
			} else {
				System.out.println("Star not found");
			}

			System.out.println("-------------------");
		}

	}

	@BeforeTest
	public void beforeTest() {

		System.setProperty(
				"webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");

		webdriver = new ChromeDriver();

	}

	@AfterTest
	public void afterTest() {

		webdriver.close();
	}

}
