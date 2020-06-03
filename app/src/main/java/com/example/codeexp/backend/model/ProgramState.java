package com.example.codeexp.backend.model;

import java.util.List;

/**
 * Current shared state of the program to be updated when info is available.
 */
public class ProgramState {
    private static ProgramState singleton = new ProgramState();

    public static ProgramState getSingleton() {
        return singleton;
    }

    public Profile currentProfile;
    public List<JobPresented> jobsListed;

    private ProgramState() {}

}
