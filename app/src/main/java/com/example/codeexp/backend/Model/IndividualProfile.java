package com.example.codeexp.backend.Model;

import com.example.codeexp.backend.Storage.Model.FIRIndividualProfile;

import java.time.LocalDateTime;

public class IndividualProfile extends Profile {
    String bankAccount;
    EnterpriseProfile motherCompany;
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

    @Override
    public FIRIndividualProfile toFIR() {
        return new FIRIndividualProfile(this);
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public EnterpriseProfile getMotherCompany() {
        return motherCompany;
    }

    public String getMotherCompanyStaffId() {
        return motherCompanyStaffId;
    }

    public JobAccepted getJobAccepted() {
        return jobAccepted;
    }

    public Boolean getCheckedIn() {
        return isCheckedIn;
    }
}
