package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Elements & locators

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final By LOG_IN_ACCOUNT_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By PERSONAL_ACCOUNT_BUTTON = By.xpath(".//p[text()='Личный Кабинет']");
    private static final By CHECKING_MAIN_PAGE = By.xpath(".//h1[text()='Соберите бургер']");


    // actions
    @Step("Открытие главной страницы")
    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    @Step("Нажатие кнопки личный кабинет")
    public MainPage clickPersonalAccountButton() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        return this;
    }

    @Step("Нажатие кнопки Войти в аккаунт")
    public MainPage clickingLogInAccountButton() {
        driver.findElement(LOG_IN_ACCOUNT_BUTTON).click();
        return this;
    }

    @Step("Проверка, что находимся на главной странице")
    public MainPage checkingThatOnMainPage() {
        Assert.assertTrue("Пользователь находится не на главной странице",
                driver.findElement(CHECKING_MAIN_PAGE).isDisplayed());
        return this;
    }
}
