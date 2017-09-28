package iteco.stand.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.security.util.PendingException;

import javax.swing.*;
import java.util.List;

public class MainPage extends Page {


    //TODO check organization and user

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Создать')]")
    private WebElement createButton;

    @FindAll({@FindBy(how = How.XPATH, using = "//td[@aria-describedby='organizationgrid_shortName']")})
    private List<WebElement> organizationCellList;

    @FindAll({@FindBy(how = How.XPATH, using = "//td[@aria-describedby='organizationgrid_inn']")})
    private List<WebElement> innCellList;

    @FindAll({@FindBy(how = How.XPATH, using = "//ol[@class='breadcrumb']/li")})
    private List<WebElement> breadcrumb;

    @FindBy(how = How.XPATH, using = "//span[@ng-bind='showOrgName()']")
    private WebElement organizationName;

    @FindBy(how = How.XPATH, using = "//span[@ng-bind='showOrgName()']/parent::a")
    private WebElement organizationLink;

    @FindBy(how = How.XPATH, using = "//div[@ng-controller='orgModalController']//button[contains(text(), 'Выбрать')]")
    private WebElement chooseOrganizationButton;

    @FindAll({@FindBy(how = How.XPATH, using = "//td[@aria-describedby='assetgrid_accountNum']")})
    private List<WebElement> accountNumCells;

    public void chooseOrganizationByName(String name) {
        variables.put("shortOrgName", name);
        WebElement organizationName = organizationCellList.stream()
                .filter(e -> e.getAttribute("title").equals(name))
                .findFirst().get();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Actions(driver).moveToElement(organizationName)
                .click()
                .moveToElement(chooseOrganizationButton)
                .click()
                .build().perform();
        navigateTo("Финансовые организации");
        String path = String.format("//div[@class='ng-scope']//td[@title='%s']//following-sibling::td", variables.get("shortOrgName"));
        variables.put("fullOrgName", driver.findElement(By.xpath(path)).getAttribute("title"));
    }

    public void chooseOrganizationByInn(String inn) {
        WebElement organizationName = innCellList.stream()
                .filter(e -> e.getAttribute("title").equals(String.valueOf(inn)))
                .findFirst().get();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Actions(driver).moveToElement(organizationName)
                .click()
                .moveToElement(chooseOrganizationButton)
                .click()
                .build().perform();
    }

    public void navigateTo(String menuItem) {
        By pathToItem = By.xpath("//nav/*/div[@id='sua-menu-navbar']//li[contains(., '" + menuItem + "')]");
        List<WebElement> menuItemElement = driver.findElements(pathToItem);
        menuItemElement.get(0).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        menuItemElement.get(1).click();
    }

    public void createButtonClick() {
        createButton.click();
    }

    public void buttonClick(String button) {
        switch (button) {
            case "Создать":
                createButton.click();
                break;
            default:
                throw new IllegalArgumentException("Button name");
        }
    }

    public void checkOpenedFormName(String formName) {
        breadcrumb.stream()
                .filter(e -> e.getText().equals(formName))
                .findFirst().get();
    }

    public String getFullOraganizationName() {
        return variables.get("fullOrgName");
    }

    public void checkAssetInGrid(String accountNum) {
        accountNumCells.stream()
                .filter(e -> e.getText().equals(accountNum))
                .findFirst().get();
    }
    public void checkAssetNotInGrid(String accountNum) {
        WebElement elrm = accountNumCells.stream()
                .filter(e -> e.getText().equals(accountNum))
                .findFirst().get();
        if (elrm.isDisplayed()) throw new RuntimeException("Exist!");
    }
}
