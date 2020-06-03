package com.example.codeexp.backend.commands;

import com.example.codeexp.backend.Messages;

import java.time.LocalDateTime;

public class ApplyJobCommand {
    private Job job;
    private Profile profile;

    public ApplyJobCommand(Job job, Profile profile) {
        assert job != null : "null job passed";

        this.job = job;
        this.profile = profile;
    }

    public bool hasMatchingPeriod() {
        Period jobPeriod = job.getPeriod();
        Period availability = profile.getPeriod();
        LocalDateTime availStart = availability.getStart();
        LocalDateTime jobEnd = jobPeriod.getStart();

        if (availStart.compareT(jobEnd) < 0) {
            return true;
        } else {
            return false;
        }
    }

    public String success() {
        return String.format(Messages.APPLICATION_SUCCESSFUL, job.getCompanyName());
    }
}
