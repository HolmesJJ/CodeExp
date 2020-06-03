package com.example.codeexp.backend.commands;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.codeexp.backend.Messages;
import com.example.codeexp.backend.model.Job;
import com.example.codeexp.backend.model.Period;
import com.example.codeexp.backend.model.Profile;

import java.time.LocalDateTime;

public class ApplyJobCommand {
    private Job job;
    private Profile profile;

    public ApplyJobCommand(Job job, Profile profile) {
        assert job != null : "null job passed";

        this.job = job;
        this.profile = profile;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Boolean hasMatchingPeriod() {
        Period jobPeriod = job.getPeriod();
        Period availability = profile.getPeriod();
        LocalDateTime availStart = availability.getStart();
        LocalDateTime jobEnd = jobPeriod.getStart();

        if (availStart.compareTo(jobEnd) < 0) {
            return true;
        } else {
            return false;
        }
    }

    public String success() {
        return String.format(Messages.APPLICATION_SUCCESSFUL, job.getCompanyName());
    }
}
