package dev.naamad.client;

import io.restassured.response.Response;

public interface IMilestoneClient{
    Response createMilestone(String owner, String repoName, String title, String description);
    Response listMilestones(String owner, String repoName);
}
