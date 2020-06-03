package com.example.codeexp.backend.Model;

import java.time.LocalDateTime;

public class JobAccepted extends Job {
    String tempStaffId;
    double salary;

    public JobAccepted(String position, String companyId, String companyName, String jobNature, LocalDateTime start, LocalDateTime end, String tempStaffId, double salary) {
        super(position, companyId, companyName, jobNature, start, end);
        this.tempStaffId = tempStaffId;
        this.salary = salary;
    }

    public String getTempStaffId() {
        return tempStaffId;
    }

    public double getSalary() {
        return salary;
    }
}
