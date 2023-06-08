package site.nomoreparties.stellarburgers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.api.User;
import site.nomoreparties.stellarburgers.api.UserClient;
import site.nomoreparties.stellarburgers.api.UserGenerator;
import site.nomoreparties.stellarburgers.pages.LoginPage;
import site.nomoreparties.stellarburgers.pages.MainPage;
import site.nomoreparties.stellarburgers.pages.PersonalAccountPage;

public class LogInAccountTest extends TestBase {

    private UserClient userClient;
    private User user;
    private String accessToken;
    private MainPage mainPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    public void logInLoginAccountButtonTest() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        userClient.create(user);

        mainPage.open();
        mainPage.clickingLogInAccountButton();
        loginPage.fillingFieldsLoginWindowClickingLogInButton(user.getEmail(), user.getPassword());
        mainPage.checkingThatOnMainPage();
    }

    @Test
    @Description("Вход через кнопку «Личный кабинет»")
    public void logInViaPersonalAccountButtonTest() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        userClient.create(user);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.fillingFieldsLoginWindowClickingLogInButton(user.getEmail(), user.getPassword());
        mainPage.checkingThatOnMainPage();
    }

    @Test
    @Description("Вход через кнопку в форме регистрации")
    public void logInViaButtonInRegistrationFormTest() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        userClient.create(user);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterButton();
        loginPage.clickLogInButtonUnderRegistrationForm();
        loginPage.fillingFieldsLoginWindowClickingLogInButton(user.getEmail(), user.getPassword());
        mainPage.checkingThatOnMainPage();
    }

    @Test
    @Description("Вход через кнопку в форме восстановления пароля")
    public void logInViaButtonInPasswordRecoveryFormTest() {
        userClient = new UserClient();
        user = UserGenerator.getRandom();
        userClient.create(user);

        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.clickRestorePasswordButton();
        loginPage.clickLogInButtonUnderRegistrationForm();
        loginPage.fillingFieldsLoginWindowClickingLogInButton(user.getEmail(), user.getPassword());
        mainPage.checkingThatOnMainPage();
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
