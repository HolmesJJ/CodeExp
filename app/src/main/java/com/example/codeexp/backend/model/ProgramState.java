package com.example.codeexp.backend.model;

import com.example.codeexp.backend.authentication.AuthNotifierDelegate;
import com.example.codeexp.backend.storage.FIREnterpriseProfileStorage;
import com.example.codeexp.backend.storage.FIRIndividualProfileStorage;
import com.example.codeexp.backend.storage.StorageUpdateDelegate;

import java.util.List;

/**
 * Current shared state of the program to be updated when info is available.
 */
public class ProgramState implements StorageUpdateDelegate {
    private static ProgramState singleton = new ProgramState();

    public static ProgramState getSingleton() {
        return singleton;
    }

    public AuthNotifierDelegate del;

    public Profile currentProfile;
    public List<JobPresented> jobsListed;

    private ProgramState() {}

    public void init(String emailUid) {
        // load user profile; the correct one will be saved to ProgramState
        FIRIndividualProfileStorage.getSingleton().fetchUserProfile(emailUid);
        FIREnterpriseProfileStorage.getSingleton().fetchUserProfile(emailUid);
    }

    @Override
    public void profileDidUpdate() {
        del.authDidSucceedAndLoadProfile();
    }

    @Override
    public void jobsDidUpdate() {

    }
}
