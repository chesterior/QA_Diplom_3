package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By REGISTER_BUTTON = By.xpath(".//a[text()='Зарегистрироваться']");
    private static final By FIELD_NAME_OR_EMAIL = By.cssSelector(".input__textfield");
    private static final By FIELD_PASSWORD = By.xpath(".//input[@name='Пароль']");
    private static final By REGISTRATION_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");
    private static final By RESTORE_PASSWORD_BUTTON = By.xpath(".//a[text()='Восстановить пароль']");
    private static final By LOG_IN_BUTTON = By.xpath(".//button[text()='Войти']");
    private static final By LOG_IN_BUTTON_IN_REGISTRATION_FORM = By.xpath(".//a[text()='Войти']");
    private static final By INCORRECT_PASSWORD =
            By.xpath(".//div[@class='input__container']//p[text()='Некорректный пароль']");


    @Step("Нажатие кнопки зарегистрироваться")
    public LoginPage clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
        return this;
    }

    @Step("Нажатие кнопки войти под формой регистрации")
    public LoginPage clickLogInButtonUnderRegistrationForm() {
        driver.findElement(LOG_IN_BUTTON_IN_REGISTRATION_FORM).click();
        return this;
    }

    @Step("Нажатие кнопки восстановить пароль")
    public LoginPage clickRestorePasswordButton() {
        driver.findElement(RESTORE_PASSWORD_BUTTON).click();
        return this;
    }

    @Step("Заполнение полей окна регистрации и нажатие кнопки Зарегистрироваться")
    public LoginPage fillingFieldsRegistrationClickingRegisterButton(String name, String email, String password) {
        driver.findElements(FIELD_NAME_OR_EMAIL).get(0).sendKeys(name);
        driver.findElements(FIELD_NAME_OR_EMAIL).get(1).sendKeys(email);
        driver.findElement(FIELD_PASSWORD).sendKeys(password);
        driver.findElement(REGISTRATION_BUTTON).click();
        return this;
    }

    @Step("Проверка наличия фразы Некорректный пароль")
    public LoginPage incorrectPassword() {
        Assert.assertTrue("На странице отсутствует фраза Некорректный пароль",
                driver.findElement(INCORRECT_PASSWORD).isDisplayed());
        return this;
    }

    @Step("Проверка наличия кнопки Войти")
    public LoginPage logInButtonIsDisplayed() {
        Assert.assertTrue("На странице отсутствует кнопка Войти",
                driver.findElement(LOG_IN_BUTTON).isDisplayed());
        return this;
    }

    @Step("Заполнение полей окна Входа и нажатие кнопки Войти")
    public LoginPage fillingFieldsLoginWindowClickingLogInButton(String email, String password) {
        driver.findElements(FIELD_NAME_OR_EMAIL).get(0).sendKeys(email);
        driver.findElement(FIELD_PASSWORD).sendKeys(password);
        driver.findElement(LOG_IN_BUTTON).click();
        return this;
    }
}
