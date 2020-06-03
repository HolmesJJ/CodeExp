package com.example.codeexp.backend.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.codeexp.backend.storage.model.FIRJobPresented;

import java.time.LocalDateTime;

public class JobPresented extends Job {
    Entity target;
    double salaryMin;
    double salaryMax;
    int numSlots;
    LocalDateTime expirationDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public JobPresented(String jobId, String position, String companyId, String companyName, String jobNature, LocalDateTime start, LocalDateTime end, Entity target, double salaryMax, double salaryMin, int numSlots, LocalDateTime expirationDate) {
        super(jobId, position, companyId, companyName, jobNature, start, end);
        this.target = target;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.numSlots = numSlots;
        this.expirationDate = expirationDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public JobPresented(FIRJobPresented job) {
        this(job.getJobId(), job.getPosition(), job.getCompanyId(), job.getCompanyName(), job.getJobNature(), job.getPeriod().getStart(), job.getPeriod().getEnd(), job.getTarget(), job.getSalaryMax(), job.getSalaryMin(), job.getNumSlots(), job.getExpirationDate());
    }

    public FIRJobPresented toFIR() {
        return new FIRJobPresented(this);
    }

    public Entity getTarget() {
        return target;
    }
    public double getSalaryMax() {
        return salaryMax;
    }
    public double getSalaryMin() {
        return salaryMin;
    }
    public int getNumSlots() {
        return numSlots;
    }
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
}
