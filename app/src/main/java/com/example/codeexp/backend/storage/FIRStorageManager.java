package com.example.codeexp.backend.storage;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.codeexp.backend.model.Entity;
import com.example.codeexp.backend.model.JobPresented;
import com.example.codeexp.backend.model.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class FIRStorageManager implements JobPresentedStorage, JobPresentedStorageSync {
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void writeJob(JobPresented job) {
        //TODO
    }

    @Override
    public void fetchJobs(LocalDateTime fromNow) {

    }

    @Override
    public void hasLoadedJobs(List<JobPresented> jobs) {

    }

    public static void writeProfile(Profile profile) {
        db.collection(getCollection(profile.getEntity())).document(profile.getEmailUid()).set(profile, SetOptions.merge());
    }

    public static void fetchProfile(String emailUid, Entity entity, OnSuccessListener<DocumentSnapshot> onSuccess) {
        //TODO: fetch then pass back
        DocumentReference docRef = db.collection(getCollection(entity)).document(emailUid);
        docRef.get().addOnSuccessListener(onSuccess);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fetchProfiles(List<String> emailUids, Entity entity, OnCompleteListener<QuerySnapshot> onCompletion) {
        db.collection((getCollection(entity)))
                .whereIn("emailUid",  emailUids)
                .get()
                .addOnCompleteListener(onCompletion);
    }

    private static String getCollection(Entity entity) {
        switch (entity) {
            case ENTERPRISE:
                return FIRCollections.ENTERPRISE_PROFILE.toString();
            case INDIVIDUAL:
                return FIRCollections.INDIVIDUAL_PROFILE.toString();
            default:
                return ""; //TODO: can throw error
        }
    }

}
