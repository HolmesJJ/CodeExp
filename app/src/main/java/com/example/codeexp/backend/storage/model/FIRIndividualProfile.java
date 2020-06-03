package com.example.codeexp.backend.storage.model;

import com.example.codeexp.backend.model.Entity;
import com.example.codeexp.backend.model.IndividualProfile;
import com.example.codeexp.backend.model.JobAccepted;
import com.example.codeexp.backend.model.Name;
import com.example.codeexp.backend.model.Period;
import com.example.codeexp.backend.model.Profile;

public class FIRIndividualProfile extends Profile {
    String bankAccount;
    String motherCompanyId; // email as id in storage
    String motherCompanyStaffId;
    JobAccepted jobAccepted;
    Boolean isCheckedIn;

    public FIRIndividualProfile(String emailUid, Name name, String displayName, String description, Period period,
                                String bankAccount, String motherCompanyId, String motherCompanyStaffId, JobAccepted jobAccepted, Boolean isCheckedIn) {
        super(Entity.INDIVIDUAL, emailUid, name.getFirstName(), name.getLastName(), displayName, description, period.getStart(), period.getEnd());
        this.bankAccount = bankAccount;
        this.motherCompanyId = motherCompanyId;
        this.motherCompanyStaffId = motherCompanyStaffId;
        this.jobAccepted = jobAccepted;
        this.isCheckedIn = isCheckedIn;
    }
    public FIRIndividualProfile(IndividualProfile prof) {
        this(prof.getEmailUid(), prof.getName(), prof.getDisplayName(), prof.getDescription(), prof.getPeriod(),
                prof.getBankAccount(), prof.getMotherCompany().getEmailUid(), prof.getMotherCompanyStaffId(), prof.getJobAccepted(), prof.getCheckedIn());
    }

    @Override
    public Profile toFIR() {
        return this;
    }

    public String getBankAccount() {
        return bankAccount;
    }
    public Boolean getCheckedIn() {
        return isCheckedIn;
    }
    public String getMotherCompanyStaffId() {
        return motherCompanyStaffId;
    }
    public String getMotherCompanyId() {
        return motherCompanyId;
    }
    public JobAccepted getJobAccepted() {
        return jobAccepted;
    }
}
