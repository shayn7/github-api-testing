package dev.naamad.auth;

import dev.naamad.utils.Constants;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationManager {

    public String getBearerToken(String clientId, String clientSecret, String code) {
        String url = Constants.GITHUB_AUTH_URL;
        Response response = given()
                .contentType(Constants.CONTENT_TYPE_JSON)
                .formParam(Constants.CLIENT_ID, clientId)
                .formParam(Constants.CLIENT_SECRET, clientSecret)
                .formParam(Constants.CODE, code)
                .post(url);

        if (response.getStatusCode() == 200) {
            return response.jsonPath().getString("access_token");
        } else {
            // Handle authentication error
            System.out.println("Error occurred while retrieving bearer token: " + response.getStatusLine());
            return null;
        }
    }
}
