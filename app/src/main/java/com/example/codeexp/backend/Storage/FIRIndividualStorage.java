package com.example.codeexp.backend.Storage;

import com.example.codeexp.backend.Exception.WrongEntityException;
import com.example.codeexp.backend.Model.IndividualProfile;
import com.example.codeexp.backend.Model.Profile;

public class FIRIndividualStorage implements StorageManager, StorageSync {
    @Override
    public void fetchUserProfile(String emailUid) {

    }

    @Override
    public void writeUserProfile(Profile user) throws WrongEntityException {
        if (!(user instanceof IndividualProfile)) {
            throw new WrongEntityException(user.getEntity());
        }

        FIRStorageManager.writeProfile(user.toFIR());
    }

    @Override
    public void hasLoadedUserProfile(Profile user) {

    }
}
