package dev.naamad.client;

import dev.naamad.auth.AuthenticationManager;
import dev.naamad.utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GitHubMilestoneClient extends GitHubClient implements IMilestoneClient{

    public GitHubMilestoneClient(AuthenticationManager authenticationManager, String token) {
        super(authenticationManager, token);
    }

    @Override
    public Response createMilestone(String owner, String repoName, String title, String description) {
        String bearerToken = getBearerToken();
        String jsonBody = "{ \"title\": \"" + title + "\", \"description\": \"" + description + "\" }";
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .body(jsonBody)
                .when()
                .post(Constants.API_BASE_URL + Constants.API_REPO_MILESTONES, owner, repoName);
    }

    @Override
    public Response listMilestones(String owner, String repoName) {
        String bearerToken = getBearerToken();
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .when()
                .get(Constants.API_REPO_MILESTONES, Constants.API_BASE_URL, owner, repoName);
    }
}
