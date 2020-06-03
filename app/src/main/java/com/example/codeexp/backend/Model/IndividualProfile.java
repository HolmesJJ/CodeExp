package com.example.codeexp.backend.Model;

import java.time.LocalDateTime;

public class IndividualProfile extends Profile {
    public IndividualProfile(String emailUid, String firstName, String lastName, String displayName, String description, LocalDateTime start, LocalDateTime end) {
        super(Entity.INDIVIDUAL, emailUid, firstName, lastName, displayName, description, start, end);
    }
}
