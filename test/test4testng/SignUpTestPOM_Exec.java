package test4testng;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SignUpTestPOM_Exec {
 
WebDriver driver;
@BeforeTest
  public void beforeTest() {
System.setProperty("webdriver.chrome.driver", "test\\resources\\drivers\\chromedriver.exe");
driver = new ChromeDriver();
driver.get("http://205.147.102.59:9001/");
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

  }
 
  @Test
  public void logintest() throws InterruptedException {
  SignUpLiveTextilePOM signUpPage = new SignUpLiveTextilePOM(driver);
  driver.findElement(By.id("show_signup")).click();
  signUpPage.signUp("nehal", "ahuja", "12345ab@6789", "12345ab@6789", "nehalahuja996@gmail.com", "9999999999", "LTI", "Mahape", "Buyer");
  signUpPage.checkIfErrorExists();
  //loginpage.login("narendra.gupta@qaagility.com", "test@123");
  signUpPage.checkIfErrorExists();
  }
 
/*  @Test
  public void logintestPF() {
  LoginLiveTextilePOM loginpageF = new LoginLiveTextilePOM(driver);
  driver.findElement(By.cssSelector("#show_login")).click();
  loginpageF.login("narendra@qaagility.com", "test@123");
  loginpageF.checkIfErrorExists();
  loginpageF.login("narendra.gupta@qaagility.com", "test@123");
  loginpageF.checkIfErrorExists();
  }*/
 
  @AfterTest
  public void afterTest() {
  //driver.quit();
  }

}