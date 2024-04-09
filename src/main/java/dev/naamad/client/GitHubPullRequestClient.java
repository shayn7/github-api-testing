package dev.naamad.client;

import dev.naamad.auth.AuthenticationManager;
import dev.naamad.utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GitHubPullRequestClient extends GitHubClient implements IPullRequestClient{

    public GitHubPullRequestClient(AuthenticationManager authenticationManager, String token) {
        super(authenticationManager, token);
    }

    @Override
    public Response mergePullRequest(String owner, String repoName, int pullRequestNumber) {
        String bearerToken = getBearerToken();
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .when()
                .put(Constants.API_REPO_PULL_REQUEST_MERGE, Constants.API_BASE_URL, owner, repoName, pullRequestNumber);
    }

    @Override
    public Response listPullRequests(String owner, String repoName) {
        String bearerToken = getBearerToken();
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .when()
                .get(Constants.API_REPO_PULL_REQUESTS, Constants.API_BASE_URL, owner, repoName);
    }

    @Override
    public Response createPullRequest(String owner, String repoName, String sourceBranch, String targetBranch, String title) {
        String bearerToken = getBearerToken();
        String jsonBody = "{ \"title\": \"" + title + "\", \"head\": \"" + sourceBranch + "\", \"base\": \"" + targetBranch + "\" }";
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .body(jsonBody)
                .when()
                .post(Constants.API_BASE_URL + Constants.API_REPO_PULL_REQUESTS, owner, repoName);
    }
}
