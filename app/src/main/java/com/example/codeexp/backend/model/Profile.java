package com.example.codeexp.backend.model;

import java.time.LocalDateTime;

public abstract class Profile {
    private String emailUid;
    private Name name;
    private String displayName;
    private String description;
    private Period period; //looking for jobs

    private Entity entity;

    protected Profile(Entity entity, String emailUid, String firstName, String lastName, String displayName, String description, LocalDateTime start, LocalDateTime end) {
        this.entity = entity;
        this.emailUid = emailUid;
        this.name = new Name(firstName, lastName);
        this.displayName = displayName;
        this.description = description;
        this.period = new Period(start, end);
    }

    public abstract Profile toFIR();

    public Entity getEntity() {
        return entity;
    }
    public String getEmailUid() {
        return emailUid;
    }
    public Name getName() {
        return name;
    }
    public String getDisplayName() {
        return displayName;
    }
    public Period getPeriod() {
        return period;
    }
    public String getDescription() {
        return description;
    }
}
