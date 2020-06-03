package com.example.codeexp.backend.Authentication;

/**
 * Presenter views and logic managers post-authentication should implement this interface.
 */
public interface AuthDelegate {
    public void signUpDidSucceed();
    public void signUpDidFail();

    public void loginDidSucceed();
    public void loginDidFail();
}
