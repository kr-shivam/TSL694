/*Author: Kumar Shivam*/

package scripts;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssignmentDragAndDrop {
	
	public static void main(String[] args) {
		File pathToBinary = new File("C:\\Users\\LTI.INFVA07132\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		
		// create instance of driver and visit the web site
		WebDriver webdriver = new FirefoxDriver(ffBinary,firefoxProfile);
		webdriver.get("http://code.makery.ch/library/dart-drag-and-drop/");
		webdriver.manage().window().maximize();

		
		long timeoutInSeconds = 10;
		WebDriverWait wait = new WebDriverWait(webdriver, timeoutInSeconds );
		

		webdriver.switchTo().frame(0);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div/img[1]")));
		
		WebElement trash = webdriver.findElement(By.xpath("//div[@class='trash']"));
		
		Actions builder = new Actions(webdriver);
		
		
	/*Author's notes
	 * The element[1] after being dragged to trash is removed from the list of 4 elements and the 2nd takes the id[1]
	 * so we are using img[1] in the counter to drag 4 times*/
		
		int j=1;
		while(j<5){
			
			WebElement ls= webdriver.findElement(By.xpath("/html/body/div/img[1]"));
			Action dragAndDrop = builder.clickAndHold(ls)
					.moveToElement(trash)	
					.release(trash)					
					.build();
			dragAndDrop.perform();
			
			j++;
		}
		
		System.out.println("Action Completed");

		}
	
	
}
