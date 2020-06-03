package com.example.codeexp.backend.storage;

public enum FIRCollections {
    INDIVIDUAL_PROFILE("Individual"),
    ENTERPRISE_PROFILE("Enterprise"),
    JOB_PRESENTED("GenericJobs");

    private final String path;

    FIRCollections(String path) {
        this.path = path;
    }


    @Override
    public String toString() {
        return path;
    }
}
