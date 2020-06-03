package com.example.codeexp.backend.exceptions;

public class UnknownException extends Exception {
    public UnknownException() {
        super("An unknown error has occured. Please try again in a few moments.");
    }
}
