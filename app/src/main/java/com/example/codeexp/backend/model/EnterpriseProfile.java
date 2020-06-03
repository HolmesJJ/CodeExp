package com.example.codeexp.backend.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.codeexp.backend.storage.model.FIREnterpriseProfile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EnterpriseProfile extends Profile {
    String jobNature;
    List<IndividualProfile> employees;
    List<IndividualProfile> offered;
    int numEmployees; // to look for jobs for
    // INSERT EMPLOYEE GROUPS/OTHER INFORMATION

    public EnterpriseProfile(String emailUid, String firstName, String lastName, String displayName, String description, LocalDateTime start, LocalDateTime end, String jobNature, int numEmployees) {
        super(Entity.ENTERPRISE, emailUid, firstName, lastName, displayName, description, start, end);
        this.jobNature = jobNature;
        this.numEmployees = numEmployees;
        this.employees = new ArrayList<>();
        this.offered = new ArrayList<>();
    }

    public EnterpriseProfile(FIREnterpriseProfile profile) {
        this(profile.getEmailUid(), profile.getName().getFirstName(), profile.getName().getLastName(), profile.getDisplayName(), profile.getDescription(), profile.getPeriod().getStart(), profile.getPeriod().getEnd(),
                profile.getJobNature(), profile.getNumEmployees());
    }

    public EnterpriseProfile(FIREnterpriseProfile profile, List<IndividualProfile> employees, List<IndividualProfile> offered) {
        this(profile);
        this.employees = employees;
        this.offered = offered;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public FIREnterpriseProfile toFIR() {
        return new FIREnterpriseProfile(this);
    }

    public String getJobNature() {
        return jobNature;
    }

    public int getNumEmployees() {
        return numEmployees;
    }

    public List<IndividualProfile> getEmployees() {
        return employees;
    }

    public List<IndividualProfile> getOffered() {
        return offered;
    }

    public void addNewEmployee(IndividualProfile newEmployee) {
        employees.add(newEmployee);
        offered.remove(newEmployee);
    }

    public void addNewOffer(IndividualProfile profile) {
        offered.add(profile);
    }
}
