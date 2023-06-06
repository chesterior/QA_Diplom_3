package site.nomoreparties.stellarburgers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import site.nomoreparties.stellarburgers.api.User;
import site.nomoreparties.stellarburgers.api.UserClient;
import site.nomoreparties.stellarburgers.api.UserGenerator;
import site.nomoreparties.stellarburgers.pages.LoginPage;
import site.nomoreparties.stellarburgers.pages.MainPage;

public class LogInAccountTest extends TestBase {

    private UserClient userClient;
    private User user;
    private String accessToken;

    @Test
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    public void logInLoginAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        try {
            userClient = new UserClient();
            user = UserGenerator.getRandom();
            userClient.create(user);

            mainPage.open();
            mainPage.clickingLogInAccountButton();
            loginPage.fillingFieldsLoginWindowClickingLogInButton(user.getEmail(), user.getPassword());
            mainPage.checkingThatOnMainPage();

        } finally {
            User userLogin = new User(user.getEmail(), user.getPassword());
            Response loginResponse = userClient.login(userLogin);
            String responseBody = loginResponse.getBody().asString();
            JsonPath jsonPath = JsonPath.from(responseBody);
            accessToken = jsonPath.getString("accessToken");

            Response deleteResponse = userClient.delete(accessToken);
            deleteResponse
                    .then()
                    .statusCode(202);
        }
    }

    @Test
    @Description("Вход через кнопку «Личный кабинет»")
    public void logInViaPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        try {
            userClient = new UserClient();
            user = UserGenerator.getRandom();
            userClient.create(user);

            mainPage.open();
            mainPage.clickPersonalAccountButton();
            loginPage.fillingFieldsLoginWindowClickingLogInButton(user.getEmail(), user.getPassword());
            mainPage.checkingThatOnMainPage();

        } finally {
            User userLogin = new User(user.getEmail(), user.getPassword());
            Response loginResponse = userClient.login(userLogin);
            String responseBody = loginResponse.getBody().asString();
            JsonPath jsonPath = JsonPath.from(responseBody);
            accessToken = jsonPath.getString("accessToken");

            Response deleteResponse = userClient.delete(accessToken);
            deleteResponse
                    .then()
                    .statusCode(202);
        }
    }

    @Test
    @Description("Вход через кнопку в форме регистрации")
    public void logInViaButtonInRegistrationFormTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        try {
            userClient = new UserClient();
            user = UserGenerator.getRandom();
            userClient.create(user);

            mainPage.open();
            mainPage.clickPersonalAccountButton();
            loginPage.clickRegisterButton();
            loginPage.clickLogInButtonUnderRegistrationForm();
            loginPage.fillingFieldsLoginWindowClickingLogInButton(user.getEmail(), user.getPassword());
            mainPage.checkingThatOnMainPage();

        } finally {
            User userLogin = new User(user.getEmail(), user.getPassword());
            Response loginResponse = userClient.login(userLogin);
            String responseBody = loginResponse.getBody().asString();
            JsonPath jsonPath = JsonPath.from(responseBody);
            accessToken = jsonPath.getString("accessToken");

            Response deleteResponse = userClient.delete(accessToken);
            deleteResponse
                    .then()
                    .statusCode(202);
        }
    }

    @Test
    @Description("Вход через кнопку в форме восстановления пароля")
    public void logInViaButtonInPasswordRecoveryFormTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        try {
            userClient = new UserClient();
            user = UserGenerator.getRandom();
            userClient.create(user);

            mainPage.open();
            mainPage.clickPersonalAccountButton();
            loginPage.clickRestorePasswordButton();
            loginPage.clickLogInButtonUnderRegistrationForm();
            loginPage.fillingFieldsLoginWindowClickingLogInButton(user.getEmail(), user.getPassword());
            mainPage.checkingThatOnMainPage();

        } finally {
            User userLogin = new User(user.getEmail(), user.getPassword());
            Response loginResponse = userClient.login(userLogin);
            String responseBody = loginResponse.getBody().asString();
            JsonPath jsonPath = JsonPath.from(responseBody);
            accessToken = jsonPath.getString("accessToken");

            Response deleteResponse = userClient.delete(accessToken);
            deleteResponse
                    .then()
                    .statusCode(202);
        }
    }
}
