package dev.naamad.client;

import dev.naamad.auth.AuthenticationManager;
import dev.naamad.utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GitHubIssueClient extends GitHubClient implements IIssueClient{

    public GitHubIssueClient(AuthenticationManager authenticationManager, String token) {
        super(authenticationManager, token);
    }

    @Override
    public Response createIssue(String owner, String repoName, String title, String body) {
        String bearerToken = getBearerToken();
        String jsonBody = "{ \"title\": \"" + title + "\", \"body\": \"" + body + "\" }";
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .body(jsonBody)
                .pathParam("owner", owner)
                .pathParam("repo", repoName)
                .when()
                .post(Constants.API_BASE_URL + Constants.API_REPO_ISSUES);
    }

    @Override
    public Response listIssues(String owner, String repoName) {
        String bearerToken = getBearerToken();
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .when()
                .get(Constants.API_BASE_URL + Constants.API_REPO_ISSUES, owner, repoName);
    }

    @Override
    public Response getIssue(String owner, String repoName, int issueNumber) {
        String bearerToken = getBearerToken();
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .when()
                .get(Constants.API_REPO_ISSUE, Constants.API_BASE_URL, owner, repoName, issueNumber);
    }

    @Override
    public Response addLabelToIssue(String owner, String repoName, int issueNumber, String label) {
        String bearerToken = getBearerToken();
        String jsonBody = "[\"" + label + "\"]";
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .body(jsonBody)
                .when()
                .post(Constants.API_REPO_ISSUE_LABELS, Constants.API_BASE_URL, owner, repoName, issueNumber);
    }
}
