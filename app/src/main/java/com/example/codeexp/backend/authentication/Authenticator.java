package com.example.codeexp.backend.authentication;

public interface Authenticator {
    public void login(String email, String password);
    public void signUp(String email, String password);

    public void logout();

    public Boolean isAlreadyLoggedIn();
}
