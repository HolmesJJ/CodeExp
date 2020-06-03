package com.example.codeexp.backend.Model;

import java.time.LocalDateTime;

public class IndividualProfile extends Profile {
    String bankAccount;
    EnterpriseProfile motherCompany; //TODO: can consider storing string id and retrieve company info separately if not enuf space
    String motherCompanyStaffId;
    JobAccepted jobAccepted;
    Boolean isCheckedIn;

    public IndividualProfile(String emailUid, String firstName, String lastName, String displayName, String description, LocalDateTime start, LocalDateTime end,
                             String bankAccount, EnterpriseProfile motherCompany, String motherCompanyStaffId, JobAccepted jobAccepted, Boolean isCheckedIn) {
        super(Entity.INDIVIDUAL, emailUid, firstName, lastName, displayName, description, start, end);
        this.bankAccount = bankAccount;
        this.motherCompany = motherCompany;
        this.motherCompanyStaffId = motherCompanyStaffId;
        this.jobAccepted = jobAccepted;
        this.isCheckedIn = isCheckedIn;
    }
}
