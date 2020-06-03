package com.example.codeexp.backend.Model;

import java.time.LocalDateTime;

public class EnterpriseProfile extends Profile {
    String jobNature;
    int numEmployees; // to look for jobs for
    // INSERT EMPLOYEE GROUPS/OTHER INFORMATION

    public EnterpriseProfile(String emailUid, String firstName, String lastName, String displayName, String description, LocalDateTime start, LocalDateTime end, String jobNature, int numEmployees) {
        super(Entity.ENTERPRISE, emailUid, firstName, lastName, displayName, description, start, end);
        this.jobNature = jobNature;
        this.numEmployees = numEmployees;
    }
}
