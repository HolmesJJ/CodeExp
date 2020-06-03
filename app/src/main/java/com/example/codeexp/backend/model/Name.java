package com.example.codeexp.backend.model;

import com.example.codeexp.backend.exceptions.EmptyNameException;

public class Name {
    String firstName;
    String lastName;

    Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    void editFirstName(String firstName) throws EmptyNameException {
        if (firstName.length() == 0) {
            throw new EmptyNameException(1);
        }
    }

    void editLastName(String lastName) throws EmptyNameException {
        if (lastName.length() == 0) {
            throw new EmptyNameException(2);
        }
    }

    void editName(String firstName, String lastName) throws EmptyNameException {
        if (firstName.length() == 0) {
            throw new EmptyNameException(1);
        } else if (lastName.length() == 0) {
            throw new EmptyNameException(2);
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
