package site.nomoreparties.stellarburgers;

import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
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
    private MainPage mainPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @Description("Успешная регистрация")
    public void successfulRegistrationTest() {
        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterButton();
        loginPage.fillingFieldsRegistrationClickingRegisterButton(Constants.getName(), Constants.getEmail(),
                Constants.getPassword());
        loginPage.logInButtonIsDisplayed();
        userClient = new UserClient();
        userLogin = new User(Constants.getEmail(), Constants.getPassword());
    }

    @Test
    @Description("Ошибка регистрации Некорректный пароль")
    public void registrationErrorIncorrectPasswordTest() {
        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterButton();
        loginPage.fillingFieldsRegistrationClickingRegisterButton(Constants.getName(), Constants.getEmail(),
                Constants.getIncorrectPassword());
        loginPage.incorrectPassword();
    }

    @After
    @Description("Удаление пользователя")
    public void deleteUser() {
        if (userLogin != null) {
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
