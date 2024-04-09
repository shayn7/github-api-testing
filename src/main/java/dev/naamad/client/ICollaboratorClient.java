package dev.naamad.client;

import io.restassured.response.Response;

public interface ICollaboratorClient{
    Response listCollaborators(String owner, String repoName);
    Response addCollaborator(String owner, String repoName, String collaboratorUsername);
    Response removeCollaborator(String owner, String repoName, String collaboratorUsername);
}
