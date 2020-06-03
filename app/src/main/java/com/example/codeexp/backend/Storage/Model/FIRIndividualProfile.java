package com.example.codeexp.backend.Storage.Model;

import com.example.codeexp.backend.Model.Entity;
import com.example.codeexp.backend.Model.IndividualProfile;
import com.example.codeexp.backend.Model.JobAccepted;
import com.example.codeexp.backend.Model.Name;
import com.example.codeexp.backend.Model.Period;
import com.example.codeexp.backend.Model.Profile;

import java.time.LocalDateTime;

public class FIRIndividualProfile extends Profile {
    String bankAccount;
    String motherCompanyId; //TODO: prolly email as id in storage
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
}
