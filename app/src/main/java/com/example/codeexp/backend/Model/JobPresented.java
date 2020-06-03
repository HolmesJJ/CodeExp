package com.example.codeexp.backend.Model;

import java.time.LocalDateTime;

public class JobPresented extends Job {
    Entity target;
    double salaryMin;
    double salaryMax;
    int numSlots;
    LocalDateTime expirationDate;

    public JobPresented(String position, String companyId, String companyName, String jobNature, LocalDateTime start, LocalDateTime end, Entity target, double salaryMax, double salaryMin, int numSlots, LocalDateTime expirationDate) {
        super(position, companyId, companyName, jobNature, start, end);
        this.target = target;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.numSlots = numSlots;
        this.expirationDate = expirationDate;
    }
}
