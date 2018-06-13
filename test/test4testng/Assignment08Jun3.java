package test4testng;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.Test;
 
public class Assignment08Jun3 {
 
public WebDriver driver;
 
  @Test(priority=0)
   public void First() {
       System.out.println("This is the Test Case number One");
   }
   @Test(priority=1)
   public void Second() {
    System.out.println("This is the Test Case number Two");
   }
   @Test(priority=2)
   public void Third() {
    System.out.println("This is the Test Case number Three");
   }
   @Test(priority=3)
   public void Fourth() {
    System.out.println("This is the Test Case number Four");
   }
 }
