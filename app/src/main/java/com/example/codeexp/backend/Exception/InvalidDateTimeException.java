package com.example.codeexp.backend.Exception;

public class InvalidDateTimeException extends Exception {
    public InvalidDateTimeException() {
        super("Start date cannot be after end date!");
    }
}
