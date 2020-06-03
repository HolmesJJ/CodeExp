package com.example.codeexp.backend.storage;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.codeexp.backend.model.Entity;
import com.example.codeexp.backend.model.JobPresented;
import com.example.codeexp.backend.model.Profile;
import com.example.codeexp.backend.model.ProgramState;
import com.example.codeexp.backend.storage.model.FIRJobPresented;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static android.content.ContentValues.TAG;

public class FIRStorageManager implements JobPresentedStorage, JobPresentedStorageSync {
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void hasLoadedJobs(List<JobPresented> jobs) {
        ProgramState.getSingleton().jobsListed = jobs;
        //TODO: tell ui to refresh the jobs listing page
    }

    @Override
    public void writeJob(JobPresented job) {
        db.collection(FIRCollections.JOB_PRESENTED.toString()).document(job.getJobId()).set(job, SetOptions.merge());
    }

    @Override
    public void fetchJobs(LocalDateTime fromNow) {
        db.collection(FIRCollections.JOB_PRESENTED.toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        List<JobPresented> jobs = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            FIRJobPresented job = document.toObject(FIRJobPresented.class);
                            jobs.add(new JobPresented(job));
                        }
                        hasLoadedJobs(jobs);
                    }
                });
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
