package com.example.codeexp.backend.exceptions;

public class InvalidDateTimeException extends Exception {
    public InvalidDateTimeException() {
        super("Start date cannot be after end date!");
    }
}
