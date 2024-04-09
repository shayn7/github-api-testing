package dev.naamad.client;

import io.restassured.response.Response;

public interface IPullRequestClient {
    Response createPullRequest(String owner, String repoName, String sourceBranch, String targetBranch, String title);
    Response listPullRequests(String owner, String repoName);
    Response mergePullRequest(String owner, String repoName, int pullRequestNumber);

}
