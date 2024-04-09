package dev.naamad.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GitHubCommentsTests extends TestSetup{

    @Test
    public void testCreateComment() {
        String repoName = "test-repo" + UUID.randomUUID().toString().substring(0, 8);
        String issueTitle = "Test Issue";
        String issueBody = "This is a test issue.";
        String commentBody = "This is a test comment.";

        gitHubRepositoryClient.createRepository(repoName);

        // Create an issue
        Response createIssueResponse = gitHubIssueClient.createIssue(owner, repoName, issueTitle, issueBody);
        assertEquals(createIssueResponse.statusCode(), 201);

        JsonPath jsonPath = createIssueResponse.jsonPath();
        int issueNumber = jsonPath.get("number");

        // Create a comment on the issue
        Response createCommentResponse = gitHubCommentClient.createComment(owner, repoName, issueNumber, commentBody);
        assertEquals(createCommentResponse.statusCode(), 201);

        // List comments for the issue
        Response listCommentsResponse = gitHubCommentClient.listComments(owner, repoName, issueNumber);
        assertEquals(listCommentsResponse.statusCode(), 200);

        // Assert that the created comment is listed
        JsonPath commentsJsonPath = listCommentsResponse.jsonPath();
        List<String> commentBodies = commentsJsonPath.getList("body");
        assertTrue(commentBodies.contains(commentBody));
    }
}
