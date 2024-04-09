package dev.naamad.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.testng.Assert.*;

public class GitHubIssueClientTests extends TestSetup{

    @Test
    public void testCreateIssue() {
        String repoName = "test-repo" + UUID.randomUUID().toString().substring(0, 8);
        String issueTitle = "Test Issue";
        String issueBody = "This is a test issue.";

        gitHubRepositoryClient.createRepository(repoName);

        Response response = gitHubIssueClient.createIssue(owner, repoName, issueTitle, issueBody);
        assertEquals(response.statusCode(), 201);

        // Assert the response body contains the created issue details
        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.get("title"), issueTitle);
        assertEquals(jsonPath.get("body"), issueBody);
        assertNotNull(jsonPath.get("number"));
    }

    @Test
    public void testListIssues() {
        String repoName = "test-repo" + UUID.randomUUID().toString().substring(0, 8);

        gitHubRepositoryClient.createRepository(repoName);

        // Create some issues
        gitHubIssueClient.createIssue(owner, repoName, "Issue 1", "This is issue 1");
        gitHubIssueClient.createIssue(owner, repoName, "Issue 2", "This is issue 2");

        Response response = gitHubIssueClient.listIssues(owner, repoName);
        assertEquals(response.statusCode(), 200);

        // Assert that at least 2 issues are listed
        JsonPath jsonPath = response.jsonPath();
        List<String> issueTitles = jsonPath.getList("title");
        assertTrue(issueTitles.size() >= 2, "Less than 2 issues found in the response");
    }

}
