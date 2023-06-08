package site.nomoreparties.stellarburgers;


import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pages.ConstructorPage;
import site.nomoreparties.stellarburgers.pages.MainPage;

public class ConstructorTest extends TestBase {
    private MainPage mainPage;
    private ConstructorPage constructorPage;

    @Before
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        constructorPage = new ConstructorPage(driver);
    }

    @Test
    @Description("Переход к разделу Соусы")
    public void saucesSectionTest() {
        mainPage.open();
        constructorPage.clickSaucesSectionButton();
        Assert.assertTrue("Отсутствует раздел соусы", constructorPage.checkSaucesSectionIsDisplayed());
    }

    @Test
    @Description("Переход к разделу Булки")
    public void breadSectionTest() {
        mainPage.open();
        constructorPage.clickBreadSectionButton();
        Assert.assertTrue("Отсутствует раздел Булки", constructorPage.checkBreadSectionIsDisplayed());
    }

    @Test
    @Description("Переход к разделу Начинки")
    public void fillingsSectionTest() {
        mainPage.open();
        constructorPage.clickFillingsSectionButton();
        Assert.assertTrue("Отсутствует раздел Начинки", constructorPage.checkFillingsSectionIsDisplayed());
    }
}
