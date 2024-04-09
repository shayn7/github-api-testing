package dev.naamad.utils;

import org.yaml.snakeyaml.Yaml;

public class Constants {

    // HTTP request methods
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    public static final String HTTP_PUT = "PUT";
    public static final String HTTP_PATCH = "PATCH";
    public static final String HTTP_DELETE = "DELETE";

    // HTTP headers
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String GITHUB_AUTH_URL = "https://github.com/login/oauth/access_token";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String CODE = "code";

    // Content types
    public static final String CONTENT_TYPE_JSON = "application/json";

    // API endpoints
    public static final String API_BASE_URL = "https://api.github.com";
    public static final String API_USER_REPOS = "/user/repos";
    public static final String API_REPO_ISSUES = "/repos/{owner}/{repo}/issues";
    public static final String API_REPO_LABELS = "/repos/{owner}/{repo}/labels";
    public static final String API_REPO_MILESTONES = "/repos/{owner}/{repo}/milestones";

    public static final String API_REPO = "/repos/{owner}/{repo}";
    public static final String API_REPO_COLLABORATORS = "/repos/{owner}/{repo}/collaborators";
    public static final String API_REPO_COMMENTS = "/repos/{owner}/{repo}/issues/{issueNumber}/comments";
    public static final String API_REPO_PULLS = "/repos/{owner}/{repo}/pulls";
    public static final String API_REPO_COMMITS = "/repos/{owner}/{repo}/commits";
    public static final String API_REPO_BRANCHES = "/repos/{owner}/{repo}/git/refs";
    public static final String API_REPO_RELEASES = "/repos/{owner}/{repo}/releases";
    public static final String API_REPO_CONTENTS = "/repos/{owner}/{repo}/contents/{path}";
    public static final String API_REPO_COMPARE = "/repos/{owner}/{repo}/compare/{base}...{head}";
    public static final String API_REPO_ISSUE = "/repos/{owner}/{repo}/issues";
    public static final String API_REPO_ISSUE_LABELS = "/repos/{owner}/{repo}/issues/{issueNumber}/labels";
    public static final String API_REPO_COLLABORATOR = "/repos/{owner}/{repo}/collaborators/{collaboratorUsername}";
    public static final String API_REPO_ISSUE_COMMENTS = "/repos/{owner}/{repo}/issues/{issueNumber}/comments";
    public static final String API_REPO_PULL_REQUEST_MERGE = "/repos/{owner}/{repo}/pulls/{pullRequestNumber}/merge";
    public static final String API_REPO_PULL_REQUESTS = "/repos/{owner}/{repo}/pulls";

    public static final String AUTHORIZATION_TOKEN_PREFIX = "token ";

    // Constructor (private to prevent instantiation)
    private Constants() {
    }
}


