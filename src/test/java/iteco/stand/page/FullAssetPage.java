package iteco.stand.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FullAssetPage extends Page{

    public FullAssetPage(WebDriver driver){
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//input[@name='accountNum']")
    private WebElement accountNum;

    @FindBy(how = How.XPATH, using = "//select[@name='currency']")
    private WebElement currency;

    @FindBy(how = How.XPATH, using = "//input[@name='balanceInitial']")
    private WebElement balanceInitial;
    @FindBy(how = How.XPATH, using = "//input[@name='balanceInitialCur']")
    private WebElement balanceInitialCur;

    @FindBy(how = How.XPATH, using = "//input[@name='balanceRur']")
    private WebElement balanceRur;
    @FindBy(how = How.XPATH, using = "//input[@name='balanceCur']")
    private WebElement balanceCur;

    @FindBy(how = How.XPATH, using = "//div[@class='ng-scope']//button[contains(., 'Сохранить')]")
    private WebElement saveButton;


    @FindAll({@FindBy(how = How.XPATH, using = "//ul[@id='myTab']/li/a")})
    private List<WebElement> tabList;

    public void accountNumInput(String num){
        new Actions(driver).moveToElement(accountNum).click().build().perform();
        accountNum.sendKeys(num);
    }
    public void accountNumClear(){
        accountNum.clear();
    }

//    public void chooseCurrency(String currencyName){
//        new Select(currency).selectByVisibleText(currencyName);
//    }

    public void setBalanceInitial(String initialBalance){
        balanceInitial.clear();
        balanceInitial.sendKeys(initialBalance);
    }

    public void balanceRurClick(){
        balanceRur.click();
    }

    public String getInitialBalanceValue(){
        balanceInitial.click();
        return balanceInitial.getAttribute("value");
    }

    public String getBalanceRurValue(){
        balanceRur.click();
        return balanceRur.getAttribute("value");
    }

    public void checkIsTabPresens(String tabName){
        tabList.stream()
                .filter(e->e.getText().equals(tabName)).findFirst().get();
    }

    public void checkCurrencyName(String currencyName){
        Select curencySelect = new Select(currency);
        if (!curencySelect.getFirstSelectedOption().getText().equals(currencyName))
            throw new RuntimeException("Wrong currency name!");
    }

    public void setBalanceInitialCur(String initialBalanceValue) {
        balanceInitialCur.clear();
        balanceInitialCur.sendKeys(initialBalanceValue);
    }

    public void setBalanceCur(String initialBalanceValue) {
        balanceCur.clear();
        balanceCur.sendKeys(initialBalanceValue);
    }

    public void chooseTab(String tabName) {
        WebElement tab = tabList.stream()
                .filter(e->e.getText().equals(tabName)).findFirst().get();
        new Actions(driver).moveToElement(tab).click().build().perform();
    }

    public void saveButtonClick() {
        saveButton.click();
    }
}
