package iteco.stand.steps;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import iteco.stand.helper.BrowserFactory;
import iteco.stand.page.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class IntangibleAssetsSteps {
    WebDriver driver;

    LoginPage loginPage;
    MainPage mainPage;
    AssetPage assetPage;
    FullAssetPage fullAssetPage;

    @Дано("^Выполнен вход в систему$")
    public void выполнеВходВСистему(){
        loginPage.login("sua_all","Q1w2e3r4");
        mainPage.chooseOrganizationByName("ддддддддд");
    }

    @Когда("^Пользователь открыл (.*)$")
    public void пользовательОткрыл(String menuItem) throws Throwable {
        mainPage.navigateTo(menuItem);
    }
    @Тогда("^Проверить открылась форма (.*)$")
    public void проверитьОткрыласьФорма(String formName) throws Throwable {
        mainPage.checkOpenedFormName(formName);
    }

    @Тогда("^проверить наличие актива с номером (.*)$")
    public void проверитьНаличиеАктиваСНомеромAccountNum(String accountNum) throws Throwable {
        //mainPage.checkAssetNotInGrid(accountNum);
    }
    @Когда("^Нажал кнопку (.*)$")
    public void нажалКнопку(String button) throws Throwable {
        mainPage.buttonClick(button);
    }
    @Тогда("^Выпадающий список «Наименование ФО» должен быть заполнен наименование текущей организации и недоспупен для редактирования$")
    public void выпадающийСписокНаименованиеФОДолженБытьЗаполненНаименованиеТекущейОрганизацииИНедоспупенДляРедактирования() throws Throwable {
        assetPage.foNameEqualOrganization(mainPage.getFullOraganizationName());
        assetPage.foFieldIsDisabled();
    }
    @Когда("^Выбрал тип актива (.*)")
    public void выбралТипАктива(String assetName) throws Throwable {
        assetPage.chooseAssetName(assetName);
    }
    @И("^На этой странице нажал кнопку Далее$")
    public void наЭтойСтраницеНажалКнопкуДалее() throws Throwable {
        assetPage.nextButtonClick();
    }
    @Тогда("^На Карточке актива должны отображаться закладки$")
    public void наКарточкеАктиваДолжныОтображатьсяЗакладки() throws Throwable {
        fullAssetPage.checkIsTabPresens("Основная информация");
        fullAssetPage.checkIsTabPresens("Приём");
        fullAssetPage.checkIsTabPresens("Судебная работа");
        fullAssetPage.checkIsTabPresens("Хранение");
        fullAssetPage.checkIsTabPresens("Аренда");
        fullAssetPage.checkIsTabPresens("Оценка");
        fullAssetPage.checkIsTabPresens("Реализация");
        fullAssetPage.checkIsTabPresens("Списание");
        fullAssetPage.checkIsTabPresens("Утилизация");
        fullAssetPage.checkIsTabPresens("Связи с активами");
    }
    @Когда("^Ввел в поле номер счета (.*)$")
    public void ввелНомерСчета(String accountNum) throws Throwable {
        fullAssetPage.accountNumClear();
        fullAssetPage.accountNumInput(accountNum);
    }
    @Тогда("^Валюта должна быть (.*)$")
    public void валютаДолжнаБытьCurrency(String currencyName) throws Throwable {
        fullAssetPage.checkCurrencyName(currencyName);
    }


    @И("^Ввел начальную стоимость баланса (.*)$")
    public void ввелНачальнуюСтоимостьБаланса(String balance) throws Throwable {
        fullAssetPage.setBalanceInitial(balance);
    }

    @И("^кликнул на Текущая балансовая стоимость$")
    public void кликнулНаТекущаяБалансоваяСтоимость() throws Throwable {
        fullAssetPage.balanceRurClick();
    }

    @Тогда("^Текущая балансовая стоимость заполнилась автоматически$")
    public void текущаяБалансоваяСтоимостьЗаполниласьАвтоматически() throws Throwable {
        Assert.assertTrue(
                fullAssetPage.getInitialBalanceValue().equals(fullAssetPage.getBalanceRurValue())
        );
    }

    @И("^Заполнил все обязательные поля$")
    public void заполнилВсеОбязательныеПоля() throws Throwable {
        fullAssetPage.setBalanceInitialCur(fullAssetPage.getInitialBalanceValue());
        fullAssetPage.setBalanceCur(fullAssetPage.getInitialBalanceValue());
    }

    @И("^перешел на вкладку (.*)$")
    public void перешелНаВкладкуПрием(String tabName) throws Throwable {
        fullAssetPage.chooseTab(tabName);
    }

    @Когда("^Нажал на кнопку Сохранить$")
    public void нажалНаКнопкуСохранить() throws Throwable {
        fullAssetPage.saveButtonClick();
    }

    @И("^проверить создание актива (.*)$")
    public void проверитьСозданиеАктива(String accountNum) throws Throwable {
        mainPage.checkAssetInGrid(accountNum);
    }

    @Before
    public void setUp(){
        driver = BrowserFactory.getDriver(BrowserFactory.Browser.IE, "http://95.128.177.26:8081/sua");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        assetPage = new AssetPage(driver);
        fullAssetPage = new FullAssetPage(driver);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        assetPage = PageFactory.initElements(driver, AssetPage.class);
        fullAssetPage = PageFactory.initElements(driver, FullAssetPage.class);
    }

    @After
    public void tearDown(){
        driver.quit();
    }


}
