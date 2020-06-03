package com.example.codeexp.backend.model;

import com.example.codeexp.backend.storage.Model.FIREnterpriseProfile;

import java.time.LocalDateTime;

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
    }

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

    public void addNewEmployee(IndividualProfile newEmployee) {
        employees.add(newEmployee);
        offered.remove(profile);
    }

    public void addNewOffer(IndividualProfile profile) {
        offered.add(profile);
    }
}