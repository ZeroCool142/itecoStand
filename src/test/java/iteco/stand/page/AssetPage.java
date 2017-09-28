package iteco.stand.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class AssetPage extends Page{

    public AssetPage(WebDriver driver){
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//select[@ng-model='cardData.functionalType.id']")
    private WebElement assetTypeComboBox;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Далее')]")
    private WebElement nextButton;

    @FindBy(how = How.XPATH, using = "//input[@name='orgName']")
    private WebElement orgNameField;



    public void chooseAssetName(String name){
        new Select(assetTypeComboBox).selectByVisibleText(name);
    }

    public void nextButtonClick(){
        nextButton.click();
    }


    public void foNameEqualOrganization(String organizationName) {
        if (!orgNameField.getAttribute("value").equals(organizationName)) throw new RuntimeException("Wrong organization name");
    }

    public void foFieldIsDisabled() {
        if (orgNameField.isEnabled()) throw new RuntimeException("Organization name field is enabled!");
    }
}
