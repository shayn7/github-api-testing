package dev.naamad.client;

import io.restassured.response.Response;

public interface IRepositoryClient {
    Response createRepository(String repoName);
    Response listRepositories();
    Response getRepositoryDetails(String owner, String repoName);
    Response deleteRepository(String owner, String repoName);
}
