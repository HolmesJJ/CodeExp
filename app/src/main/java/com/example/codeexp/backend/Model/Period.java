package com.example.codeexp.backend.Model;

import com.example.codeexp.backend.Exception.InvalidDateTimeException;

import java.time.LocalDateTime;

public class Period {
    LocalDateTime start;
    LocalDateTime end;

    public Period(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public void editStartDate(LocalDateTime start) throws InvalidDateTimeException {
        if (isValidPeriod(start, this.end)) {
            this.start = start;
        } else {
            throw new InvalidDateTimeException();
        }
    }

    public void editEndDate(LocalDateTime end) throws InvalidDateTimeException {
        if (isValidPeriod(this.start, end)) {
            this.end = end;
        } else {
            throw new InvalidDateTimeException();
        }
    }

    public boolean isValidPeriod(LocalDateTime start, LocalDateTime end) {
        return end.isAfter(start);
    }
}
