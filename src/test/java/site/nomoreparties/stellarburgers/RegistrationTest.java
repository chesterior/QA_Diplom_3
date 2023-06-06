package site.nomoreparties.stellarburgers;

import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.Test;
import site.nomoreparties.stellarburgers.api.User;
import site.nomoreparties.stellarburgers.api.UserClient;
import site.nomoreparties.stellarburgers.pages.Constants;
import site.nomoreparties.stellarburgers.pages.LoginPage;
import site.nomoreparties.stellarburgers.pages.MainPage;
import io.restassured.path.json.JsonPath;

public class RegistrationTest extends TestBase {

    private UserClient userClient;
    private User userLogin;
    private String accessToken;

    @Test
    @Description("Успешная регистрация")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        try {
            mainPage.open();
            mainPage.clickPersonalAccountButton();
            loginPage.clickRegisterButton();
            loginPage.fillingFieldsRegistrationClickingRegisterButton(Constants.getName(), Constants.getEmail(),
                    Constants.getPassword());
            loginPage.logInButtonIsDisplayed();

        } finally {
            userClient = new UserClient();
            userLogin = new User(Constants.getEmail(), Constants.getPassword());
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
    @Description("Ошибка регистрации Некорректный пароль")
    public void registrationErrorIncorrectPasswordTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterButton();
        loginPage.fillingFieldsRegistrationClickingRegisterButton(Constants.getName(), Constants.getEmail(),
                Constants.getIncorrectPassword());
        loginPage.incorrectPassword();
    }
}
