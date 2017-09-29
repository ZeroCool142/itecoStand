package iteco.stand.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class LoginPage extends Page{

    /*
    * login:   sua_all
    * password:    Q1w2e3r4
    * */

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//input[@name='j_username']")
    private WebElement userField;

    @FindBy(how = How.XPATH, using = "//input[@name='j_password']")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Войти')]")
    private WebElement enterButton;

    public void login(String username, String password){
        userField.clear();
        passwordField.clear();

        userField.sendKeys(username);
        passwordField.sendKeys(password);

        enterButton.click();
    }




}
