package com.example.codeexp.backend.storage;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.codeexp.backend.exceptions.WrongEntityException;
import com.example.codeexp.backend.model.EnterpriseProfile;
import com.example.codeexp.backend.model.Entity;
import com.example.codeexp.backend.model.IndividualProfile;
import com.example.codeexp.backend.model.Profile;
import com.example.codeexp.backend.model.ProgramState;
import com.example.codeexp.backend.storage.model.FIREnterpriseProfile;
import com.example.codeexp.backend.storage.model.FIRIndividualProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FIREnterpriseProfileStorage implements ProfileStorage, ProfileStorageSync {
    private static FIREnterpriseProfileStorage singleton = new FIREnterpriseProfileStorage();

    private FIREnterpriseProfileStorage() {}

    public static FIREnterpriseProfileStorage getSingleton() {
        return singleton;
    }

    @Override
    public void hasLoadedUserProfile(Profile user) {
        // call logic/app state function to pass info over
        ProgramState.getSingleton().currentProfile = user;
        //TODO: refresh ui if needed
    }

    @Override
    public void fetchUserProfile(String emailUid) {
        FIRStorageManager.fetchProfile(emailUid, Entity.ENTERPRISE, new OnSuccessListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                FIREnterpriseProfile company = documentSnapshot.toObject(FIREnterpriseProfile.class);
                if (company == null) {
                    return; //TODO: can alert
                }
                hasLoadedEnterpriseProfile_withoutIndividuals(company);
            }
        });
    }

    @Override
    public void writeUserProfile(Profile user) throws WrongEntityException {
        if (!(user instanceof EnterpriseProfile)) {
            throw new WrongEntityException(user.getEntity());
        }

        FIRStorageManager.writeProfile(user.toFIR());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void hasLoadedEnterpriseProfile_withoutIndividuals(FIREnterpriseProfile company) {
        FIRStorageManager.fetchProfiles(company.getEmployeesEmails(), Entity.INDIVIDUAL, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                List<IndividualProfile> employees = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    FIRIndividualProfile prof = document.toObject(FIRIndividualProfile.class);
                    employees.add(new IndividualProfile(prof, null)); //TODO: remove cyclic dependency hence no need to null
                }
                FIRStorageManager.fetchProfiles(company.getOfferedEmails(), Entity.INDIVIDUAL, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        List<IndividualProfile> offered = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            FIRIndividualProfile prof = document.toObject(FIRIndividualProfile.class);
                            offered.add(new IndividualProfile(prof, null));
                        }

                        hasLoadedUserProfile(new EnterpriseProfile(company, employees, offered));
                    }
                });
            }
        });
    }
}
