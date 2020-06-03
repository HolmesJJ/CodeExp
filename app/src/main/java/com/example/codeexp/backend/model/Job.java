package com.example.codeexp.backend.model;

import java.time.LocalDateTime;

public abstract class Job {
    String position;
    String companyId; //likely email; can consider new Company object (dif from EnterpriseProfile)
    String companyName;
    String jobNature;
    Period period;

    public Job(String position, String companyId, String companyName, String jobNature, LocalDateTime start, LocalDateTime end) {
        this.position = position;
        this.companyId = companyId;
        this.companyName = companyName;
        this.jobNature = jobNature;
        this.period = new Period(start, end);
    }

    public String getPosition() {
        return position;
    }
    public Period getPeriod() {
        return period;
    }
    public String getCompanyId() {
        return companyId;
    }
    public String getCompanyName() {
        return companyName;
    }
    public String getJobNature() {
        return jobNature;
    }
}
