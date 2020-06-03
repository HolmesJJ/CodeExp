package com.example.codeexp.backend.Exception;

public class EmptyNameException extends Exception {
    private int val;

    public EmptyNameException(int val) {
        this.val = val;
        outputErrorMessage();
    }

    public void outputErrorMessage() {
        switch (val) {
            case 1:
                 System.err.println("First name cannot be empty");
            case 2:
                System.err.println("Last name cannot be empty");
            default:
                System.err.println("Unknown error");
        }
    }
}
