package com.example.codeexp.backend.Storage;

import com.example.codeexp.backend.Model.JobPresented;

import java.time.LocalDateTime;
import java.util.List;

public interface JobPresentedStorage {
    public void writeJob(JobPresented job);
    public void fetchJobs(LocalDateTime fromNow);
}

interface JobPresentedStorageSync {
    public void hasLoadedJobs(List<JobPresented> jobs);
}
