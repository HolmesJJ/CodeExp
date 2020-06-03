package com.example.codeexp.backend.authentication;

/**
 * Presenter views and logic managers post-authentication should implement this interface.
 */
public interface LoginAuthDelegate {
    public void loginDidSucceed();
    public void loginDidFail();
}
