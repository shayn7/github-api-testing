package dev.naamad.tests;

import dev.naamad.auth.AuthenticationManager;
import dev.naamad.client.*;
import dev.naamad.utils.ApplicationProperties;
import org.testng.annotations.BeforeSuite;

import java.util.Optional;

public abstract class TestSetup {

    protected GitHubClient gitHubClientRepository;
    protected GitHubMilestoneClient gitHubMilestoneClient;
    protected GitHubRepositoryClient gitHubRepositoryClient;
    protected GitHubCommentClient gitHubCommentClient;
    protected GitHubIssueClient gitHubIssueClient;
    protected GitHubCollaboratorClient gitHubCollaboratorClient;
    protected GitHubPullRequestClient gitHubPullRequestClient;
    protected String token;
    protected String owner;

    @BeforeSuite
    public void setup() {
        token = getProperty("token");
        owner = getProperty("owner");
        gitHubClientRepository = new GitHubClient(new AuthenticationManager(), token);
        gitHubPullRequestClient = new GitHubPullRequestClient(new AuthenticationManager(), token);
        gitHubMilestoneClient = new GitHubMilestoneClient(new AuthenticationManager(), token);
        gitHubRepositoryClient = new GitHubRepositoryClient(new AuthenticationManager(), token);
        gitHubCommentClient = new GitHubCommentClient(new AuthenticationManager(), token);
        gitHubIssueClient = new GitHubIssueClient(new AuthenticationManager(), token);
        gitHubCollaboratorClient = new GitHubCollaboratorClient(new AuthenticationManager(), token);
    }


    private String getProperty(String propertyName) {
        return Optional.ofNullable(System.getProperty(propertyName))
                .orElseGet(() -> Optional.ofNullable(new ApplicationProperties().readProperty(propertyName))
                        .orElse(""));
    }
}
