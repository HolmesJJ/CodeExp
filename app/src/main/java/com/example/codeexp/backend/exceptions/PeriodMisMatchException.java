package com.example.codeexp.backend.exceptions;

public class PeriodMisMatchException extends Exception {
    public PeriodMisMatchException() {
        super("The job period and your availability do not match.");
    }
}
