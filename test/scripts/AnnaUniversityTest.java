package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

public class AnnaUniversityTest {

	public static void main(String[] args) {
		
		File pathToBinary = new File("C:\\Users\\LTI.INFVA07132\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		
		WebDriver webdriver = new FirefoxDriver(ffBinary,firefoxProfile);
		webdriver.get("https://www.annauniv.edu");
		WebElement webelement = webdriver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[1]/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td[5]/div/a"));
		webelement.click();
				
		WebElement web2 = webdriver.findElement(By.xpath("//*[@name='link13']"));
		WebElement web3 = webdriver.findElement(By.id("menuItemHilite33"));
		Actions actions = new Actions(webdriver);
		
		actions.moveToElement(web2).click(web3).build().perform();
		System.out.println("Title: "+webdriver.getTitle());
		
		WebElement web4 = webdriver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td"));
		System.out.println("\n"+web4.getText()+"\n-------------------\n");
		
		
		WebElement web5 = webdriver.findElement(By.xpath("//*[@name='link2']"));
		WebElement web6 = webdriver.findElement(By.id("menuItemHilite13"));
				
		actions.moveToElement(web5).click(web6).build().perform();
		System.out.println("Title: "+webdriver.getTitle());
		
		WebElement web7 = webdriver.findElement(By.xpath("/html/body/table/tbody/tr[4]/td/table/tbody/tr/td[1]/table[1]/tbody/tr/td/p"));
		System.out.println("\n"+web7.getText()+"\n-------------------\n");
		
		

	}

}
