package com.example.codeexp.backend.Model;

import java.time.LocalDateTime;

public class EnterpriseProfile extends Profile {
    // INSERT EMPLOYEE GROUPS/OTHER INFORMATION

    public EnterpriseProfile(String emailUid, String firstName, String lastName, String displayName, String description, LocalDateTime start, LocalDateTime end) {
        super(Entity.ENTERPRISE, emailUid, firstName, lastName, displayName, description, start, end);
    }
}
