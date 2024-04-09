package dev.naamad.client;

import dev.naamad.auth.AuthenticationManager;
import dev.naamad.utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GitHubCollaboratorClient extends GitHubClient implements ICollaboratorClient{

    public GitHubCollaboratorClient(AuthenticationManager authenticationManager, String token) {
        super(authenticationManager, token);
    }

    @Override
    public Response listCollaborators(String owner, String repoName) {
        String bearerToken = getBearerToken();
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .when()
                .get(Constants.API_BASE_URL + Constants.API_REPO_COLLABORATORS, owner, repoName);
    }

    @Override
    public Response addCollaborator(String owner, String repoName, String collaboratorUsername) {
        String bearerToken = getBearerToken();
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .body("{ \"permission\": \"push\" }")
                .when()
                .put(Constants.API_BASE_URL + Constants.API_REPO_COLLABORATOR, owner, repoName, collaboratorUsername);
    }

    @Override
    public Response removeCollaborator(String owner, String repoName, String collaboratorUsername) {
        String bearerToken = getBearerToken();
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .when()
                .delete(Constants.API_REPO_COLLABORATOR, Constants.API_BASE_URL, owner, repoName, collaboratorUsername);
    }

}
