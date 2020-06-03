package com.example.codeexp.backend.storage.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.codeexp.backend.model.EnterpriseProfile;
import com.example.codeexp.backend.model.Entity;
import com.example.codeexp.backend.model.Profile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FIREnterpriseProfile extends Profile {
    String jobNature;
    List<String> employeesEmails;
    List<String> offeredEmails;
    int numEmployees;

    protected FIREnterpriseProfile(String emailUid, String firstName, String lastName, String displayName, String description, LocalDateTime start, LocalDateTime end,
                                   String jobNature, List<String> employeesEmails, List<String> offeredEmails, int numEmployees) {
        super(Entity.ENTERPRISE, emailUid, firstName, lastName, displayName, description, start, end);
        this.jobNature = jobNature;
        this.employeesEmails = employeesEmails;
        this.offeredEmails = offeredEmails;
        this.numEmployees = numEmployees;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public FIREnterpriseProfile(EnterpriseProfile prof) {
        this(prof.getEmailUid(), prof.getName().getFirstName(), prof.getName().getLastName(), prof.getDisplayName(), prof.getDescription(), prof.getPeriod().getStart(), prof.getPeriod().getEnd(),
                prof.getJobNature(), prof.getEmployees().stream().map(e -> e.getEmailUid()).collect(Collectors.toList()),
                prof.getOffered().stream().map(e -> e.getEmailUid()).collect(Collectors.toList()), prof.getNumEmployees());
    }

    @Override
    public Profile toFIR() {
        return this;
    }

    public String getJobNature() {
        return jobNature;
    }
    public int getNumEmployees() {
        return numEmployees;
    }
    public List<String> getEmployeesEmails() {
        return employeesEmails;
    }
    public List<String> getOfferedEmails() {
        return offeredEmails;
    }
}
