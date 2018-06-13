package test4testng;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.Test;
 
public class Assignment08Jun4 {
 
public WebDriver driver;
 
  @Test
   public void First() {
       System.out.println("This is the Test Case number One");
   }
   @Test(enabled = false)
   public void Second() {
    System.out.println("This is the Test Case number Two");
   }
   @Test
   public void Third() {
    System.out.println("This is the Test Case number Three");
   }
   @Test
   public void Fourth() {
    System.out.println("This is the Test Case number Four");
   }
 }
