package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private final WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By EXIT_BUTTON = By.xpath(".//li/button[text()='Выход']");
    private static final By PROFILE_BUTTON = By.xpath(".//a[text()='Профиль']");
    private static final By CONSTRUCTOR_BUTTON = By.xpath(".//p[text()='Конструктор']");
    private static final By STELLAR_BURGERS_LOGO =
            By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");


    @Step("Проверка наличия кнопки Профиль")
    public PersonalAccountPage checkingProfileButton() {
        Assert.assertTrue("На странице отсутствует кнопка Профиль",
                driver.findElement(PROFILE_BUTTON).isDisplayed());
        return this;
    }

    @Step("Нажатие кнопки выход")
    public PersonalAccountPage clickExitButton() {
        driver.findElement(EXIT_BUTTON).click();
        return this;
    }

    @Step("Нажатие кнопки Конструктор")
    public PersonalAccountPage clickConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
        return this;
    }

    @Step("Нажатие на логотип Stellar Burgers")
    public PersonalAccountPage clickOnStellarBurgersLogo() {
        driver.findElement(STELLAR_BURGERS_LOGO).click();
        return this;
    }
}
