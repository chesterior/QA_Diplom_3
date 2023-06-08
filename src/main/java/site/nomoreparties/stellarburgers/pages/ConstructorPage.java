package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
    private final WebDriver driver;

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }


    private static final By SAUCES_SECTION_BUTTON = By.xpath(".//span[text()='Соусы']");
    private static final By FILLINGS_SECTION_BUTTON = By.xpath(".//span[text()='Начинки']");
    private static final By BREAD_SECTION_BUTTON = By.xpath(".//span[text()='Булки']");

    private static final By BREAD_SECTION = By.xpath(".//h2[text()='Булки']");
    private static final By SAUCES_SECTION = By.xpath(".//h2[text()='Соусы']");
    private static final By FILLINGS_SECTION = By.xpath(".//h2[text()='Начинки']");


    @Step("Нажатие кнопки Соусы")
    public ConstructorPage clickSaucesSectionButton() {
        driver.findElement(SAUCES_SECTION_BUTTON).click();
        return this;
    }
    @Step("Проверка наличия раздела Соусы")
    public boolean checkSaucesSectionIsDisplayed() {
        return driver.findElement(SAUCES_SECTION).isDisplayed();
    }

    @Step("Нажатие кнопки Начинки")
    public ConstructorPage clickFillingsSectionButton() {
        driver.findElement(FILLINGS_SECTION_BUTTON).click();
        return this;
    }
    @Step("Проверка наличия раздела Начинки")
    public boolean checkFillingsSectionIsDisplayed() {
        return driver.findElement(FILLINGS_SECTION).isDisplayed();
    }

    @Step("Нажатие кнопки Булка")
    public ConstructorPage clickBreadSectionButton() {
        driver.findElement(SAUCES_SECTION_BUTTON).click();
        driver.findElement(BREAD_SECTION_BUTTON).click();
        return this;
    }
    @Step("Проверка наличия раздела Булки")
    public boolean checkBreadSectionIsDisplayed() {
        return driver.findElement(BREAD_SECTION).isDisplayed();
    }
}
