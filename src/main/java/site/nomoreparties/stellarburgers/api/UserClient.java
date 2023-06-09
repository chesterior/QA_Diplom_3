package site.nomoreparties.stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {
    private static final String USER_LOGIN_PATH = "/api/auth/login";
    private static final String USER_PATH = "/api/auth/user";
    private static final String USER_CREATE_PATH = "/api/auth/register";

    @Step("Удаление пользователя")
    public Response delete(String tokenUser) {
        return given()
                .header("Authorization", tokenUser)
                .spec(getBaseSpec())
                .log().all()
                .when()
                .delete(USER_PATH);
    }

    @Step("Авторизация пользователя")
    public Response login(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .log().all()
                .when()
                .post(USER_LOGIN_PATH);
    }

    public Response create(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .log().all()
                .when()
                .post(USER_CREATE_PATH);
    }
}
