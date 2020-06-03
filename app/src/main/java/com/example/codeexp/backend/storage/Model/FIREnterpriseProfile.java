package com.example.codeexp.backend.storage.Model;

import com.example.codeexp.backend.model.EnterpriseProfile;

import java.time.LocalDateTime;

public class FIREnterpriseProfile extends EnterpriseProfile {
    public FIREnterpriseProfile(String emailUid, String firstName, String lastName, String displayName, String description, LocalDateTime start, LocalDateTime end, String jobNature, int numEmployees) {
        super(emailUid, firstName, lastName, displayName, description, start, end, jobNature, numEmployees);
    }
    public FIREnterpriseProfile(EnterpriseProfile prof) {
        this(prof.getEmailUid(), prof.getName().getFirstName(), prof.getName().getLastName(), prof.getDisplayName(), prof.getDescription(), prof.getPeriod().getStart(), prof.getPeriod().getEnd(),
                prof.getJobNature(), prof.getNumEmployees());
    }
}
