package dev.naamad.client;

import dev.naamad.auth.AuthenticationManager;
import dev.naamad.utils.Constants;

public class GitHubClient {

    private final AuthenticationManager authenticationManager;
    private final String token;


    public GitHubClient(AuthenticationManager authenticationManager, String token) {
        this.authenticationManager = authenticationManager;
        this.token = token;
    }

    protected String getBearerToken() {
        return !token.isEmpty() ? token : authenticationManager.getBearerToken(Constants.CLIENT_ID, Constants.CLIENT_SECRET, Constants.CODE);
    }
}
