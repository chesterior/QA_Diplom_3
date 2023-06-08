package site.nomoreparties.stellarburgers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.api.User;
import site.nomoreparties.stellarburgers.api.UserClient;
import site.nomoreparties.stellarburgers.api.UserGenerator;
import site.nomoreparties.stellarburgers.pages.LoginPage;
import site.nomoreparties.stellarburgers.pages.MainPage;
import site.nomoreparties.stellarburgers.pages.PersonalAccountPage;

public class PersonalAccountTest extends TestBase {

    private UserClient userClient;
    private User user;
    private String accessToken;
    private MainPage mainPage;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;

    @Before
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
    }

    @Test
    @Description("Переход по клику на «Личный кабинет»")
    public void clickPersonalAccountButtonTest() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        userClient.create(user);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.fillingFieldsLoginWindowClickingLogInButton(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccountButton();
        personalAccountPage.checkingProfileButton();
    }

    @Test
    @Description("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void transitionToConstructorByClickingConstructorButtonTest() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        userClient.create(user);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.fillingFieldsLoginWindowClickingLogInButton(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccountButton();
        personalAccountPage.checkingProfileButton();
        personalAccountPage.clickConstructorButton();
        mainPage.checkingThatOnMainPage();
    }

    @Test
    @Description("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void transitionToConstructorByClickingOnStellarBurgersLogoTest() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        userClient.create(user);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.fillingFieldsLoginWindowClickingLogInButton(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccountButton();
        personalAccountPage.checkingProfileButton();
        personalAccountPage.clickOnStellarBurgersLogo();
        mainPage.checkingThatOnMainPage();
    }

    @Test
    @Description("Выход из аккаунта")
    public void logOutOfAccountTest() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        userClient.create(user);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.fillingFieldsLoginWindowClickingLogInButton(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccountButton();
        personalAccountPage.clickExitButton();
        loginPage.logInButtonIsDisplayed();
    }

    @After
    @Description("Удаление пользователя")
    public void deleteUser() {
        if (user != null) {
            User userLogin = new User(user.getEmail(), user.getPassword());
            Response loginResponse = userClient.login(userLogin);
            String responseBody = loginResponse.getBody().asString();
            JsonPath jsonPath = JsonPath.from(responseBody);
            accessToken = jsonPath.getString("accessToken");

            Response deleteResponse = userClient.delete(accessToken);
            deleteResponse
                    .then()
                    .log().all()
                    .statusCode(202);
        }
    }
}
