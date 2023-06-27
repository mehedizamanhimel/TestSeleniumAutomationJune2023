package Web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="ID")
    WebElement textfield_UserName;

    @FindBy(css="pass")
    WebElement textfield_Password;

    @FindBy(xpath="buttonLogin")
    WebElement button_Login;

    @FindBy(name="buttonLogin")
    WebElement text_PostLogin;

    public void provide_Username(String username){
        textfield_UserName.sendKeys(username);
    }

    public void provide_Password(String Password){

        textfield_Password.sendKeys(Password);
    }

    public void clickTheLoginButton(){

        button_Login.click();
    }

    public String return_LoginConsent(){
        return text_PostLogin.getText();
    }
    

}
