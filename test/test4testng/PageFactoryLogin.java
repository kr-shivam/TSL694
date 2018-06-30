package test4testng;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFactoryLogin {

// #login_email
// By loginEmail = By.cssSelector("#login_email");
@FindBy(css= "#login_email") 
WebElement loginEmail;
 
// #login_password
// By loginPwd = By.cssSelector("#login_password");
@FindBy(css= "#login_password") 
WebElement loginPwd;
 
// #login_submit
// By loginSubmit = By.cssSelector("#login_submit");
@FindBy(css= "#login_submit") 
WebElement loginSubmit;

// #login_close > i
// By loginClose = By.cssSelector("#login_close > i");
@FindBy(css= "#login_close > i") 
WebElement loginClose;
 
// #error
// By errorMessage = By.cssSelector("#error");
@FindBy(css= "#error") 
WebElement errorMessage;
 
// #login_display > div > div
// By loginDisplay = By.cssSelector("#login_display > div > div");
@FindBy(css= "#login_display > div > div") 
WebElement loginDisplay;
 
WebDriver driver;

// Constructor to set the local driver object in this class
// and also to intialize the WebElements
    public PageFactoryLogin(WebDriver driver){
 
    PageFactory.initElements(driver, this);
    this.driver = driver;

    }
    
    //Get the title of Login Page

    public String getTitle(){

     return    driver.getTitle();

    }
    
    public void login(String loginstr,String pwdstr){
 
    loginEmail.clear();
    loginPwd.clear();
    loginEmail.click();
    loginEmail.sendKeys(loginstr);
        loginPwd.sendKeys(pwdstr);
        loginSubmit.click();

    }
    
    public void closeLogin(){

         loginClose.click();

    }
    
    public boolean checkIfErrorExists(){

    boolean present = false;
        try {
       
        String error = errorMessage.getText();
        if( error.equals("Error : Invalid login, try again !!")){
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
