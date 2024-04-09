package dev.naamad.client;

import dev.naamad.auth.AuthenticationManager;
import dev.naamad.utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GitHubCommentClient extends GitHubClient implements ICommentClient {

    public GitHubCommentClient(AuthenticationManager authenticationManager, String token) {
        super(authenticationManager, token);
    }

    @Override
    public Response createComment(String owner, String repoName, int issueNumber, String commentBody) {
        String bearerToken = getBearerToken();
        String jsonBody = "{ \"body\": \"" + commentBody + "\" }";
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .body(jsonBody)
                .pathParam("owner", owner)
                .pathParam("repo", repoName)
                .pathParam("issueNumber", issueNumber)
                .when()
                .post(Constants.API_BASE_URL + Constants.API_REPO_ISSUE_COMMENTS);
    }

    @Override
    public Response listComments(String owner, String repoName, int issueNumber) {
        String bearerToken = getBearerToken();
        return given()
                .contentType(ContentType.JSON)
                .header(Constants.HEADER_AUTHORIZATION, Constants.AUTHORIZATION_TOKEN_PREFIX + bearerToken)
                .when()
                .get(Constants.API_BASE_URL + Constants.API_REPO_ISSUE_COMMENTS, owner, repoName, issueNumber);
    }
}
