package dev.naamad.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

public class GitHubRepositoryClientTests extends TestSetup{

    @Test
    public void testCreateRepository() {
        String repoName = "test-repo" + UUID.randomUUID().toString().substring(0, 8);
        Response response = gitHubRepositoryClient.createRepository(repoName);
        assertEquals(response.statusCode(), 201);
        // Assert the response body contains the created repository details
        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.get("name"), repoName);
        assertNotNull(jsonPath.get("id"));
        assertNotNull(jsonPath.get("owner.login"));
    }

    @Test
    public void testListRepositories() {
        Response response = gitHubRepositoryClient.listRepositories();
        assertEquals(response.statusCode(), 200);
        assertNotNull(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
        List<String> repoNames = jsonPath.getList("name");
        assertTrue(repoNames.size() > 0, "No repositories found in the response");
    }

    @Test
    public void testGetRepositoryDetails() {
        String repoName = "test-repo" + UUID.randomUUID().toString().substring(0, 8);
        gitHubRepositoryClient.createRepository(repoName);

        Response response = gitHubRepositoryClient.getRepositoryDetails(owner, repoName);
        assertEquals(response.statusCode(), 200);
        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.get("name"), repoName);
    }

    @Test
    public void testDeleteRepository() {
        // Create a repository first to ensure we have something to delete
        String repoName = "test-repo" + UUID.randomUUID().toString().substring(0, 8);
        gitHubRepositoryClient.createRepository(repoName);

        Response response = gitHubRepositoryClient.deleteRepository(owner, repoName);
        assertEquals(response.statusCode(), 204);

        // Ensure the repository is deleted
        Response getResponse = gitHubRepositoryClient.getRepositoryDetails(owner, repoName);
        assertEquals(getResponse.statusCode(), 404, "Repository still exists after deletion");
    }
}
