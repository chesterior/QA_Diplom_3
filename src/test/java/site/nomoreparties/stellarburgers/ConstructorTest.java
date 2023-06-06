package site.nomoreparties.stellarburgers;


import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pages.ConstructorPage;
import site.nomoreparties.stellarburgers.pages.MainPage;

public class ConstructorTest extends TestBase {

    @Test
    @Description("Переход к разделу Соусы")
    public void saucesSectionTest() {
        MainPage mainPage = new MainPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        mainPage.open();
        constructorPage.clickSaucesSectionButton();
        Assert.assertTrue("Отсутствует раздел соусы",
                driver.findElement(constructorPage.checkSaucesSectionOpen).isDisplayed()
        );
    }

    @Test
    @Description("Переход к разделу Булки")
    public void breadSectionTest() {
        MainPage mainPage = new MainPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        mainPage.open();
        constructorPage.clickBreadSectionButton();
        Assert.assertTrue("Отсутствует раздел Булки",
                driver.findElement(constructorPage.checkBreadSectionOpen).isDisplayed()
        );
    }

    @Test
    @Description("Переход к разделу Начинки")
    public void fillingsSectionTest() {
        MainPage mainPage = new MainPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        mainPage.open();
        constructorPage.clickFillingsSectionButton();
        Assert.assertTrue("Отсутствует раздел Начинки",
                driver.findElement(constructorPage.checkFillingsSectionOpen).isDisplayed()
        );
    }
}
