package dev.naamad.client;

import io.restassured.response.Response;

public interface IIssueClient {
    Response createIssue(String owner, String repoName, String title, String body);
    Response listIssues(String owner, String repoName);
    Response getIssue(String owner, String repoName, int issueNumber);
    Response addLabelToIssue(String owner, String repoName, int issueNumber, String label);


}
