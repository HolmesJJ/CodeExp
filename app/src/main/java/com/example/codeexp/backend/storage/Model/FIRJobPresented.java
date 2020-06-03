package com.example.codeexp.backend.storage.Model;

import com.example.codeexp.backend.model.Entity;
import com.example.codeexp.backend.model.JobPresented;

import java.time.LocalDateTime;

public class FIRJobPresented extends JobPresented {
    public FIRJobPresented(String position, String companyId, String companyName, String jobNature, LocalDateTime start, LocalDateTime end, Entity target, double salaryMax, double salaryMin, int numSlots, LocalDateTime expirationDate) {
        super(position, companyId, companyName, jobNature, start, end, target, salaryMax, salaryMin, numSlots, expirationDate);
    }
    public FIRJobPresented(JobPresented job) {
        this(job.getPosition(), job.getCompanyId(), job.getCompanyName(), job.getJobNature(), job.getPeriod().getStart(), job.getPeriod().getEnd(),
                job.getTarget(), job.getSalaryMax(), job.getSalaryMin(), job.getNumSlots(), job.getExpirationDate());
    }
}
