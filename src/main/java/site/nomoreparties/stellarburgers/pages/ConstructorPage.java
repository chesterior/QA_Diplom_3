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

    public By checkBreadSectionOpen = By.xpath(".//h2[text()='Булки']");
    public By checkSaucesSectionOpen = By.xpath(".//h2[text()='Соусы']");
    public By checkFillingsSectionOpen = By.xpath(".//h2[text()='Начинки']");


    @Step("Нажатие кнопки Соусы и проверка открытия соответсвующего раздела")
    public ConstructorPage clickSaucesSectionButton() {
        driver.findElement(SAUCES_SECTION_BUTTON).click();
        return this;
    }

    @Step("Нажатие кнопки Начинки и проверка открытия соответсвующего раздела")
    public ConstructorPage clickFillingsSectionButton() {
        driver.findElement(FILLINGS_SECTION_BUTTON).click();
        return this;
    }

    @Step("Нажатие кнопки Булка и проверка открытия соответсвующего раздела")
    public ConstructorPage clickBreadSectionButton() {
        driver.findElement(SAUCES_SECTION_BUTTON).click();
        driver.findElement(BREAD_SECTION_BUTTON).click();
        return this;
    }
}
