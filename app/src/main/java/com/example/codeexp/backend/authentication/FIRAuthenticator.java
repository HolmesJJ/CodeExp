package com.example.codeexp.backend.authentication;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class FIRAuthenticator implements Authenticator {
    private static FirebaseAuth auth = FirebaseAuth.getInstance();

    private static FIRAuthenticator singleton = new FIRAuthenticator();
    private FIRAuthenticator() {}

    public static FIRAuthenticator getSingleton() {
        return singleton;
    }

    private String currentUserEmail = null;

    //TODO: remember to set delegate appropriately; eg. when at login activity, set delegate = login activity.
    private LoginAuthDelegate loginAuthDelegate = null;
    private SignUpAuthDelegate signUpAuthDelegate = null;

    private void updateCurrentUser(String email) {
        currentUserEmail = email;
    }

    @Override
    public Boolean isAlreadyLoggedIn() {
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            return false;
        }
        updateCurrentUser(user.getEmail());
        return true;
    }

    @Override
    public void setLoginAuthDelegate(LoginAuthDelegate del) {
        loginAuthDelegate = del;
    }

    @Override
    public void setSignUpAuthDelegate(SignUpAuthDelegate del) {
        signUpAuthDelegate = del;
    }

    @Override
    public void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            updateCurrentUser(auth.getCurrentUser().getEmail());
                            loginAuthDelegate.loginDidSucceed();
                        } else {
                            loginAuthDelegate.loginDidFail();
                        }
                    }
                });
    }

    @Override
    public void signUp(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            updateCurrentUser(auth.getCurrentUser().getEmail());
                            signUpAuthDelegate.signUpDidSucceed();
                        } else {
                            signUpAuthDelegate.signUpDidFail();
                        }
                    }
                });
    }

    @Override
    public void logout() {
        auth.signOut();
        updateCurrentUser(null);
    }

    @Override
    public String getCurrentUserEmail() {
        return currentUserEmail;
    }
}
