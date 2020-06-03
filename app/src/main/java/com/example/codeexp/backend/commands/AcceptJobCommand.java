package com.example.codeexp.backend.commands;

import com.example.codeexp.backend.Messages;
import com.example.codeexp.backend.model.EnterpriseProfile;
import com.example.codeexp.backend.model.IndividualProfile;
import com.example.codeexp.backend.model.Job;
import com.example.codeexp.backend.model.JobAccepted;

public class AcceptJobCommand {
    private EnterpriseProfile enterprise;
    private JobAccepted job;
    private IndividualProfile profile;

    public AcceptJobCommand(EnterpriseProfile enterprise, JobAccepted job, IndividualProfile profile) {
        assert job != null : "null job passed";

        this.enterprise = enterprise;
        this.job = job;
        this.profile = profile;
        bindIndividual();
    }

    public String bindIndividual() {
        profile.setJobAccepted(job);
        enterprise.addNewEmployee(profile);
        return String.format(Messages.JOB_ACCEPTED, job.getCompanyName(), job.getPosition());
    }
}
