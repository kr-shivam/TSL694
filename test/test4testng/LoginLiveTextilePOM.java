package test4testng;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginLiveTextilePOM {

	// initialize the objects

	// #login_email
	By loginEmail = By.cssSelector("#login_email");

	// #login_password
	By loginPwd = By.cssSelector("#login_password");
	// #login_submit
	By loginSubmit = By.cssSelector("#login_submit");
	// #login_close > i
	By loginClose = By.cssSelector("#login_close > i");
	// #show_forgot
	By forgotPwd = By.cssSelector("#show_forgot");
	// #error
	By errorMessage = By.cssSelector("#error");
	// #login_form > h1
	By loginHeader = By.cssSelector("#login_form > h1");
	// #login_display > div > div
	By loginDisplay = By.cssSelector("#login_display > div > div");

	WebDriver driver;

	// Constructor to set the local driver object in this class
	public LoginLiveTextilePOM(WebDriver driver) {

		this.driver = driver;

	}

	// Get the title of Login Page

	public String getTitle() {

		return driver.getTitle();

	}

	public void login(String loginstr, String pwdstr) {

		driver.findElement(loginEmail).clear();
		driver.findElement(loginPwd).clear();
		driver.findElement(loginEmail).click();
		driver.findElement(loginEmail).sendKeys(loginstr);
		driver.findElement(loginPwd).sendKeys(pwdstr);
		driver.findElement(this.loginSubmit).click();

	}

	public void closeLogin() {

		driver.findElement(loginClose).click();

	}

	public boolean checkIfErrorExists() {

		boolean present = false;
		try {

			WebElement errorelement = driver.findElement(errorMessage);
			String error = errorelement.getText();
			if (error.equals("Error : Invalid login, try again !!")) {
				System.out.println("error message is Visible");
				present = true;
			} else {
				System.out.println("error message is InVisible");
				present = false;
			}

		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			present = false;
		}
		// System.out.println("login email element exsts "+ present);
		return present;

	}

}
