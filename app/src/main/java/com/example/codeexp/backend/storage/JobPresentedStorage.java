package com.example.codeexp.backend.storage;

import com.example.codeexp.backend.model.JobPresented;

import java.time.LocalDateTime;
import java.util.List;

public interface JobPresentedStorage {
    public void writeJob(JobPresented job);
    public void fetchJobs();
}

interface JobPresentedStorageSync {
    public void hasLoadedJobs(List<JobPresented> jobs);
}
