package dev.naamad.client;

import dev.naamad.auth.AuthenticationManager;
import dev.naamad.utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GitHubRepositoryClient extends GitHubClient implements IRepositoryClient {

    public GitHubRepositoryClient(AuthenticationManager authenticationManager, String token) {
        super(authenticationManager, token);
    }


    @Override
    public Response createRepository(String repoName) {
        String bearerToken = getBearerToken();
        String jsonBody = "{ \"name\": \"" + repoName + "\" }";
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .body(jsonBody)
                .when()
                .post(Constants.API_BASE_URL + Constants.API_USER_REPOS);
    }

    @Override
    public Response listRepositories() {
        String bearerToken = getBearerToken();
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .when()
                .get(Constants.API_BASE_URL + Constants.API_USER_REPOS);
    }

    @Override
    public Response getRepositoryDetails(String owner, String repoName) {
        String bearerToken = getBearerToken();
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .pathParam("owner", owner)
                .pathParam("repo", repoName)
                .when()
                .get(Constants.API_BASE_URL + Constants.API_REPO);
    }

    @Override
    public Response deleteRepository(String owner, String repoName) {
        String bearerToken = getBearerToken();
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .pathParam("owner", owner)
                .pathParam("repo", repoName)
                .when()
                .delete(Constants.API_BASE_URL + Constants.API_REPO);
    }
}

