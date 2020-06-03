package com.example.codeexp.backend.Storage;

public enum FIRCollections {
    INDIVIDUAL_PROFILE("Individual"),
    ENTERPRISE_PROFILE("Enterprise");

    private final String path;

    FIRCollections(String path) {
        this.path = path;
    }


    @Override
    public String toString() {
        return path;
    }
}
