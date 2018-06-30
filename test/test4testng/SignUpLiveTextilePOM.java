package test4testng;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpLiveTextilePOM {

By firstname = By.id("firstname");
By lastname = By.id("lastname");
By password = By.id("password");
By confirmpassword = By.id("confirmpassword");
By email = By.id("email");
By mobile = By.id("mobile");
By organisationname = By.id("organisationname");
By companyaddress = By.id("companyaddress");
By category=By.className("option");
By buyer=By.id("buyer");
By supplier=By.id("supplier");
By both=By.id("both");
By signup_submit=By.id("signup_submit");


WebDriver driver;
 
// Constructor to set the local driver object in this class
    public SignUpLiveTextilePOM(WebDriver driver){

        this.driver = driver;

    }
    
    //Get the title of Login Page

    public String getTitle(){

     return    driver.getTitle();

    }
    
    public void signUp(String firstnamex,String lastnamex,String passwordx,
    		String confirmpasswordx,String emailx,String mobilex,
    		String organisationnamex,String companyaddressx ,String categoryx) throws InterruptedException{
 
    driver.findElement(firstname).click();
    driver.findElement(firstname).sendKeys(firstnamex);
    driver.findElement(lastname).click();
    driver.findElement(lastname).sendKeys(lastnamex);
    driver.findElement(password).click();
    driver.findElement(password).sendKeys(passwordx);
    driver.findElement(confirmpassword).click();
    driver.findElement(confirmpassword).sendKeys(confirmpasswordx);
    driver.findElement(email).click();
    driver.findElement(email).sendKeys(emailx);
    driver.findElement(mobile).click();
    driver.findElement(mobile).sendKeys(mobilex);
    driver.findElement(organisationname).click();
    driver.findElement(organisationname).sendKeys(organisationnamex);
    driver.findElement(companyaddress).click();
    driver.findElement(companyaddress).sendKeys(companyaddressx);
    
    Thread.sleep(2000);
    List<WebElement> categoryselect=driver.findElements(category);
    
    
    for (WebElement webElement : categoryselect) {
		if (webElement.getText().equalsIgnoreCase(categoryx)) {
			webElement.click();
		}
	}
    
    driver.findElement(signup_submit).click();
    }
    
    public void closeLogin(){

         driver.findElement(By.id("signup_close")).click();

    }
    
    public boolean checkIfErrorExists(){

    boolean present = false;
        try {
       
        WebElement errorelement = driver.findElement(By.id("error_signup"));
        String error = errorelement.getText();
        if( error.equals("The fields highlighted in red are mandatory.")){
        System.out.println("error message is Visible");
        present = true;
        }
        else{
        System.out.println("error message is InVisible");
        present = false;
        }
 
        } 
        catch (NoSuchElementException e) {
           System.out.println(e.getMessage());
           present = false;
        }
        //System.out.println("login email element exsts "+ present);
        return present;

   } 
    
    
}
