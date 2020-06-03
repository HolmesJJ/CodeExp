package com.example.codeexp.backend.storage;

import com.example.codeexp.backend.exceptions.WrongEntityException;
import com.example.codeexp.backend.model.IndividualProfile;
import com.example.codeexp.backend.model.Profile;
import com.example.codeexp.backend.storage.Model.FIRIndividualProfile;

public class FIRIndividualProfileStorage implements ProfileStorage, ProfileStorageSync {
    @Override
    public void fetchUserProfile(String emailUid) {
        //TODO: fetch indiv prof, then take company id(email) to retrieve Enterpr prof
        //TODO: call hasLoadedIndiid.....()
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

    private void hasLoadedIndividualProfile_withoutEnterprise(FIRIndividualProfile prof) {
        //TODO: fetch enterprise prof, if not existent, return null? or ignore?
        //TODO: then create IndivProf and pass back to hasLoadedUserProf
    }
}
