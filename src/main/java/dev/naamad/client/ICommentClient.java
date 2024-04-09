package dev.naamad.client;

import io.restassured.response.Response;

public interface ICommentClient {
    Response createComment(String owner, String repoName, int issueNumber, String commentBody);
    Response listComments(String owner, String repoName, int issueNumber);
}
