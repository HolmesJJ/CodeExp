package com.example.codeexp.backend.storage;

import com.example.codeexp.backend.model.Entity;
import com.example.codeexp.backend.model.JobPresented;
import com.example.codeexp.backend.model.Profile;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.time.LocalDateTime;
import java.util.List;

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
        String collection = getCollection(profile.getEntity());
        db.collection(collection).document(profile.getEmailUid()).set(profile, SetOptions.merge());
    }

    public static void fetchProfile(String emailUid, Entity entity) {
        String collection = getCollection(entity);
        //TODO: fetch then pass back
        //h
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
