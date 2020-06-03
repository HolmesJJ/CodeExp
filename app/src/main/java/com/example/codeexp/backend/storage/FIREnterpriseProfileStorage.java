package com.example.codeexp.backend.storage;

import com.example.codeexp.backend.exceptions.WrongEntityException;
import com.example.codeexp.backend.model.EnterpriseProfile;
import com.example.codeexp.backend.model.Entity;
import com.example.codeexp.backend.model.Profile;

public class FIREnterpriseProfileStorage implements ProfileStorage, ProfileStorageSync {

    @Override
    public void fetchUserProfile(String emailUid) {
        //TODO: fetch profile and pass to hasFetchedUserProfile
        FIRStorageManager.fetchProfile(emailUid, Entity.ENTERPRISE);
    }

    @Override
    public void writeUserProfile(Profile user) throws WrongEntityException {
        if (!(user instanceof EnterpriseProfile)) {
            throw new WrongEntityException(user.getEntity());
        }

        FIRStorageManager.writeProfile(user.toFIR());
    }

    @Override
    public void hasLoadedUserProfile(Profile user) {
        //TODO: call logic/app state function to pass info over
    }
}
