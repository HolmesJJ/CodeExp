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
    private static String currentUserEmail = null;

    //TODO: remember to set delegate appropriately; eg. when at login activity, set delegate = login activity.
    public AuthDelegate authDelegate = null;

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
    public void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            updateCurrentUser(auth.getCurrentUser().getEmail());
                            authDelegate.loginDidSucceed();
                        } else {
                            authDelegate.loginDidFail();
                        }
                    }
                });
    }

    @Override
    public void signUp(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            updateCurrentUser(auth.getCurrentUser().getEmail());
                            authDelegate.signUpDidSucceed();
                        } else {
                            authDelegate.signUpDidFail();
                        }
                    }
                });
    }

    @Override
    public void logout() {
        auth.signOut();
        updateCurrentUser(null);
    }

    public static String getCurrentUserEmail() {
        return currentUserEmail;
    }
}

/*
Sample signUp activity as delegate:
public void signUpDidSucceed() {
Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                }
then fail like
Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
* */
