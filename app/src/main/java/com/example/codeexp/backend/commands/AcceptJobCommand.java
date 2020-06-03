package com.example.codeexp.backend.commands;

import com.example.codeexp.backend.Messages;
import com.example.codeexp.backend.model.EnterpriseProfile;
import com.example.codeexp.backend.model.IndividualProfile;
import com.example.codeexp.backend.model.Job;
import com.example.codeexp.backend.model.JobAccepted;
import com.example.codeexp.backend.model.Profile;
import com.example.codeexp.backend.model.ProgramState;

public class AcceptJobCommand {
    private EnterpriseProfile enterprise;
    private JobAccepted job;
    private Profile profile;

    public AcceptJobCommand(EnterpriseProfile enterprise, JobAccepted job, IndividualProfile profile) {
        assert job != null : "null job passed";

        this.enterprise = enterprise;
        this.job = job;
        this.profile = ProgramState.currentProfile;
        bindIndividual();
    }

    public String bindIndividual() {
        profile.setJobAccepted(job);
        enterprise.addNewEmployee(profile);
        return String.format(Messages.JOB_ACCEPTED, job.getCompanyName(), job.getPosition());
    }
}
