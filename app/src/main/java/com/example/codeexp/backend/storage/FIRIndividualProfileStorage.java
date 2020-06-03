package com.example.codeexp.backend.storage;

import com.example.codeexp.backend.exceptions.WrongEntityException;
import com.example.codeexp.backend.model.EnterpriseProfile;
import com.example.codeexp.backend.model.Entity;
import com.example.codeexp.backend.model.IndividualProfile;
import com.example.codeexp.backend.model.Profile;
import com.example.codeexp.backend.model.ProgramState;
import com.example.codeexp.backend.storage.model.FIREnterpriseProfile;
import com.example.codeexp.backend.storage.model.FIRIndividualProfile;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class FIRIndividualProfileStorage implements ProfileStorage, ProfileStorageSync {
    private static FIRIndividualProfileStorage singleton = new FIRIndividualProfileStorage();
    private FIRIndividualProfileStorage() {}

    public static FIRIndividualProfileStorage getSingleton() {
        return singleton;
    }

    @Override
    public void hasLoadedUserProfile(Profile user) {
        // call logic component to update.
        ProgramState.getSingleton().currentProfile = user;
        //TODO: refresh ui if applicable
    }

    @Override
    public void fetchUserProfile(String emailUid) {
        FIRStorageManager.fetchProfile(emailUid, Entity.INDIVIDUAL, new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                FIRIndividualProfile prof = documentSnapshot.toObject(FIRIndividualProfile.class);
                hasLoadedIndividualProfile_withoutEnterprise(prof);
            }
        });
    }

    @Override
    public void writeUserProfile(Profile user) throws WrongEntityException {
        if (!(user instanceof IndividualProfile)) {
            throw new WrongEntityException(user.getEntity());
        }

        FIRStorageManager.writeProfile(user.toFIR());
    }

    private void hasLoadedIndividualProfile_withoutEnterprise(FIRIndividualProfile prof) {
        //NOTE: on failure, ignores request.
        FIRStorageManager.fetchProfile(prof.getMotherCompanyId(), Entity.ENTERPRISE, new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                FIREnterpriseProfile company = documentSnapshot.toObject(FIREnterpriseProfile.class);
                hasLoadedUserProfile(new IndividualProfile(prof, new EnterpriseProfile(company)));
            }
        });
    }
}
