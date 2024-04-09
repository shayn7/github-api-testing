package dev.naamad.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.UUID;

import static org.testng.Assert.*;

public class GitHubMilestoneClientTests extends TestSetup {

    @Test
    public void testCreateMilestone() {
        String repoName = "test-repo" + UUID.randomUUID().toString().substring(0, 8);
        String milestoneTitle = "Test Milestone";
        String milestoneDescription = "This is a test milestone.";

        gitHubRepositoryClient.createRepository(repoName);

        Response createMilestoneResponse = gitHubMilestoneClient.createMilestone(owner, repoName, milestoneTitle, milestoneDescription);
        assertEquals(createMilestoneResponse.statusCode(), 201);

        // Assert the response body contains the created milestone details
        JsonPath jsonPath = createMilestoneResponse.jsonPath();
        assertEquals(jsonPath.get("title"), milestoneTitle);
        assertEquals(jsonPath.get("description"), milestoneDescription);
        assertNotNull(jsonPath.get("number"));
    }




}



